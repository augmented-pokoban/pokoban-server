package scripts

import ch.qos.logback.classic.LoggerContext
import com.github.salomonbrys.kotson.jsonObject
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.slf4j.LoggerFactory
import server.repositories.DbRepository
import server.repositories.FileRepository
import server.services.LevelService
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.streams.asSequence
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.locks.ReentrantLock

val levelType = "unsupervised"
val levelDifficulty = "supervised"
val offset = 0
val chunkCount = 10000
val upsert = true
val poolSize = 500
var threadPool = ForkJoinPool(poolSize)
val threadCount = 1
var totalFilesStored = 0
var totalJobs = 0
val totalFilesCount = Files.list(Paths.get("../levels/$levelDifficulty")).count()

fun main(args: Array<String>) {

    // set logging
    /*val mongoLogger = Logger.getLogger("org.mongodb.driver")
    mongoLogger.level = Level.WARNING*/

    val loggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
    val rootLogger = loggerContext.getLogger("org.mongodb.driver")
    rootLogger.level = ch.qos.logback.classic.Level.OFF

    val chunks = (0 until chunkCount).map { mutableListOf<String>() }.toMutableList()
    val threads = (0 until threadCount).toList()

    // evenly divide the level files into the chunks
    /*Files.list(Paths.get("../levels/$levelDifficulty")).asSequence().drop(offset).forEach {
        val smallestChunk = chunks.mapIndexed { i, it -> Pair(i, it.size) }.minBy { it.second }
        chunks[smallestChunk!!.first].add(it.toAbsolutePath().toString())
    }*/

    val fileIterator = Files.list(Paths.get("../levels/$levelDifficulty")).asSequence().drop(offset).iterator()

    threadPool.submit {
        uploadIterator(threads, fileIterator)
        // uploadChunks(chunks)
    }.get()
}

fun uploadChunks(chunks: MutableList<MutableList<String>>) {
    chunks.parallelStream().forEach {
        // each chunk

        println("Started thread $it")

        val fileRepository = FileRepository()
        val dbRepository = DbRepository.getSupervisedLevelsRepo()

        it.forEach {
            // each level in chunk
            val file = Paths.get(it)
            uploadFile(file, fileRepository, dbRepository)
        }
    }

    println("Stored a total of $totalFilesStored level-files.")
}

fun uploadIterator(threads: List<Number>, fileIterator: Iterator<Path>) {

    // iterator read lock
    val lock = ReentrantLock()

    // parallel threads for reading files
    threads.parallelStream().forEach {

        println("Starting thread $it")

        val jobs = mutableListOf<Job>()
        val fileRepository = FileRepository()
        val dbRepository = DbRepository.getUnsupervisedLevelsRepo()

        while (fileIterator.hasNext()) {
            // get the next file from stream - thread safe
            lock.lock()
            val file = fileIterator.next()
            lock.unlock()

            jobs.add(launch { uploadFile(file, fileRepository, dbRepository) })

            totalJobs++
            if (totalJobs % 1000 == 0) println("Thread-$it: Queued $totalJobs level-file jobs.")
        }

        // wait for all the jobs on this thread to finish
        runBlocking {
            jobs.forEach { it.join() }
        }
    }
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

    val result = dbRepository.insert(metadata, upsert)

    println("Uploaded ${totalFilesStored++} of $totalFilesCount level-files ($filename).")
}