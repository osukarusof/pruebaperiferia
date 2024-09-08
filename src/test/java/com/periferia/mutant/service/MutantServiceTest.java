package com.periferia.mutant.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import com.periferia.mutant.config.MapperConfig;
import com.periferia.mutant.dto.IsMutantCalculate;
import com.periferia.mutant.dto.IsMutantDto;
import com.periferia.mutant.dto.MutantDto;
import com.periferia.mutant.entity.MutantEntity;
import com.periferia.mutant.exception.ForbiddenException;
import com.periferia.mutant.exception.NotFoundException;
import com.periferia.mutant.repository.MutantRepository;
import com.periferia.mutant.service.impl.MutantServiceImpl;
import com.periferia.mutant.utils.MutantUtil;
import com.periferia.mutant.utils.Util;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

@SpringJUnitConfig({ MutantServiceImpl.class, MutantUtil.class, Util.class})
@Import({MapperConfig.class})
public class MutantServiceTest {

    @MockBean
    private MutantRepository mutantRepository;

    @Inject
    private Util util;

    private MutantEntity mutantEntity;

    @Autowired
    private MutantServiceImpl mutantService;


    @Test
    public void isMutantExistErrorNotFound () {
        insertDb();
        when(mutantRepository.findByDnaSequence(anyString())).thenReturn(Optional.of(mutantEntity));
        String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        MutantDto body = MutantDto.builder().dna(dna).build();
        assertThrows(NotFoundException.class, () -> mutantService.isMutant(body));
    }

    @Test
    public void  isMutantNotMutantErrorForbiddenException () {
        when(mutantRepository.findByDnaSequence(anyString())).thenReturn(Optional.empty());
        String[] dna = {"ATCGTA", "GCTGAC", "TACGTT", "CGTACG", "AGCTGA", "TGATCG"};
        MutantDto body = MutantDto.builder().dna(dna).build();
        assertThrows(ForbiddenException.class, () -> mutantService.isMutant(body));
    }

    @Test
    public void isMutant () {
        insertDb();
        when(mutantRepository.findByDnaSequence(anyString())).thenReturn(Optional.empty());
        String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        MutantDto body = MutantDto.builder().dna(dna).build();
        when(mutantRepository.save(any())).thenReturn(mutantEntity);
        String dnaJson = util.arrayConverToJson(dna);
        assertEquals(util.mapaRespuesta(MutantDto.builder().dna(util.jsonConverToArray(dnaJson)).build()), mutantService.isMutant(body));
    }

    @Test
    public void isMutantErorNotFountException () {
        when(mutantRepository.getMutantCalculate()).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> mutantService.isMutantCalculate());
    }

    @Test
    public void isMutantCalculate () {

        IsMutantCalculate isMutantCalculate = new IsMutantCalculate() {
            @Override
            public Long getCountMutantDna() {
                return 20L;
            }

            @Override
            public Long getCountHumanDna() {
                return 10L;
            }

            @Override
            public Double getRatio() {
                return 2.0;
            }
        };

        when(mutantRepository.getMutantCalculate()).thenReturn(Optional.of(isMutantCalculate));
        assertEquals(util.mapaRespuesta(util.convertTo(isMutantCalculate, IsMutantDto.class)), mutantService.isMutantCalculate());
    }


    private void insertDb () {
        String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        mutantEntity  = MutantEntity
                .builder()
                .dnaSequence(util.arrayConverToJson(dna))
                .isMutant(true)
                .build();
    }
}
