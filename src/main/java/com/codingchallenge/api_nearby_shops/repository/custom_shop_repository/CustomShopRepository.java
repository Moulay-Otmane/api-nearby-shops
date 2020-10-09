package com.codingchallenge.api_nearby_shops.repository.custom_shop_repository;

import com.codingchallenge.api_nearby_shops.model.Reaction;
import com.codingchallenge.api_nearby_shops.model.ReactionType;
import com.codingchallenge.api_nearby_shops.model.Shop;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomShopRepository {
    GeoResults<Shop> getShopsByLocationNearAndShopIsNotFavorite(Point location, Distance distance, String userId, LocalDateTime limitDateToEnableDislikedShops);
    List<Shop> getShopsByUserIdAndReactionType(String userId, ReactionType reactionType);
    void addOrUpdateUserReactionUsingShopId(Reaction newReaction, String shopId);
    void removeUserReactionUsingShopIdAndUserId(String userId, String shopId, ReactionType reactionType);
}
