package controllers

import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson
import exceptions.ImpossibleActionException
import model.PokobanAction
import services.PokobanService
import java.io.File
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

operator fun Number.plusAssign(d: Double) { this.toDouble() + d }

@Path("/")
class PokobanController {

	/**
	 * Returns all games
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	fun index(): String {
		return Gson().toJson(PokobanService.instance.all().map {
			jsonObject(
					"state" to Gson().toJsonTree(it.getState()),
					"gameID" to it.id
			)
		})
	}

	/**
	 * Returns the state of game id
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	fun show(@PathParam("id") id: String): String {
		val game = PokobanService.instance.get(id)
		return jsonObject(
				"state" to Gson().toJsonTree(game.getState()),
				"gameID" to game.id
		).toString()
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

		var success = true
		var (reward, game) = try {
			PokobanService.instance.transition(id, pokobanAction)
		} catch (e: ImpossibleActionException) {
			success = false
			Pair(-0.5, PokobanService.instance.get(id)) // return the same game state
		}

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
		val game = PokobanService.instance.remove(id)

		if (!filename.isEmpty() && game != null) {
			// save the game before removing
			val filename = filename + "_" + game.id
			File(javaClass.classLoader.getResource("saves/" + filename).toURI()).writeText(
					jsonObject(
							"id" to game.id,
							"level" to game.level.filename,
							"initial" to Gson().toJsonTree(game.getState())
					).toString()
			)
		}

		return jsonObject("success" to true).toString()
	}
}