package controllers

import PokobanServer.constants.UPLOAD_PATH
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson
import model.Pokoban
import model.PokobanTransition
import org.springframework.stereotype.Component
import services.LevelService
import java.io.File
import javax.servlet.ServletContext
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType

@Component
@Path("/levels")
public class LevelController {

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

        val levelsPath = context.getRealPath(UPLOAD_PATH + "levels/$folder")
        var levelFiles = File(levelsPath).listFiles()
        val total = levelFiles.size

        // slice files list
        levelFiles = if (total < limit) {
            levelFiles.sliceArray(skip until total)
        }
        else {
            levelFiles.sliceArray(skip..limit)
        }

        return jsonObject(
                "data" to Gson().toJsonTree(levelFiles.map { it.name.replace(".lvl", "") }),
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
    @Path("{folder}/{filename}/state")
    @Produces(MediaType.APPLICATION_JSON)
    fun state(@PathParam("folder") folder: String,
              @PathParam("filename") filename: String,
              @Context context: ServletContext): String {
        val levelsPath = context.getRealPath(UPLOAD_PATH + "levels/$folder")
        val level = LevelService.instance.loadLevel("$levelsPath/$filename.lvl")
        val state = Pokoban(filename, level)
        return jsonObject(
                "initial" to Gson().toJsonTree(state.getState()),
                "transitions" to Gson().toJsonTree(emptyList<PokobanTransition>())
        ).toString()
    }
}