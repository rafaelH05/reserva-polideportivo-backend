package com.example.mi_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("https://reservasdeportivas.netlify.app")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true);
    }
}