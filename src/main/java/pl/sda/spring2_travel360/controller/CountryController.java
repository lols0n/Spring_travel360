package pl.sda.spring2_travel360.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.spring2_travel360.dto.CountryDto;
import pl.sda.spring2_travel360.request.AddCountryRequest;
import pl.sda.spring2_travel360.response.GetCountriesResponse;
import pl.sda.spring2_travel360.service.CountryService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/country")
public class CountryController {

    private final CountryService service;

    @GetMapping
    public GetCountriesResponse getCountries() {
        var conturies = service.getAllContury();
        return GetCountriesResponse.of(conturies);
    }

    @PostMapping
    public void addCountry(@RequestBody AddCountryRequest request) {
        var countryDto = CountryDto.builder()
                .name(request.getCountry())
                .build();
        service.addCountry(countryDto);
    }
}
