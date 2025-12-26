package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        Server production = new Server()
                .url("https://9233.pro604cr.amypo.ai")
                .description("Production");

        Server local = new Server()
                .url("http://localhost:9001")
                .description("Local");

        return new OpenAPI()
                .info(new Info()
                        .title("Warranty Claim & Fraud Detection API")
                        .description("Spring Boot + JWT + TestNG")
                        .version("1.0")
                )
                .servers(List.of(production, local))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", bearerScheme)
                )
                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth")
                );
    }
}
