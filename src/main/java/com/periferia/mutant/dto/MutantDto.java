package com.periferia.mutant.dto;


import com.periferia.mutant.valid.ValidDna;
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

    @NotNull(message = "This field is required")
    @ValidDna(message = "DNA array must not be empty and all rows must have the same length")
    private String[] dna;
}