package services

import model.Pokoban
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/")
class PokobanService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun index(): List<Pokoban> {
        return listOf<Pokoban>(Pokoban())
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun show(@PathParam("id") id: String): Pokoban {
        return Pokoban()
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun create(): Pokoban {
        return Pokoban()
    }
}