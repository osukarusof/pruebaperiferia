package com.periferia.mutant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MutantDto {

    @NotNull(message = "El campo dna no puede ser nulo")
    @NotBlank(message = "El campo dna no puede estar vacio")
    private String[] dna;
}