import controllers.PokobanController
import java.util.*
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath("/api")
class PokobanServer : Application() {

	/**
	 * @return a non-empty collection with classes, that must be included in the published JAX-RS application
	 */
	override fun getClasses(): Set<Class<*>> {
		return HashSet<Class<*>>(listOf(PokobanController::class.java))
	}
}