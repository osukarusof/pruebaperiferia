package com.periferia.mutant.service.impl;

import com.periferia.mutant.dto.MutantDto;
import com.periferia.mutant.repository.MutantRepository;
import com.periferia.mutant.service.MutantService;
import com.periferia.mutant.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MutantServiceImpl implements MutantService {

    private final MutantRepository mutantRepository;
    @Override
    public ApiResponse mutant (MutantDto mutantDto) {
        return null;
    }

    @Override
    public ApiResponse isMutant () {
        return null;
    }
}
