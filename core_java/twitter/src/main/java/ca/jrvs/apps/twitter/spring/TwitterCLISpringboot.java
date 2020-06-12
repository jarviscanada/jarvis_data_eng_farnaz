package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ca.jrvs.apps.twitter")
public class TwitterCLISpringboot implements CommandLineRunner {

    private TwitterCLIApp app;

    @Autowired
    public TwitterCLISpringboot(TwitterCLIApp app) {
        this.app = app;
    }

    public static void main(String[] args) {
        SpringApplication springbootApp = new SpringApplication(TwitterCLISpringboot.class);

        //Turn off web
        springbootApp.setWebApplicationType(WebApplicationType.NONE);
        springbootApp.run(args);
    }


    @Override
    public void run(String... args) throws Exception {
        app.run(args);
    }

}