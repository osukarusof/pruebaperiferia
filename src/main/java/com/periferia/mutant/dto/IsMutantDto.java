package com.periferia.mutant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IsMutantDto {
    private Long countMutantDna;
    private Long countHumanDna;
    private Double ratio;
}
