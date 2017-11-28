package server;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.message.config.AuthConfigFactory;

@Controller
@SpringBootApplication
public class Main extends SpringBootServletInitializer {

    @RequestMapping(value = "/**/{[path:[^\\.]*}")
    public String redirectUi() {
        return "forward:/index.html";
    }

    public static void main(String[] args) throws Exception {
        if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        new Main()
                .configure(new SpringApplicationBuilder(Main.class))
                .run(args);
    }
}
