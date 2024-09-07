package com.periferia.mutant.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Util {

    private final ModelMapper modelMapper;

    @Autowired
    public Util(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> T convertTo(Object origen, Class<T> destino) {
        return modelMapper.map(origen, destino);
    }
}
