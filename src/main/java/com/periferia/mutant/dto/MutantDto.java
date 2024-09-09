package com.periferia.mutant.dto;


import com.periferia.mutant.valid.ValidDna;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Este es el body para enviar la cadena de ADN")
public class MutantDto {

    @NotNull(message = "This field is required")
    @ValidDna(message = "DNA array must not be empty and all rows must have the same length")
    @Schema(description = "Contendrá la cadena de ADN para ser validada", example = "[\"ATGCGA\", \"CAGTGC\", \"TTATGT\", \"AGAAGG\", \"CCCCTA\", \"TCACTG\"]")
    private String[] adn;
}