package com.periferia.mutant.controller;

import com.periferia.mutant.dto.MutantDto;
import com.periferia.mutant.service.MutantService;
import com.periferia.mutant.utils.ApiResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "Servicio para mutantes")
public class MutantController {

    private final MutantService mutantService;

    @Operation(summary = "Nos permite validar si una cadena de ADN es mutante o es humana")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El ADN entregado es un Mutante",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseUtil.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "El ADN entregado es un Humano",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "El ADN entregado ya fue verificado",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/mutant")
    public ResponseEntity<ApiResponseUtil<Object>> mutant (@RequestBody @Valid  MutantDto mutantDto) {
        return new ResponseEntity<ApiResponseUtil<Object>>(mutantService.mutant(mutantDto), HttpStatus.OK);
    }

    @Operation(summary = "Nos permite realizar el cálculo para obtener las estadísticas de todas las cadenas de ADN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El ADN entregado es un Mutante",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseUtil.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "El ADN entregado es un Humano",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "El ADN entregado ya fue verificado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/status")
    public ResponseEntity<ApiResponseUtil<Object>> mutantStatus() {
        return new ResponseEntity<>(mutantService.isMutant(), HttpStatus.OK);
    }
}