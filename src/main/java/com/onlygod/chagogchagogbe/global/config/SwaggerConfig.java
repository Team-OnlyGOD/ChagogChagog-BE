package com.onlygod.chagogchagogbe.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info().title("차곡차곡 API")
                        .description("차곡차곡 API 명세입니다.")
                        .version("v1")
                );
    }
}
