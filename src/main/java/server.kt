import services.PokobanService
import java.util.*
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath("/")
class MyApplication : Application() {

    /**
     * @return a non-empty collection with classes, that must be included in the published JAX-RS application
     */
    override fun getClasses(): Set<Class<*>> {
        return HashSet<Class<*>>(listOf(PokobanService::class.java))
    }
}