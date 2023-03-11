package com.example.ruleEngine.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private String title = "rule-engine";
    private String description = "rule-engine api";
    private String version = "v0.0.1";

    @Bean
    public OpenAPI heroOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(title)
                        .description(description)
                        .version(version));
    }
}