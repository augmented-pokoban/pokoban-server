package server.repositories

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.mongodb.MongoClientOptions
import com.mongodb.MongoClientURI
import org.bson.Document
import org.litote.kmongo.KMongo
import org.litote.kmongo.find
import org.litote.kmongo.insertOne
import org.litote.kmongo.json

class DbRepository(table: String){

    companion object {
        private val options = MongoClientOptions.Builder()
                .sslEnabled(true)
                .sslInvalidHostNameAllowed(true)
        private val client = KMongo.createClient(MongoClientURI(
                "mongodb://pokoban:bo7DAZ60wYGp0uoh2dCb7EEknJ6RwF3UddzpXhRj2wvaNpFI5QlFDdrB4zo3YDjygXVXxGBgPkprumB3yAXRcA==" +
                        "@pokoban.documents.azure.com:10255/?replicaSet=globaldb", options))
        val db = client.getDatabase("pokoban")

        fun getSupervisedLevelsRepo(): DbRepository = DbRepository("supervised")
        fun getUnsupervisedLevelsRepo(): DbRepository = DbRepository("unsupervised")
        fun validateLevelFolder(folder: String): Boolean = listOf("supervised", "unsupervised").contains(folder)
    }

    private val collection = db.getCollection(table)

    /**
     * Insert a single item into the db
     */
    fun insert(item: JsonObject){
        collection.insertOne(item.toString())
    }

    /**
     * Retrieve a list of items using the skip-take interface
     */
    fun paginate(skip: Int, take: Int): List<Document> {
        return collection
                .find()
                .skip(skip)
                .take(take)
                .toList()
    }

    /**
     * Retrieves one element based on the id
     */
    fun one(id: String): JsonObject = Gson().fromJson(collection.find("{_id:${id.json}}").first().toJson())


    /**
     * retrieves the number of elements in the collection
     */
    fun count(): Long = collection.count()
}