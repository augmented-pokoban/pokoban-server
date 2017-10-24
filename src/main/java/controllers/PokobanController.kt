package controllers

import PokobanServer.constants.UPLOAD_PATH
import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson
import com.google.gson.JsonObject
import model.PokobanAction
import services.PokobanService
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.servlet.ServletContext
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType

operator fun Number.plusAssign(d: Double) {
    this.toDouble() + d
}

@Path("/")
class PokobanController {

    /**
     * Returns all finished games
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun index(@DefaultValue("saves") @QueryParam("folder") folder: String,
              @Context context: ServletContext): String {
        val gameFiles = File(context.getRealPath(UPLOAD_PATH + folder)).listFiles()
        return Gson().toJson(gameFiles.map { Gson().fromJson<JsonObject>(File(it.toURI()).readText()) })
    }

    /**
     * Returns the state of game id
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun show(@PathParam("id") id: String,
             @Context context: ServletContext): String {
        return File(context.getRealPath(UPLOAD_PATH + "saves/$id.json")).readText()
    }

    /**
     * Creates a new Pokoban game instance
     */
    @POST
    @Path("{filename}")
    @Produces(MediaType.APPLICATION_JSON)
    fun create(@PathParam("filename") filename: String,
               @Context context: ServletContext): String {
        val game = PokobanService.instance.start(
                context.getRealPath(UPLOAD_PATH + "levels") + "/$filename.lvl"
        )
        return jsonObject(
                "state" to Gson().toJsonTree(game.getState()),
                "map" to game.level.mapfile,
                "gameID" to game.id
        ).toString()
    }

    /**
     * Takes given action in given game
     */
    @POST
    @Path("{id}/{action}")
    @Produces(MediaType.APPLICATION_JSON)
    fun transition(@PathParam("id") id: String,
                   @PathParam("action") action: String): String {

        val pokobanAction = PokobanAction.valueOf(action.toUpperCase().replace("-", "_"))
        var (success, reward, game) = PokobanService.instance.transition(id, pokobanAction)

        val done = game.isDone()
        if (done) reward += 1.0

        return jsonObject(
                "state" to Gson().toJsonTree(game.getState()),
                "action" to pokobanAction.toString(),
                "reward" to reward,
                "done" to done,
                "success" to success
        ).toString()
    }

    /**
     * Deletes a Pokoban game instance
     */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun destroy(@PathParam("id") id: String,
                @DefaultValue("false") @QueryParam("store") store: Boolean,
                @DefaultValue("false") @QueryParam("is_planner") isPlanner: Boolean,
                @DefaultValue("") @QueryParam("description") description: String,
                @Context context: ServletContext): String {
        val (initalState, game, transitions) = PokobanService.instance.remove(id)

        if (store && initalState != null && game != null && transitions != null) {

            val folder = when (isPlanner) {
                true -> "saves"
                false -> "replays"
            }
            val storePath = Paths.get(context.getRealPath(UPLOAD_PATH) + "/$folder/" + game.id + ".json")

            // store JSON object for a full game
            Files.write(
                    storePath,
                    jsonObject(
                            "id" to game.id,
                            "description" to description,
                            "date" to Date().time,
                            "level" to game.level.filename.replace(".lvl", ""),
                            "initial" to Gson().toJsonTree(initalState),
                            "transitions" to Gson().toJsonTree(transitions)
                    ).toString().toByteArray()
            )
        }

        return jsonObject("success" to true).toString()
    }
}