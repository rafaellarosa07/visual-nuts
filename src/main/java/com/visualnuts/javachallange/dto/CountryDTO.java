package com.visualnuts.javachallange.dto;

import lombok.Data;

import java.util.List;

@Data
public class CountryDTO {

    private String country;

    private List<String> languages;

}
