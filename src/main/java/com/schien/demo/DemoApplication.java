package com.schien.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.Optional;

//https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-external-config
@PropertySources({
        @PropertySource("classpath:default.properties"),
        @PropertySource(value = "file:${user.home}/.secret.properties", ignoreResourceNotFound = true)
})
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @Bean
    public CommandLineRunner demo(LecturerRepository repository) {
        return (args) -> {

            if (repository.count() == 0) {
                log.info("Filling empty database");
                // save a couple of customers
                repository.save(new Lecturer("Dan", "Schien"));
                repository.save(new Lecturer("Simon", "Lock"));
                repository.save(new Lecturer("Ian", "Holyer"));
            }

            // fetch all customers
            log.info("Existing lecturers:");
            log.info("-------------------------------");
            for (Lecturer lecturer : repository.findAll()) {
                log.info(lecturer.toString());
            }
            log.info("");
        };
    }
}
