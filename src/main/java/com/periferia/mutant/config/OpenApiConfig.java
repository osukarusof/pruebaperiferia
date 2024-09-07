package com.periferia.mutant.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Prueba Periferia",
                version = "1.0.0",
                description = "Esta es la prueba por parte de compañía de periferia de Mutantes"
        )
)
public class OpenApiConfig {
}
