package com.periferia.mutant.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                        .info(new Info()
                                .title("Prueba Periferia")
                                .version("1.0.0")
                                .description("Esta es la prueba por parte de compañía de periferia de Mutantes")
                                .license(
                                        new License().name("Apache 2.0").url("http://springdoc.org")
                                )
                        )
                        .externalDocs(new ExternalDocumentation()
                                .description("Documentación del Proyecto")
                                .url("https://github.com/osukarusof"));
        }
}
