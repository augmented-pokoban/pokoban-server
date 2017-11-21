package server;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import server.controllers.LevelController;
import server.controllers.PokobanController;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(PokobanServer.class);
        register(PokobanController.class);
        register(LevelController.class);
    }
}