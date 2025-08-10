package com.neurotechR3.inventory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class GlobalCORS {

  @Value("${app.cors.allowed-origins}")
  private String allowedOrigins; // comma-separated list

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        String[] origins = Arrays.stream(allowedOrigins.split(","))
                                 .map(String::trim)
                                 .toArray(String[]::new);
        registry.addMapping("/**")
            .allowedOrigins(origins)            // lock to your origins
            .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(false);           // keep false unless you really need cookies
      }
    };
  }
}
