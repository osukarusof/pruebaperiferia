package com.periferia.mutant.service;

import com.periferia.mutant.dto.MutantDto;
import com.periferia.mutant.utils.ApiResponse;

public interface MutantService {
    ApiResponse<Object> mutant (MutantDto mutantDto);

    ApiResponse<Object> isMutant ();
}
