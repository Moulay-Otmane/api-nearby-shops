package com.codingchallenge.api_nearby_shops.repository;

import com.codingchallenge.api_nearby_shops.model.Shop;
import com.codingchallenge.api_nearby_shops.repository.custom_shop_repository.CustomShopRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShopRepository extends MongoRepository<Shop, String>, CustomShopRepository {

}
