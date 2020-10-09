package com.codingchallenge.api_nearby_shops.controller;

import com.codingchallenge.api_nearby_shops.service.CityService;
import com.codingchallenge.api_nearby_shops.service.dto.CityDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest-service")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public List<CityDTO> getAllCities(){
        return cityService.getCities();
    }

}
