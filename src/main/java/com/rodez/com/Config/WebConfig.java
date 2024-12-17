package com.rodez.com.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT");
//                .allowedHeaders("Content-Type", "Authorization")
//                .exposedHeaders("Authorization")          // Expose ces en-têtes dans la réponse
//                .allowCredentials(true);                  // Autorise l'envoi de cookies/credentials
    }
}