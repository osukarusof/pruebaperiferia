package com.periferia.mutant.exception;

import com.periferia.mutant.utils.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponseUtil<Object>> handleNotFoundException (NotFoundException ex){
        ApiResponseUtil<Object> responseDto = ApiResponseUtil
                .builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .data(new ArrayList<>())
                .fieldErrors(new ArrayList<>())
                .build();
        return new ResponseEntity<ApiResponseUtil<Object>>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiResponseUtil<Object>> handleForbiddenException(ForbiddenException ex) {

        ApiResponseUtil<Object> responseDto = ApiResponseUtil
                .builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(ex.getMessage())
                .data(new ArrayList<>())
                .fieldErrors(new ArrayList<>())
                .build();

        return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponseUtil<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, Object> errorResponse = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorResponse.put(fieldName, errorMessage);
        });

        ApiResponseUtil<Object> responseDto = ApiResponseUtil
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed. Check the request parameters.")
                .data(new ArrayList<>())
                .fieldErrors(errorResponse)
                .build();

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
