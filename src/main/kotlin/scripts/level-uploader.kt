package scripts

import com.github.salomonbrys.kotson.jsonObject
import com.mongodb.MongoCommandException
import server.repositories.DbRepository
import server.repositories.FileRepository
import server.services.LevelService
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.net.URI
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.streams.asSequence

val levelType = "train"
val levelDifficulty = "medium"
var totalFilesStored = 0
val threadCount = 100
val offset = 527260
val chunkCount = 10
val upsert = false

fun main(args: Array<String>) {

    // set logging
    val mongoLogger = Logger.getLogger("org.mongodb.driver")
    mongoLogger.level = Level.WARNING

    val chunks = (0 until chunkCount).map { mutableListOf<String>() }.toMutableList()
    val threads = (0 until threadCount).toList()

    // evenly divide the level files into the chunks
    Files.list(Paths.get("../levels/$levelDifficulty")).asSequence().drop(offset).forEach {
        val smallestChunk = chunks.mapIndexed { i, it -> Pair(i, it.size) }.minBy { it.second }
        chunks[smallestChunk!!.first].add(it.toAbsolutePath().toString())
    }

    val fileIterator = Files.list(Paths.get("../levels/$levelDifficulty")).asSequence().drop(offset).iterator()

    uploadIterator(threads, fileIterator)
    uploadChunks(chunks)
}

fun uploadChunks(chunks: MutableList<MutableList<String>>) {
    chunks.parallelStream().forEach { // each chunk

        println("Started thread $it")

        var filesStored = 0
        val fileRepository = FileRepository()
        val dbRepository = DbRepository.getSupervisedLevelsRepo()

        it.forEach { // each level in chunk

            val file = Paths.get(it)

            uploadFile(file, fileRepository, dbRepository)

            filesStored++
            if (filesStored % 1000 == 0) println("Thread $it: Stored $filesStored level-files on blob file storage.")
        }

        totalFilesStored += filesStored
    }

    println("Stored a total of $totalFilesStored level-files.")
}

fun uploadIterator(threads: List<Number>, fileIterator: Iterator<Path>) {
    // parallel
    threads.parallelStream().forEach {

        println("Started thread $it")

        var filesStored = 0
        val fileRepository = FileRepository()
        val dbRepository = DbRepository.getSupervisedLevelsRepo()

        while (fileIterator.hasNext()) {

            // get the next file from stream
            val file = fileIterator.next()
            uploadFile(file, fileRepository, dbRepository)

            filesStored++
            if (filesStored % 1000 == 0) println("Thread $it: Stored $filesStored level-files on blob file storage.")
        }

        totalFilesStored += filesStored
    }

    println("Stored a total of $totalFilesStored level-files.")
}

fun uploadFile(file: Path, fileRepository: FileRepository, dbRepository: DbRepository) {
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
        dbRepository.insert(metadata, upsert)
    } catch (e: MongoCommandException) {
        println(e.message)
        Thread.sleep(5000)
    }
}