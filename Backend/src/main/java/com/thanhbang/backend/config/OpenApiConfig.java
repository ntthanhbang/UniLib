package com.thanhbang.backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customizeOpenAPI() {
    final String securitySchemeName = "BearerAuth";
    return new OpenAPI()
        .info(new Info()
            .title("UniLib API Docs")
            .version("0.1.0 Super Early Alpha")
            .description("This is the API documentation for the UniLib application."))
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName)) // Add security to all endpoints
        .components(new Components()
            .addSecuritySchemes(securitySchemeName,
                new SecurityScheme()
                    .name(securitySchemeName)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT"))); // Bearer format
  }
}
