package com.vasilkov.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Web application.
 *
 * @author Artem Vasilkov
 */
@SpringBootApplication
public class WebApplication {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}