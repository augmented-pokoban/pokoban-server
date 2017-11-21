package server.repositories

import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.toJsonArray
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.litote.kmongo.*

class Repository(table: String){
    companion object {
        private val client = KMongo.createClient()
        val db = client.getDatabase("test")
    }

    private val collection = db.getCollection(table)

    fun insert(item: JsonObject){
        collection.insertOne(item.toString())
    }

    fun one(id: String): JsonObject = Gson().fromJson(collection.find(filter = id).first().toJson())

    fun paginate(skip: Int, take: Int): JsonArray{
        return collection
                .find()
                .skip(skip)
                .take(take)
                .toJsonArray()
    }

    fun count(): Long = collection.count()
}