package transfo;

import org.springframework.boot.SpringApplication;

public class MainApplication {

    public static void main(String[] args) {
        SpringApplication context = new SpringApplication(AppConfig.class);
        context.run(args);
    }

}
