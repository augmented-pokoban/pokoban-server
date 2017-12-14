package server.repositories

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.mongodb.*
import com.mongodb.client.model.UpdateOptions
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.bson.Document
import org.litote.kmongo.*
import org.litote.kmongo.util.KMongoUtil.EMPTY_JSON

class DbRepository(table: String) {

    companion object {
        private val options = MongoClientOptions.Builder()
                .sslEnabled(true)
                .sslInvalidHostNameAllowed(true)
                .maxConnectionIdleTime(1200000) // 2 minute

        private val client = KMongo.createClient(MongoClientURI(
                "mongodb://pokoban:bo7DAZ60wYGp0uoh2dCb7EEknJ6RwF3UddzpXhRj2wvaNpFI5QlFDdrB4zo3YDjygXVXxGBgPkprumB3yAXRcA==" +
                        "@pokoban.documents.azure.com:10255/?replicaSet=globaldb", options))

        val db = client.getDatabase("pokoban")!!

        fun getSupervisedLevelsRepo(): DbRepository = DbRepository("supervised")
        fun getUnsupervisedLevelsRepo(): DbRepository = DbRepository("unsupervised")
        fun validateLevelFolder(folder: String): Boolean = listOf("supervised", "unsupervised").contains(folder)
        fun validatePokobanFolder(folder: String): Boolean = listOf("replays", "saves").contains(folder)
    }

    private val collection = db.getCollection(table)

    /**
     * Insert a single item into the db
     */
    fun insert(item: JsonObject, upsert: Boolean = true, retry: Int = 1): Boolean {
        try {
            if (upsert) {
                val options = UpdateOptions()
                options.upsert(upsert)
                collection.replaceOneById(item["_id"].asString, Document.parse(item.toString()), options)
            } else {
                collection.insertOne(Document.parse(item.toString()))
            }
        } catch (e: MongoCommandException) {
            if (!e.message!!.contains("Request rate is large") || retry > 6) throw e
            Thread.sleep((1000 * retry).toLong()) // wait and retry
            return insert(item, upsert, retry + 1)
        } catch (e: MongoSocketReadException) {
            if (retry > 6) throw e
            Thread.sleep(5000) // wait and try
            return insert(item, upsert, retry + 1)
        }
        return true
    }

    /**
     * Retrieve a list of items using the skip-take interface
     */
    fun paginate(skip: Int, take: Int, sortField: String = "_id", sortOrder: Int = 1, retry: Int = 1, find: String = EMPTY_JSON): List<Document> {
        try {
            val sort = "{$sortField: $sortOrder}"
            val findJson = if(find == "") EMPTY_JSON else find

            return collection
                    .find(findJson)
                    .sort(sort)
                    .skip(skip)
                    .limit(take)
                    .toList()
        } catch (e: MongoQueryException) {
            if (!e.message!!.contains("Request rate is large") || retry > 3) throw e
            Thread.sleep((1000 * retry).toLong()) // wait and retry
            return paginate(skip, take, sortField, sortOrder, retry + 1)
        } catch (e: MongoSocketReadException) {
            if (retry > 6) throw e
            Thread.sleep(5000) // wait and try
            return paginate(skip, take, sortField, sortOrder, retry + 1)
        }
    }

    /**
     * Retrieves oneMeta element based on the id
     */
    fun one(id: String): JsonObject = Gson().fromJson(collection.find("{_id:${id.json}}").first().toJson())


    /**
     * retrieves the number of elements in the collection
     */
    fun count(): Long = collection.count()
}