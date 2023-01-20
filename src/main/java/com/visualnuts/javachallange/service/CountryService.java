package com.visualnuts.javachallange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.visualnuts.javachallange.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public String commonOfficialLangAll() {
        List<String> languages = new ArrayList<>();
        getCountries().stream().forEach(countryDTO -> {
            languages.addAll(countryDTO.getLanguages());
        });
//        languages.stream().reduce((a, b) -> Comparator.comparing(String::valueOf).compare(a, b) >= 0 ? a : b);
//
//        languages.stream()
//                .collect(Collectors.groupingBy(LanguageDTO::isSomeLang, Collectors.counting()))
//                //TODO Your problem is equals method - Function.isIdentity use memory reference.
//                .entrySet().stream()
//                .filter(l -> l.)
//                .map(String::valueOf)
//                .orElse(null);
        mostCommon(languages);
        return null;
    }

    private static <T> T mostCommon(List<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Map<T, Integer> max = new HashMap<>();

        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if(max.isEmpty()){
                max.put(e.getKey(), e.getValue());
            }
            max.values().stream().forEach(value -> {
                if (max == null || e.getValue() >= value) {
                    max.put(e.getKey(), e.getValue());
                }
            });
        }

        return (T) max.keySet();
    }


    private List<CountryDTO> getCountries() {
        return jsonService.convertJsonToCountryList();
    }
}
