package com.periferia.mutant.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Converter
@RequiredArgsConstructor
public class DnaSequenceArrayConverter implements AttributeConverter<String[], String> {

    private  final Util util;

    @Override
    public String convertToDatabaseColumn(String[] attribute) {
        return util.arrayConverToJson(attribute);
    }

    @Override
    public String[] convertToEntityAttribute(String dbData) {
        return util.jsonConverToArray(dbData);
    }
}
