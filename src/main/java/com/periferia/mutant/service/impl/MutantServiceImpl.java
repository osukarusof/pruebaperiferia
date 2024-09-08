package com.periferia.mutant.service.impl;

import com.periferia.mutant.dto.IsMutantCalculate;
import com.periferia.mutant.dto.IsMutantDto;
import com.periferia.mutant.dto.MutantDto;
import com.periferia.mutant.entity.MutantEntity;
import com.periferia.mutant.exception.ForbiddenException;
import com.periferia.mutant.exception.NotFoundException;
import com.periferia.mutant.repository.MutantRepository;
import com.periferia.mutant.service.MutantService;
import com.periferia.mutant.utils.ApiResponseUtil;
import com.periferia.mutant.utils.MutantUtil;
import com.periferia.mutant.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MutantServiceImpl implements MutantService {

    private final MutantRepository mutantRepository;

    private  final MutantUtil mutantUtil;
    private final Util util;

    @Override
    public ApiResponseUtil<Object> mutant (MutantDto mutantDto) {

        String dnaJson = util.arrayConverToJson(mutantDto.getDna());
        Optional<MutantEntity> mutantOpt = mutantRepository.findByDnaSequence(dnaJson);
        if (mutantOpt.isPresent()) {
            throw new NotFoundException("This DNA sequence has already been used");
        }

        Boolean isMutant = mutantUtil.isMutantValid(mutantDto.getDna());

        mutantRepository.save(MutantEntity
                .builder()
                .dnaSequence(dnaJson)
                .isMutant(isMutant)
                .build());

        if (!isMutant) {
            throw  new ForbiddenException("This sequence is not a mutant");
        }

        return util.mapaRespuesta(MutantDto.builder().dna(util.jsonConverToArray(dnaJson)).build());
    }

    @Override
    public ApiResponseUtil<Object> isMutant () {
        Optional<IsMutantCalculate> isMutantCalculateOpt = mutantRepository.getMutantCalculate();
        if (isMutantCalculateOpt.isEmpty()) {
            throw new NotFoundException("There is no DNA to perform the calculations");
        }

        return util.mapaRespuesta(util.convertTo(isMutantCalculateOpt.get(), IsMutantDto.class));
    }
}
