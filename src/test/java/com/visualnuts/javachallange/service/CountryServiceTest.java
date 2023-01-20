package com.visualnuts.javachallange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.visualnuts.javachallange.dto.CountryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    @InjectMocks
    private CountryService countryService;
    @Mock
    private JsonService jsonService;
    private CountryDTO countryDTO;

    @BeforeEach
    void setUp() {
        countryDTO = new CountryDTO();
        countryDTO.setCountry("BR");
        countryDTO.setLanguages(new ArrayList<>());
        countryDTO.getLanguages().add("PT");
    }

    @Test
    void countAllOfficialLangCountries() {
        when(jsonService.convertJsonToCountryList()).thenReturn(Arrays.asList(countryDTO));
        Integer value = countryService.countAllOfficialLangCountries();
        Mockito.verify(jsonService).convertJsonToCountryList();
        assertNotNull(value);
    }

    @Test
    void numberCountriesWorld() {
        when(jsonService.convertJsonToCountryList()).thenReturn(Arrays.asList(countryDTO));
        Long value = countryService.numberCountriesWorld();
        Mockito.verify(jsonService).convertJsonToCountryList();
        assertNotNull(value);
    }

    @Test
    void countryMostOfficialLangGermany() throws JsonProcessingException {
        when(jsonService.convertJsonToCountryList()).thenReturn(Arrays.asList(countryDTO));
        CountryDTO value = countryService.countryMostOfficialLangGermany();
        Mockito.verify(jsonService).convertJsonToCountryList();
        assertNotNull(value);
    }


    @Test
    void countryMostOfficialLangGermanyException() throws JsonProcessingException {
        when(jsonService.convertJsonToCountryList()).thenReturn(Arrays.asList(countryDTO));
        Assertions.assertThrows(RuntimeException.class, () -> countryService.countryMostOfficialLangGermany());
    }


    @Test
    void countryHighestNumbOfficialLang() {
        when(jsonService.convertJsonToCountryList()).thenReturn(Arrays.asList(countryDTO));
        CountryDTO value = countryService.countryHighestNumbOfficialLang();
        Mockito.verify(jsonService).convertJsonToCountryList();
        assertNotNull(value);
    }

    @Test
    void commonOfficialLangAll() {
        when(jsonService.convertJsonToCountryList()).thenReturn(Arrays.asList(countryDTO));
        String value = countryService.commonOfficialLangAll();
        Mockito.verify(jsonService).convertJsonToCountryList();
        assertNotNull(value);
    }


}