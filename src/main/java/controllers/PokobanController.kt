package controllers

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
    @Produces(MediaType.APPLICATION_JSON)
    fun create(): Pokoban {
        return PokobanService.instance.start()
    }
}