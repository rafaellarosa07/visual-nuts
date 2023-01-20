package com.visualnuts.javachallange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visualnuts.javachallange.JsonStaticCountries;
import com.visualnuts.javachallange.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonService {


    private ObjectMapper mapper;


    @Autowired
    public JsonService(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    public List<CountryDTO> convertJsonToCountryList() {
        try {
            return mapper.readValue(JsonStaticCountries.json,
                    new TypeReference<List<CountryDTO>>() {
                    });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
