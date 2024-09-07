package com.periferia.mutant.service.impl;

import com.periferia.mutant.dto.MutantDto;
import com.periferia.mutant.entity.MutantEntity;
import com.periferia.mutant.repository.MutantRepository;
import com.periferia.mutant.service.MutantService;
import com.periferia.mutant.utils.ApiResponse;
import com.periferia.mutant.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MutantServiceImpl implements MutantService {

    private final MutantRepository mutantRepository;
    private final Util util;

    @Override
    public ApiResponse<Object> mutant (MutantDto mutantDto) {

        MutantEntity mutantEntity = util.convertTo(mutantDto, MutantEntity.class);

        mutantRepository.save(null);
        return null;
    }

    @Override
    public ApiResponse isMutant () {
        return null;
    }
}
