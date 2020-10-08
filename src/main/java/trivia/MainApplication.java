package trivia;

import org.springframework.boot.SpringApplication;

/**
 * not-used => go see the tests GameShould
 */
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication context = new SpringApplication(AppConfig.class);
        context.run(args);
    }

}
