package server;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import javax.security.auth.message.config.AuthConfigFactory;

@SpringBootApplication
public class Main extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        new Main()
                .configure(new SpringApplicationBuilder(Main.class))
                .run(args);
    }
}
