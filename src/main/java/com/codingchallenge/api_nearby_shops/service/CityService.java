package com.codingchallenge.api_nearby_shops.service;

import com.codingchallenge.api_nearby_shops.repository.CityRepository;
import com.codingchallenge.api_nearby_shops.service.dto.CityDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /*
    * This function returns a list of all the cities saved is the database.
    * */
    public List<CityDTO> getCities(){
        return cityRepository.findAll().stream().map(city -> new CityDTO(city.getId(), city.getName(), city.getAddressList())).collect(Collectors.toList());
    }
}
