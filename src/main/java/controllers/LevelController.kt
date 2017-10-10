package controllers

import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson
import services.LevelService
import java.io.File
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/levels")
class LevelController {

	/**
	 * Returns all level files
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	fun index(): String {
		val levelFiles = File(javaClass.classLoader.getResource("levels").toURI()).listFiles()

		val levels = levelFiles.map {
			val level = LevelService.instance.loadLevel(it.name)
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
	fun show(@PathParam("filename") filename: String): String {
		val level = LevelService.instance.loadLevel(filename + ".lvl")
		return jsonObject(
				"filename" to filename,
				"contents" to level.mapfile,
				"width" to level.width,
				"height" to level.height
		).toString()
	}
}