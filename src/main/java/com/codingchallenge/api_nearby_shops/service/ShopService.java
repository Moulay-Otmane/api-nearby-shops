package com.codingchallenge.api_nearby_shops.service;

import com.codingchallenge.api_nearby_shops.model.*;
import com.codingchallenge.api_nearby_shops.repository.ShopRepository;
import com.codingchallenge.api_nearby_shops.service.dto.ShopDTO;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {

    private final static double DEFAULT_NEARBY_LOCATIONS_DISTANCE_VALUE = 5;
    private final static Metrics DEFAULT_NEARBY_LOCATIONS_DISTANCE_UNIT = Metrics.KILOMETERS;
    private final static long DEFAULT_HOURS_NUMBER_BEFORE_DISPLAYING_DISLIKED_SHOP = 2;

    private ShopRepository shopRepository;
    private UserService userService;

    public ShopService(ShopRepository shopRepository, UserService userService) {
        this.shopRepository = shopRepository;
        this.userService = userService;
    }

    /*
    * This function returns nearby shops to the default location that the current user used to sign up.
    * */
    public List<ShopDTO> getNearbyShopsByDefaultUserLocation(){
        User signedUser = userService.getAuthenticatedUser();
        Distance distance = new Distance(DEFAULT_NEARBY_LOCATIONS_DISTANCE_VALUE, DEFAULT_NEARBY_LOCATIONS_DISTANCE_UNIT);
        LocalDateTime limitDateToEnableDislikedShops = LocalDateTime.now().minusHours(DEFAULT_HOURS_NUMBER_BEFORE_DISPLAYING_DISLIKED_SHOP);
        return shopRepository.getShopsByLocationNearAndShopIsNotFavorite(signedUser.getLocation(),distance,signedUser.getId(), limitDateToEnableDislikedShops).getContent().stream()
            .map(shopGeoResult -> {
                Shop shop = shopGeoResult.getContent();
                shopGeoResult.getDistance().getValue();
                return new ShopDTO(shop.getId(), shop.getName(), shop.getEmail(), shop.getPicture(), shop.getCity(), shopGeoResult.getDistance().getValue(), shop.getAddress());
            }).collect(Collectors.toList());
    }

    /*
    * This function returns nearby shops to the location passed as parameter
    * */
    public List<ShopDTO> getNearbyShopsByLocation(Point location){
        User signedUser = userService.getAuthenticatedUser();
        Distance distance = new Distance(DEFAULT_NEARBY_LOCATIONS_DISTANCE_VALUE, DEFAULT_NEARBY_LOCATIONS_DISTANCE_UNIT);
        LocalDateTime limitDateToEnableDislikedShops = LocalDateTime.now().minusHours(DEFAULT_HOURS_NUMBER_BEFORE_DISPLAYING_DISLIKED_SHOP);
        return shopRepository.getShopsByLocationNearAndShopIsNotFavorite(location,distance,signedUser.getId(), limitDateToEnableDislikedShops).getContent().stream()
                .map(shopGeoResult -> {
                    Shop shop = shopGeoResult.getContent();
                    shopGeoResult.getDistance().getValue();
                    return new ShopDTO(shop.getId(), shop.getName(), shop.getEmail(), shop.getPicture(), shop.getCity(), shopGeoResult.getDistance().getValue(), shop.getAddress());
                } ).collect(Collectors.toList());
    }

    /*
    * This function returns favorites shops of the current user.
    * */
    public List<ShopDTO> getFavoritesShops(){
        User signedUser = userService.getAuthenticatedUser();
        return shopRepository.getShopsByUserIdAndReactionType(signedUser.getId(), ReactionType.LIKE).stream().map(shop ->
                new ShopDTO(shop.getId(),shop.getName(),shop.getEmail(), shop.getPicture(), shop.getCity(), shop.getAddress())
        ).collect(Collectors.toList());
    }

    /*
    * This function update or add the reaction of the current user about a shop
    * */
    public void addOrUpdateUserReactionAboutShop(ReactionType reactionType, String shopId){
        User signedUser = userService.getAuthenticatedUser();
        Reaction newReaction = new Reaction(reactionType, signedUser.getId(),LocalDateTime.now());
        shopRepository.addOrUpdateUserReactionUsingShopId(newReaction, shopId);
    }

    /*
    * This function delete the reaction of a user about a shop.
    * */
    public void removeUserReactionAboutShop(String shopId, ReactionType reactionType){
        User signedUser = userService.getAuthenticatedUser();
        shopRepository.removeUserReactionUsingShopIdAndUserId(signedUser.getId(), shopId, reactionType);
    }

}
