package uk.co.edbrook.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AsyncClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncClientApplication.class, args);
    }

}
