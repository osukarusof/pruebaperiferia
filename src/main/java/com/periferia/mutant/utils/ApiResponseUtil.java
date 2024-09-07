package com.periferia.mutant.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Este es objeto de respuesta de los servicios")
public class ApiResponseUtil<T> {

    @Schema(description = "Contendrá el mensaje a mostrar", example = "Transacción exitosa")
    private String message;

    @Schema(description = "Contendrá el código a mostrar", example = "200")
    private int status;

    @Schema(description = "Contendrá el código, un objeto o un array como respuesta para mostrar los datos")
    private T data;

    @Schema(description = "Contendrá el código, un objeto o un array como respuesta para mostrar los erores")
    private T fieldErrors;
}
