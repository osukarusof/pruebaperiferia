package com.periferia.mutant.controller;

import com.periferia.mutant.dto.MutantDto;
import com.periferia.mutant.service.MutantService;
import com.periferia.mutant.utils.ApiResponse;
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
    public ResponseEntity<ApiResponse> mutant (@RequestBody MutantDto mutantDto) {
        return new ResponseEntity<>(mutantService.mutant(mutantDto), HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<ApiResponse> mutantStatus () {
        return new ResponseEntity<>(mutantService.isMutant(), HttpStatus.OK);
    }
}