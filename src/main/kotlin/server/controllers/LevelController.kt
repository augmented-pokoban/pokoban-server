package server.controllers

import com.github.salomonbrys.kotson.get
import server.PokobanServer.constants.UPLOAD_PATH
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson
import server.model.Pokoban
import server.model.PokobanTransition
import server.repositories.Repository
import server.services.LevelService
import javax.servlet.ServletContext
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType

@Path("/levels")
class LevelController {

    /**
     * Returns all level files
     */
    @GET
    @Path("{folder}")
    @Produces(MediaType.APPLICATION_JSON)
    fun index(@PathParam("folder") folder: String,
              @DefaultValue("0") @QueryParam("skip") skip: Int,
              @DefaultValue("1000") @QueryParam("limit") limit: Int,
              @Context context: ServletContext): String {

        val repo = Repository(folder)
        val levels = repo
                .paginate(skip, limit)
        val total = repo.count()

        return jsonObject(
                "data" to Gson().toJsonTree(levels.map {
                    jsonObject(
                            "fileRef" to it["fileRef"],
                            "height" to it["height"],
                            "width" to it["width"],
                            "countWalls" to it["countWalls"],
                            "countBoxes" to it["countBoxes"],
                            "countGoals" to it["countGoals"]
                            )
                }),
                "total" to total
        ).toString()
    }

    /**
     * Returns a level file by name
     */
    @GET
    @Path("{folder}/{filename}")
    @Produces(MediaType.APPLICATION_JSON)
    fun show(@PathParam("folder") folder: String,
             @PathParam("filename") filename: String,
             @Context context: ServletContext): String {

        //TODO: Obsolete? This is in the meta-data, file should be downloaded from blob storage

        val levelsPath = context.getRealPath(UPLOAD_PATH + "levels/$folder")
        val level = LevelService.instance.loadLevel("$levelsPath/$filename.lvl")
        return jsonObject(
                "filename" to filename,
                "contents" to level.mapfile,
                "width" to level.width,
                "height" to level.height
        ).toString()
    }

    /**
     * Returns a level state by name
     */
    @GET
    @Path("{folder}/{id}/state")
    @Produces(MediaType.APPLICATION_JSON)
    fun state(@PathParam("folder") folder: String,
              @PathParam("id") id: String,
              @Context context: ServletContext): String {

        val levelData = Repository(folder).one(id)

        //TODO: Get level from blob storage
        val levelPath = "url_to_blob_storage"
        val level = LevelService.instance.loadLevel(levelPath)
        val state = Pokoban(id, level)
        return jsonObject(
                "initial" to Gson().toJsonTree(state.getState()),
                "transitions" to Gson().toJsonTree(emptyList<PokobanTransition>())
        ).toString()
    }
}