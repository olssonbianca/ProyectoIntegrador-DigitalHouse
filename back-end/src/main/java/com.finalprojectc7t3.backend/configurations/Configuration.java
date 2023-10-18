package com.finalprojectc7t3.backend.configurations;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@org.springframework.context.annotation.Configuration
@EnableWebMvc
public class Configuration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Especifica el origen permitido
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Especifica los métodos permitidos
                .allowedHeaders("*"); // Especifica los encabezados permitidos
    }

}
