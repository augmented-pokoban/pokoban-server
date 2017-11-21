import controllers.LevelController;
import controllers.PokobanController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(
                PokobanController.class
        );
    }
}