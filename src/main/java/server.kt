import controllers.LevelController
import controllers.PokobanController
import java.util.*
import javax.ws.rs.ApplicationPath
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter
import javax.ws.rs.core.Application
import javax.ws.rs.ext.Provider


@ApplicationPath("/api")
class PokobanServer : Application() {

	object constants {
		val UPLOAD_PATH = "../../../data/"
	}

	/**
	 * @return a non-empty collection with classes, that must be included in the published JAX-RS application
	 */
	override fun getClasses(): Set<Class<*>> {
		return HashSet<Class<*>>(listOf(
				CORSFilter::class.java,
				PokobanController::class.java,
				LevelController::class.java
		))
	}
}

@Provider
class CORSFilter : ContainerResponseFilter {
	override fun filter(requestContext: ContainerRequestContext, cres: ContainerResponseContext) {
		cres.headers.add("Access-Control-Allow-Origin", "*")
		cres.headers.add("Access-Control-Allow-Headers", "X-XSRF-TOKEN, origin, content-type, accept, authorization")
		cres.headers.add("Access-Control-Allow-Credentials", "true")
		cres.headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
		cres.headers.add("Access-Control-Max-Age", "1209600")
	}
}