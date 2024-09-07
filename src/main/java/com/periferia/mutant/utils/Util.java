package com.periferia.mutant.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Util {

    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public Util(ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.modelMapper = modelMapper;
        this.objectMapper =  objectMapper;
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
