package com.codingchallenge.api_nearby_shops.repository.custom_shop_repository;

import com.codingchallenge.api_nearby_shops.model.Reaction;
import com.codingchallenge.api_nearby_shops.model.ReactionType;
import com.codingchallenge.api_nearby_shops.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CustomShopRepositoryImpl implements CustomShopRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoOperations mongoOperations;


    /*
    *  This function return a list of shops nearby the location passed as parameter with a distance lower than the distance in the parameters,
    * it returns the shops that are not one of the favorites shops of the a user,it returns also the shops disliked by the a user
    * 2 hours ago at least.
    * */
    @Override
    public GeoResults<Shop> getShopsByLocationNearAndShopIsNotFavorite(Point location, Distance distance, String userId, LocalDateTime limitDateToEnableDislikedShops) {

        NearQuery query = NearQuery.near(location).maxDistance(distance).query(
                new Query().addCriteria(
                    new Criteria().orOperator(
                            Criteria.where("reactions").not().elemMatch(
                                    Criteria.where("userId").is(userId)
                            )
                            ,Criteria.where("reactions").elemMatch(
                                    Criteria.where("userId").is(userId).and("reactionType").is(ReactionType.DISLIKE).and("createdAt").lt(limitDateToEnableDislikedShops)
                            )
                    )
                )
        );

        return mongoTemplate.geoNear(query, Shop.class);
    }

    /*
    * This function returns the list of the shops to which was added a reaction by a user
    * */
    @Override
    public List<Shop> getShopsByUserIdAndReactionType(String userId, ReactionType reactionType) {

        Query query = new Query().addCriteria(
                Criteria.where("reactions").elemMatch(
                        Criteria.where("userId").is(userId).and("reactionType").is(reactionType)
                )
        );

        return mongoOperations.find(query, Shop.class);
    }


    /*
    * This function update the reaction of a user about a shop if it exist or added if it does not exist
    * */
    @Override
    public void addOrUpdateUserReactionUsingShopId(Reaction newReaction, String shopId) {

        Query query = new Query().addCriteria(
                Criteria.where("_id").is(shopId).and("reactions").elemMatch(
                        Criteria.where("userId").is(newReaction.getUserId())
                )
        );

        Update update = new Update();
        update.set("reactions.$", newReaction);

        if(mongoTemplate.updateFirst(query, update, Shop.class).getModifiedCount() == 0){
            query = new Query().addCriteria(
                    Criteria.where("_id").is(shopId)
            );
            update= new Update().addToSet("reactions",newReaction);
            mongoTemplate.updateFirst(query, update, Shop.class);
        }
    }

    /*
    * This function remove user reaction about a shop
    * */

    @Override
    public void removeUserReactionUsingShopIdAndUserId(String userId, String shopId, ReactionType reactionType) {
        Query query = new Query().addCriteria(
                Criteria.where("_id").is(shopId).and("reactions").elemMatch(
                        Criteria.where("userId").is(userId).and("reactionType").is(reactionType)
                )
        );

        Update update = new Update().pull("reactions", new Reaction(reactionType,userId,null));
        mongoTemplate.updateFirst(query,update, Shop.class);
    }


}
