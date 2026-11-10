package com.example.carmanagement.config;

import java.nio.file.Path;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadLocation = Path.of("uploads/cars").toAbsolutePath().toUri().toString();
        registry.addResourceHandler("/car-images/**").addResourceLocations(uploadLocation);
    }
}
