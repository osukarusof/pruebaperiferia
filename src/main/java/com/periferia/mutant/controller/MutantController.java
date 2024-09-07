package com.periferia.mutant.controller;

import com.periferia.mutant.dto.MutantDto;
import com.periferia.mutant.service.MutantService;
import com.periferia.mutant.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class MutantController {

    private final MutantService mutantService;

    @PostMapping("/mutant")
    public ResponseEntity<ApiResponse<Object>> mutant (@RequestBody @Valid  MutantDto mutantDto) {
        return new ResponseEntity<ApiResponse<Object>>(mutantService.mutant(mutantDto), HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<ApiResponse<Object>> mutantStatus () {
        return new ResponseEntity<>(mutantService.isMutant(), HttpStatus.OK);
    }
}