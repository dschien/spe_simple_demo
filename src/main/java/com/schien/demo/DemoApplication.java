package com.schien.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @Bean
    public CommandLineRunner demo(LecturerRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Lecturer("Dan", "Schien"));
            repository.save(new Lecturer("Simon", "Lock"));
            repository.save(new Lecturer("Ian", "Holyer"));


            // fetch all customers
            log.info("Lecturer found with findAll():");
            log.info("-------------------------------");
            for (Lecturer lecturer : repository.findAll()) {
                log.info(lecturer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Optional<Lecturer> lecturer = repository.findById(1L);
            if (lecturer.isPresent()) {
                log.info("Lecturer found with findOne(1L):");
                log.info("--------------------------------");
                log.info(lecturer.get().toString());
                log.info("");
            }

            // fetch customers by last name
            log.info("Lecturer found with findByLastName('Schien'):");
            log.info("--------------------------------------------");
            for (Lecturer dan : repository.findByLastName("Schien")) {
                log.info(dan.toString());
            }
            log.info("");
        };
    }
}
