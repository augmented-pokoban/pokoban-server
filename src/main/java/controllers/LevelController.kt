package controllers

import PokobanServer.constants.UPLOAD_PATH
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson
import services.LevelService
import java.io.File
import javax.servlet.ServletContext
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType

@Path("/levels")
class LevelController {

	/**
	 * Returns all level files
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	fun index(@Context context: ServletContext): String {

		val levelsPath = context.getRealPath(UPLOAD_PATH + "levels")
		val levelFiles = File(levelsPath).listFiles()

		val levels = levelFiles.map {
			val level = LevelService.instance.loadLevel(it.absolutePath)
			jsonObject(
					"filename" to it.name.replace(".lvl", ""),
					"contents" to level.mapfile,
					"width" to level.width,
					"height" to level.height
			)
		}

		return Gson().toJson(levels)
	}

	/**
	 * Returns a level file by name
	 */
	@GET
	@Path("{filename}")
	@Produces(MediaType.APPLICATION_JSON)
	fun show(@PathParam("filename") filename: String,
			 @Context context: ServletContext): String {
		val levelsPath = context.getRealPath(UPLOAD_PATH + "levels")
		val level = LevelService.instance.loadLevel(levelsPath + filename + ".lvl")
		return jsonObject(
				"filename" to filename,
				"contents" to level.mapfile,
				"width" to level.width,
				"height" to level.height
		).toString()
	}
}