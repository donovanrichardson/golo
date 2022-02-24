package com.text.golo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    //https://docs.spring.io/spring-framework/docs/4.3.x/spring-framework-reference/html/cors.html
    //https://stackoverflow.com/questions/47057592/spring-cors-and-angular-not-working-http-status-code-403-error
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH")
                .allowedHeaders("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization")
//                .exposedHeaders("header1", "header2")
                .allowCredentials(true)
//                .maxAge(3600);
        ;
    }
}