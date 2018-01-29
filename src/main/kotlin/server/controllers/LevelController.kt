package server.controllers

import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson
import server.model.Pokoban
import server.model.PokobanTransition
import server.repositories.DbRepository
import server.repositories.FileRepository
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
              @DefaultValue("") @QueryParam("last_id") lastID: String,
              @Context context: ServletContext): String {

        if (!DbRepository.validateLevelFolder(folder)) throw BadRequestException("Folder: $folder not found")
        val find = if (lastID == "") lastID else "{_id : {\$gt : '$lastID'}, difficulty: 'supereasy'}"
        val repo = DbRepository(folder)
        val levels = repo.paginate(skip, limit, find = find) //repo.paginate(skip, limit, find=find)
        val total = repo.count()

        return jsonObject(
                "data" to Gson().toJsonTree(levels),
                "total" to total
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

        if (!DbRepository.validateLevelFolder(folder)) throw BadRequestException("Folder: $folder not found")

        val levelData = DbRepository(folder).one(id)

        val levelFile = FileRepository().getLevel(levelData["relativePath"].asString)
        val level = LevelService.instance.loadLevel(levelFile, levelData["_id"].asString)
        val state = Pokoban(id, level)
        return jsonObject(
                "initial" to Gson().toJsonTree(state.getState()),
                "transitions" to Gson().toJsonTree(emptyList<PokobanTransition>())
        ).toString()
    }

    @GET
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    fun update(): String {
        val result = listOf(
                LevelService.instance.updateMetaData(
                        FileRepository.Unsupervised,
                        DbRepository.getUnsupervisedLevelsRepo()),

                LevelService.instance.updateMetaData(
                        FileRepository.Supervised,
                        DbRepository.getSupervisedLevelsRepo()))

        return Gson().toJson(result).toString()
    }

    /**
     * Returns a level state by input string
     */
    @POST
    @Path("state")
    @Produces(MediaType.APPLICATION_JSON)
    fun state(@Context context: ServletContext,
              body: String): String {

        val level = LevelService.instance.loadLevel(body, "level.lvl")
        val state = Pokoban("level.lvl", level)
        return jsonObject(
                "initial" to Gson().toJsonTree(state.getState()),
                "transitions" to Gson().toJsonTree(emptyList<PokobanTransition>())
        ).toString()
    }
}