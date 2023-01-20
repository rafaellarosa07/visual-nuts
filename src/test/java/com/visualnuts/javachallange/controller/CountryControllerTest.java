package com.visualnuts.javachallange.controller;

import com.visualnuts.javachallange.dto.CountryDTO;
import com.visualnuts.javachallange.service.CountryService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CountryControllerTest {

    private static final String PATH = "/country";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    private CountryDTO countryDTO;

    @BeforeEach
    void setUp() {
        countryDTO = new CountryDTO();
        countryDTO.setCountry("BR");
        countryDTO.setLanguages(new ArrayList<>());
        countryDTO.getLanguages().add("PT");
    }


    @Test
    void getNumberCountriesWorld() throws Exception {
        long value = 2;
        when(countryService.numberCountriesWorld()).thenReturn(value);
        mockMvc.perform(get(PATH+"/number-countries-world")).andExpect(status().isOk());
    }

    @Test
    void countryHighestNumbOfficialLang() throws Exception {
        when(countryService.countryHighestNumbOfficialLang()).thenReturn(countryDTO);
        mockMvc.perform(get(PATH+"/highest-numb-official-lang"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country", Is.is("BR")));
    }

    @Test
    void countryMostOfficialLangGermany() throws Exception {
        when(countryService.countryMostOfficialLangGermany()).thenReturn(countryDTO);
        mockMvc.perform(get(PATH+"/most-official-lang-germany")).andExpect(status().isOk())
                .andExpect(jsonPath("$.country", Is.is("BR")));
    }

    @Test
    void commonOfficialLangAll() throws Exception {
        when(countryService.commonOfficialLangAll()).thenReturn("PT");
        mockMvc.perform(get(PATH+"/most-common-official-lang")).andExpect(status().isOk());
    }

    @Test
    void countAllOfficialLangCountries() throws Exception {
        int value = 2;
        when(countryService.countAllOfficialLangCountries()).thenReturn(value);
        mockMvc.perform(get(PATH+"/count-all-lang")).andExpect(status().isOk());
    }
}