package com.vasilkov.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * This class serves as the entry point for the service application.
 * It is annotated with SpringBootApplication, indicating that it is a Spring Boot application.
 *
 * @author Artem Vasilkov
 */
@EnableCaching
@SpringBootApplication
public class ServiceApplication {

    /**
     * The main method to start the service application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
