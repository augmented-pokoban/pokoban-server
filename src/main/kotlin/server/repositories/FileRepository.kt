package server.repositories
import com.microsoft.azure.storage.CloudStorageAccount
import com.microsoft.azure.storage.blob.CloudBlockBlob
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader


class FileRepository{
    private val storageConnectionString = "DefaultEndpointsProtocol=https;" +
            "AccountName=pokobanserver;" +
            "AccountKey=SKhhvBowGwTysyr0qS9ErLPnsTv4b97J77hwaxCmQFS9Fv3ueY2Jye8gUqKwfgidXoGB7hwckVJ259bzyVfylA=="

    companion object {
        val Supervised = "supervised/"
        val Unsupervised = "unsupervised/"
    }

    fun insertLevel(content: ByteArrayInputStream, name: String): String = insert(content, name, "levels")

    fun getLevel(fileName: String): InputStreamReader = getFile("levels", fileName)

    fun getLevels(folder: String): List<String> {
        val container = CloudStorageAccount
                .parse(storageConnectionString)
                .createCloudBlobClient()
                .getContainerReference("levels")

        return container.listBlobs(folder)
                .filter { it is CloudBlockBlob }
                .map { it as CloudBlockBlob }
                .map { it.name }
    }

    fun insertPlay(content: ByteArrayInputStream, name: String): String = insert(content, name, "plays")

    fun getPlay(fileName: String): InputStreamReader = getFile("plays", fileName)

    private fun insert(content: ByteArrayInputStream, name: String, containerName: String): String{
        // Retrieve storage account from connection-string.
        // Create client and get container ref
        val container = CloudStorageAccount
                .parse(storageConnectionString)
                .createCloudBlobClient()
                .getContainerReference(containerName)

        // Create the container if it does not exist.
        container.createIfNotExists()

        val blob = container.getBlockBlobReference(name)
        blob.upload(content, content.available().toLong())
        return blob.uri.toString()
    }

    private fun getFile(containerName: String, fileName: String): InputStreamReader {
        val container = CloudStorageAccount
                .parse(storageConnectionString)
                .createCloudBlobClient()
                .getContainerReference(containerName)

        // Create the container if it does not exist.
        container.createIfNotExists()

        val blob = container.getBlockBlobReference(fileName)
        val outStream = ByteArrayOutputStream()
        blob.download(outStream)

        return InputStreamReader(ByteArrayInputStream(outStream.toByteArray()))
    }

}


