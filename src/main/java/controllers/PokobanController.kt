package controllers

import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.JsonObject
import model.Pokoban
import services.PokobanService
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/")
class PokobanController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	fun index(): List<Pokoban> {
		return PokobanService.instance.all()
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	fun show(@PathParam("id") id: String): Pokoban {
		return PokobanService.instance.get(id)
	}

	/**
	 * Creates a new Pokoban game instance
	 */
	@POST
	@Path("{filename}")
	@Produces(MediaType.APPLICATION_JSON)
	fun create(@PathParam("filename") filename: String): JsonObject {
		val game = PokobanService.instance.start(filename)

		val obj = jsonObject(
				"state" to game.getState(),
				"map" to game.level.mapfile,
				"gameID" to game.id
		)

		return obj
	}
}