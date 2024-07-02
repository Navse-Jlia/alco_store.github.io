package com.vasilkov.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class provides configuration for the service.
 * It is annotated with Configuration, indicating that it is a Spring configuration class.
 *
 * @author Artem Vasilkov
 */
@Configuration
public class ServiceConfig {

    /**
     * Configures and provides an ObjectMapper bean.
     * This ObjectMapper is configured to handle Java 8 Date and Time API (JSR-310).
     *
     * @return ObjectMapper bean configured with JavaTimeModule
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

}