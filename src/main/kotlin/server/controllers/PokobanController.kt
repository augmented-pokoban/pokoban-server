package server.controllers

import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import server.model.PokobanAction
import server.repositories.DbRepository
import server.repositories.FileRepository
import server.services.PokobanService
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import javax.servlet.ServletContext
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


operator fun Number.plusAssign(d: Double) {
    this.toDouble() + d
}

@Path("/pokoban")
class PokobanController {

    /**
     * Returns getPage finished games
     */
    @GET
    @Path("{folder}")
    @Produces(MediaType.APPLICATION_JSON)
    fun index(@PathParam("folder") folder: String,
              @DefaultValue("0") @QueryParam("skip") skip: Int,
              @DefaultValue("1000") @QueryParam("limit") limit: Int,
              @DefaultValue("") @QueryParam("last_id") lastID: String,
              @DefaultValue("desc") @QueryParam("order") order: String): String {

        if (!DbRepository.validatePokobanFolder(folder)) throw BadRequestException("Folder: $folder not found.")
        val find = if (lastID == "") lastID else "{_id : {\$gt : '$lastID'}}"
        val sortOrder = if (order == "asc") 1 else -1

        val repo = DbRepository(folder)

        val total = repo.count()
        val gameFiles = repo.paginate(skip, limit, "date", sortOrder, find = find)

        return jsonObject(
                "data" to Gson().toJsonTree(gameFiles),
                "total" to total
        ).toString()
    }

    /**
     * Returns getPage running games
     */
    @GET
    @Path("running")
    @Produces(MediaType.APPLICATION_JSON)
    fun index(): String = Gson().toJson(PokobanService.instance.all().map { it.id })

    /**
     * Returns the state of game id
     */
    @GET
    @Path("{folder}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun show(@PathParam("folder") folder: String,
             @PathParam("id") id: String): String = DbRepository(folder).one(id).toString()

    /**
     * Creates a new Pokoban game instance
     */
    @POST
    @Path("{filename}")
    @Produces(MediaType.APPLICATION_JSON)
    fun create(@PathParam("filename") filename: String): String {

        val game = PokobanService.instance.start(filename.replace("_", "/"))

        println("Created game with id ${game.id} from level $filename.")

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
                   @PathParam("action") action: String) : Response {

        val pokobanAction = PokobanAction.valueOf(action.toUpperCase().replace("-", "_"))

        try {
            var (success, reward, game) = PokobanService.instance.transition(id, pokobanAction)

            val done = game.isDone()
            if (done) reward = 10.0

            val json = jsonObject(
                    "state" to Gson().toJsonTree(game.getState()),
                    "action" to pokobanAction.toString(),
                    "reward" to reward,
                    "done" to done,
                    "success" to success
            ).toString()

            return Response.ok(json).build()
        } catch (e: NoSuchElementException) {
            println("Game with id $id does not exist.\nCurrently running games:\n${PokobanService.instance.all().map { it.id }}")
            return Response.status(Response.Status.BAD_REQUEST).build()
        }
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

        println("Destroying game with id $id")

        // execute the actual request concurrently
        launch {
            val (initalState, game, transitions) = PokobanService.instance.remove(id)

            if (store && initalState != null && game != null && transitions != null) {

                val folder = if (isPlanner) "saves" else "replays"

                // Save transition in file system, get path back
                val fileName = game.id + ".zip"

                val jsonToBeZipped = jsonObject(
                        "id" to game.id,
                        "description" to description,
                        "level" to game.level.filename.replace(".lvl", ""),
                        "initial" to Gson().toJsonTree(initalState),
                        "transitions" to Gson().toJsonTree(transitions)
                ).toString().toByteArray()

                val byteStream = ByteOutputStream()
                ZipOutputStream(byteStream).use {
                    val entry = ZipEntry(game.id + ".json")
                    it.putNextEntry(entry)
                    it.write(jsonToBeZipped)
                    it.closeEntry()
                }

                val lookupUrl = FileRepository().insertPlay(byteStream.newInputStream(), fileName)

                // Write meta-data to db
                DbRepository(folder)
                        .insert(jsonObject(
                                "_id" to game.id,
                                "description" to description,
                                "date" to Date().time,
                                "level" to game.level.filename.replace(".lvl", ""),
                                "steps" to transitions.size,
                                "fileRef" to lookupUrl))

            }
        }

        return jsonObject("success" to true).toString()
    }
}