package server.controllers

import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson
import com.google.gson.JsonObject
import server.repositories.DbRepository
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/encoding")
class EncodingController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun index(@DefaultValue("0") @QueryParam("skip") skip: Int,
              @DefaultValue("1000") @QueryParam("limit") limit: Int): String {

        val db = DbRepository("enoding")

        val total = db.count()
        val encodings = db.paginate(0, 1000)

        return jsonObject(
                "data" to Gson().toJsonTree(encodings),
                "total" to total
        ).toString()
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun create(content: String): String {

        val json = Gson().fromJson<JsonObject>(content)
        val db = DbRepository("enoding")

        db.insert(json, upsert=false)

        return jsonObject(
                "success" to true
        ).toString()
    }
}