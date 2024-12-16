package com.rodez.com.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                         // Toutes les routes sont concernées
                .allowedOrigins("http://localhost:4200")   // Autorise uniquement cette origine
                .allowedMethods("GET", "POST", "PUT");      // Limite les méthodes autorisées
//                .allowedHeaders("Content-Type", "Authorization") // Autorise ces en-têtes spécifiques
//                .exposedHeaders("Authorization")          // Expose ces en-têtes dans la réponse
//                .allowCredentials(true);                  // Autorise l'envoi de cookies/credentials
    }
}