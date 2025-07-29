// src/main/java/com/yourpackage/config/AppConfig.java
package com.yourpackage.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {

    // IMPORTANT: Configure RestTemplate with a custom User-Agent header
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Replace "MyAppName/1.0" with your actual application name and version
        return builder.defaultHeader("User-Agent", "SplitarRideApp/1.0").build();
    }

    // CORS configuration to allow requests from your frontend
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }
}