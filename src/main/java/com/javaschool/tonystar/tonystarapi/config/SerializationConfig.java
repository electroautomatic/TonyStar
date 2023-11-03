package com.javaschool.tonystar.tonystarapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerializationConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }
}
