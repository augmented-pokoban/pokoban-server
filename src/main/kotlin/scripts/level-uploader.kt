package scripts

import com.github.salomonbrys.kotson.jsonObject
import com.mongodb.MongoCommandException
import org.slf4j.LoggerFactory
import server.repositories.DbRepository
import server.repositories.FileRepository
import server.services.LevelService
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Paths
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.streams.asSequence

fun main(args: Array<String>) {

    val mongoLogger = Logger.getLogger("org.mongodb.driver")
    mongoLogger.level = Level.WARNING

    val logger4j = org.apache.log4j.Logger.getRootLogger()
    logger4j.level = org.apache.log4j.Level.toLevel("WARN")

    val levelType = "train"
    val levelDifficulty = "easy"
    var totalFilesStored = 0


    val threadCount = (0 until 10).toList()
    val fileIterator = Files.list(Paths.get("../levels/$levelDifficulty")).asSequence().iterator()

    // parallel
    threadCount.parallelStream().forEach {

        var filesStored = 0

        println("Started thread $it")

        val fileRepository = FileRepository()
        val dbRepository = DbRepository.getSupervisedLevelsRepo()

        while (fileIterator.hasNext()) {

            // get the next file from stream
            val file = fileIterator.next()
            val filename = file.fileName.toString()

            // convert to state
            val fileBytes = Files.readAllBytes(file)

            // upload file blob
            fileRepository.insertLevel(ByteArrayInputStream(fileBytes), "$levelType/$filename")

            // upload metadata
            val level = LevelService.instance.loadLevel(InputStreamReader(fileBytes.inputStream()), filename)
            val metadata = jsonObject(
                    "_id" to filename.replace(".lvl", ""),
                    "fileUrl" to "https://pokobanserver.blob.core.windows.net/levels/$levelType/$filename",
                    "height" to level.height,
                    "width" to level.width,
                    "difficulty" to levelDifficulty,
                    "relativePath" to "$levelType/$filename",
                    "countWalls" to level.getWalls().count(),
                    "countGoals" to level.getGoals().count(),
                    "countBoxes" to level.getBoxes().count()
            )

            try {
                dbRepository.insert(metadata)
            } catch (e: MongoCommandException) {
                println(e.message)
                Thread.sleep(5000)
            }

            filesStored++
            if (filesStored % 100 == 0) println("Thread $it: Stored $filesStored level-files on blob file storage.")
        }

        totalFilesStored += filesStored
    }

    println("Stored a total of $totalFilesStored level-files.")
}
