package com.periferia.mutant.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class Util {

    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    public <T> ApiResponseUtil<Object> mapaRespuesta (T data) {

        return ApiResponseUtil.builder()
                .status(HttpStatus.OK.value())
                .message("This sequence is a mutant")
                .data(data)
                .fieldErrors(new ArrayList<>())
                .build();
    }

    public <T> T convertTo(Object origen, Class<T> destino) {
        return modelMapper.map(origen, destino);
    }

    public String arrayConverToJson (String[] attribute) {
        try {
            return attribute != null ? objectMapper.writeValueAsString(attribute) : "[]";
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting String[] to JSON", e);
        }
    }

    public String[] jsonConverToArray (String dbData) {
        try {
            return dbData != null && !dbData.isEmpty() ? objectMapper.readValue(dbData, String[].class) : new String[0];
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to String[]", e);
        }
    }
}
