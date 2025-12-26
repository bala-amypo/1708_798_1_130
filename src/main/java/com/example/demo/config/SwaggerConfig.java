package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // 🔐 JWT Bearer Security Scheme (Authorize button)
        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        Server prodServer = new Server()
                .url("https://9233.pro604cr.amypo.ai")
                .description("Production Server");

        return new OpenAPI()
                .servers(List.of(prodServer))
                .components(
                        new Components()
                                .addSecuritySchemes("bearerAuth", bearerAuth)
                );

    }
}
