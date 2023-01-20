package com.visualnuts.javachallange.controller;

import com.visualnuts.javachallange.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/country")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping("number-countries-world")
    @Operation(
            summary = "Get Number of Countries in the World",
            description = "Return the number of countries in the world",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request successful"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<?> numberCountriesWorld() throws Exception {

        return new ResponseEntity<>(countryService.numberCountriesWorld(), HttpStatus.OK);
    }

    @GetMapping("highest-numb-official-lang")
    @Operation(
            summary = "Find the country with the highest number of official languages.",
            description = "Return the country with the highest number of official languages.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request successful"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<?> countryHighestNumbOfficialLang() throws Exception {

        return new ResponseEntity<>(countryService.countryHighestNumbOfficialLang(), HttpStatus.OK);
    }


    @GetMapping("most-official-lang-germany")
    @Operation(
            summary = "Finds the country with the most official languages.",
            description = "Return the country with the most official languages.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request successful"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<?> countryMostOfficialLangGermany() throws Exception {

        return new ResponseEntity<>(countryService.countryMostOfficialLangGermany(), HttpStatus.OK);
    }

    @GetMapping("most-common-official-lang")
    @Operation(
            summary = "Find the most common official language(s), of all countries.",
            description = "Return the most common official language(s), of all countries.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request successful"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<?> commonOfficialLangAll() throws Exception {

        return new ResponseEntity<>(countryService.commonOfficialLangAll(), HttpStatus.OK);
    }

    @GetMapping("count-all-lang")
    @Operation(
            summary = "Counts all the official languages spoken in the listed countries.",
            description = "Return the number of all the official languages spoken in the listed countries",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request successful"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<?> countAllOfficialLangCountries() throws Exception {

        return new ResponseEntity<>(countryService.countAllOfficialLangCountries(), HttpStatus.OK);
    }
}


