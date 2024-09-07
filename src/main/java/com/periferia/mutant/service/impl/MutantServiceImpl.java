package com.periferia.mutant.service.impl;

import com.periferia.mutant.dto.MutantDto;
import com.periferia.mutant.entity.MutantEntity;
import com.periferia.mutant.exception.ForbiddenException;
import com.periferia.mutant.exception.NotFoundException;
import com.periferia.mutant.repository.MutantRepository;
import com.periferia.mutant.service.MutantService;
import com.periferia.mutant.utils.ApiResponse;
import com.periferia.mutant.utils.MutantUtil;
import com.periferia.mutant.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MutantServiceImpl implements MutantService {

    private final MutantRepository mutantRepository;

    private  final MutantUtil mutantUtil;
    private final Util util;

    @Override
    public ApiResponse<Object> mutant (MutantDto mutantDto) {

        String dnaJson = util.arrayConverToJson(mutantDto.getDna());
        Optional<MutantEntity> mutantOpt = mutantRepository.findByDnaSequence(dnaJson);
        if (mutantOpt.isPresent()) {
            throw new NotFoundException("This DNA sequence has already been used");
        }

        MutantEntity mutantEntity = util.convertTo(mutantDto, MutantEntity.class);
        Boolean isMutant = mutantUtil.isMutantValid(mutantDto.getDna());
        mutantEntity.setIsMutant(isMutant);
        mutantRepository.save(mutantEntity);

        if (!isMutant) {
            throw  new ForbiddenException("This sequence is not a mutant");
        }

        return ApiResponse.builder().status(HttpStatus.OK.value()).message("This sequence is a mutant").build();
    }

    @Override
    public ApiResponse isMutant () {
        return null;
    }
}
