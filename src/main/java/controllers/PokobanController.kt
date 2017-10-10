package controllers

import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson
import com.google.gson.JsonObject
import model.PokobanAction
import services.PokobanService
import java.io.File
import javax.ws.rs.*
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
	fun index(): String {
		val gameFiles = File(javaClass.classLoader.getResource("saves/").toURI()).listFiles()
		return Gson().toJson(
				gameFiles.map { Gson().fromJson<JsonObject>(File(it.toURI()).readText()) }
		)
	}

	/**
	 * Returns the state of game id
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	fun show(@PathParam("id") id: String): String {
		return File(javaClass.classLoader.getResource("saves/$id.json").toURI()).readText()
	}

	/**
	 * Creates a new Pokoban game instance
	 */
	@POST
	@Path("{filename}")
	@Produces(MediaType.APPLICATION_JSON)
	fun create(@PathParam("filename") filename: String): String {
		val game = PokobanService.instance.start(filename + ".lvl")
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
				@QueryParam("filename") filename: String): String {
		val (game, transitions) = PokobanService.instance.remove(id)

		if (!filename.isEmpty() && game != null) {
			// store JSON object for a full game
			File(javaClass.classLoader.getResource("saves/" + game.id + ".json").toURI()).writeText(
					jsonObject(
							"id" to game.id,
							"level" to game.level.filename,
							"initial" to Gson().toJsonTree(game.getState()),
							"transitions" to Gson().toJsonTree(transitions!!.reverse())
					).toString()
			)
		}

		return jsonObject("success" to true).toString()
	}
}