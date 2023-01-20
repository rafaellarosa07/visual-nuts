package com.visualnuts.javachallange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.visualnuts.javachallange.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CountryService {

    public final JsonService jsonService;


    @Autowired
    public CountryService(JsonService jsonService) {
        this.jsonService = jsonService;
    }


    public Long numberCountriesWorld() {
        return getCountries().stream().count();
    }

    public CountryDTO countryMostOfficialLangGermany() throws JsonProcessingException {
        return getCountries().stream().filter(country -> {
                    return country.getLanguages().stream().anyMatch(lang -> lang.equals("de"));
                }).max(Comparator.comparing(countryDTO -> countryDTO.getLanguages().size()))
                .orElseThrow(RuntimeException::new);

    }

    public CountryDTO countryHighestNumbOfficialLang() {
        return getCountries().stream()
                .max(Comparator.comparing(countryDTO -> countryDTO.getLanguages().size()))
                .orElseThrow(RuntimeException::new);
    }

    public Integer countAllOfficialLangCountries() {
        return getCountries().stream()
                .mapToInt(countryDTO -> countryDTO.getLanguages().size()).sum();
    }

    public List<String> commonOfficialLangAll() {
        List<String> languages = new ArrayList<>();
        getCountries().stream().forEach(countryDTO -> {
            languages.addAll(countryDTO.getLanguages());
        });

        Long valueParameter = languages.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        TreeMap::new,
                        Collectors.counting()
                ))
                .lastEntry()
                .getValue();

        List<String> languagesReturn = languages.stream()
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                .entrySet().stream()
                .filter(l -> l.getValue() >= valueParameter)
                .map(String::valueOf).toList();
        return languagesReturn;
    }


    private List<CountryDTO> getCountries() {
        return jsonService.convertJsonToCountryList();
    }
}
