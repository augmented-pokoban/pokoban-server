package controllers

import PokobanServer.constants.UPLOAD_PATH
import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson
import com.google.gson.JsonObject
import model.Pokoban
import model.PokobanAction
import org.springframework.stereotype.Component
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

@Component
@Path("/pokoban")
public class PokobanController {

    /**
     * Returns getPage finished games
     */
    @GET
    @Path("{folder}")
    @Produces(MediaType.APPLICATION_JSON)
    fun index(@PathParam("folder") folder: String,
              @DefaultValue("0") @QueryParam("skip") skip: Int,
              @DefaultValue("1000") @QueryParam("limit") limit: Int,
              @Context context: ServletContext): String {

        var gameFiles = File(context.getRealPath(UPLOAD_PATH + folder)).listFiles()

        // slice files list
        gameFiles = if (gameFiles.size < limit) {
            gameFiles.sliceArray(skip until gameFiles.size)
        } else {
            gameFiles.sliceArray(skip..limit)
        }

        return Gson().toJson(gameFiles.map {
            val game = Gson().fromJson<JsonObject>(File(it.toURI()).readText())
            jsonObject(
                    "id" to game["id"],
                    "description" to game["description"],
                    "date" to game["date"],
                    "level" to game["level"]
            )
        })
    }

    /**
     * Returns getPage running games
     */
    @GET
    @Path("running")
    @Produces(MediaType.APPLICATION_JSON)
    fun index(@Context context: ServletContext): String {
        return Gson().toJson(PokobanService.instance.all().map { it.id })
    }

    /**
     * Returns the state of game id
     */
    @GET
    @Path("{folder}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun show(@PathParam("folder") folder: String,
             @PathParam("id") id: String,
             @Context context: ServletContext): String {
        return File(context.getRealPath("$UPLOAD_PATH$folder/$id.json")).readText()
    }

    /**
     * Creates a new Pokoban game instance
     */
    @POST
    @Path("{folder}/{filename}")
    @Produces(MediaType.APPLICATION_JSON)
    fun create(@PathParam("folder") folder: String,
               @PathParam("filename") filename: String,
               @Context context: ServletContext): String {

        val game = PokobanService.instance.start(
                context.getRealPath(UPLOAD_PATH + "levels/$folder") + "/$filename.lvl"
        )
        return jsonObject(
                "state" to Gson().toJsonTree(game.getState()),
                "map" to game.level.mapfile,
                "gameID" to game.id
        ).toString()
    }

    /**
     * Copies an existing Pokoban game instance into a new game
     */
    @POST
    @Path("{id}/action/copy")
    @Produces(MediaType.APPLICATION_JSON)
    fun copy(@PathParam("id") id: String): String {
        val game = PokobanService.instance.copy(id)

        return jsonObject(
                "gameID" to game.id
        ).toString()
    }

    /**
     * Takes given action in given game
     */
    @PUT
    @Path("{id}/{action}")
    @Produces(MediaType.APPLICATION_JSON)
    fun transition(@PathParam("id") id: String,
                   @PathParam("action") action: String): String {

        val pokobanAction = PokobanAction.valueOf(action.toUpperCase().replace("-", "_"))
        var (success, reward, game) = PokobanService.instance.transition(id, pokobanAction)

        val done = game.isDone()
        if (done) reward = 10.0

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

            val folder = if (isPlanner) "saves" else "replays"
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