package com.oss.socialmedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all paths
                        .allowedOrigins("http://localhost:8080", "http://localhost/8080/users") // Replace with allowed origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed HTTP methods
                        .allowedHeaders("Content-Type", "Authorization", "Accept") // Specify allowed headers
                        .exposedHeaders("Authorization") // Specify headers to expose to the client
                        .allowCredentials(true) // Allow credentials such as cookies
                        .maxAge(3600); // Cache the CORS configuration for 3600 seconds (1 hour)
            }
        };
    }
}


