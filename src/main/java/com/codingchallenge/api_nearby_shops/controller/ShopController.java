package com.codingchallenge.api_nearby_shops.controller;

import com.codingchallenge.api_nearby_shops.model.ReactionType;
import com.codingchallenge.api_nearby_shops.service.ShopService;
import com.codingchallenge.api_nearby_shops.service.dto.ShopDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest-service")
public class ShopController {

    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shops/find-by-default-location")
    public List<ShopDTO> findNearbyShopsByDefaultUserCityAndLocation(){
        return shopService.getNearbyShopsByDefaultUserLocation();
    }

    @GetMapping("/shops/find-by-custom-location")
    public List<ShopDTO> findNearbyShopsByCustomCityAndLocation(@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude){
        Point location = new Point(longitude, latitude);
        return shopService.getNearbyShopsByLocation(location);
    }

    @GetMapping("/shops/favorites")
    public List<ShopDTO> getFavoritesShops(){
        return shopService.getFavoritesShops();
    }

    @PatchMapping("/shops/remove-from-favorite/{id}")
    public void removeShopFromFavorites(@PathVariable("id") String shopId){
        shopService.removeUserReactionAboutShop(shopId, ReactionType.LIKE);
    }

    @PatchMapping("/shops/add-reaction/{id}")
    public void addReactionToShop(@RequestBody ObjectNode reaction, @PathVariable("id") String shopId){
        shopService.addOrUpdateUserReactionAboutShop(ReactionType.valueOf(reaction.get("reactionType").textValue()) ,shopId);
    }
}
