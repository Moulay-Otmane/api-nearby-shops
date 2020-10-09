package com.codingchallenge.api_nearby_shops.repository;

import com.codingchallenge.api_nearby_shops.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends MongoRepository<City, String> {
}
