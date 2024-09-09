package com.periferia.mutant.service;

import com.periferia.mutant.dto.MutantDto;
import com.periferia.mutant.utils.ApiResponseUtil;

public interface MutantService {
    ApiResponseUtil<Object> isMutant (MutantDto mutantDto);

    ApiResponseUtil<Object> isMutantCalculate ();
}
