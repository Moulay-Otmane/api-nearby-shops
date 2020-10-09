package com.codingchallenge.api_nearby_shops.service;

import com.codingchallenge.api_nearby_shops.model.Reaction;
import com.codingchallenge.api_nearby_shops.model.ReactionType;
import com.codingchallenge.api_nearby_shops.model.Shop;
import com.codingchallenge.api_nearby_shops.model.User;
import com.codingchallenge.api_nearby_shops.service.arg.LoginArg;
import com.codingchallenge.api_nearby_shops.service.arg.UserArg;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Fixture {

    static User user(){
        return new User("12abc145def678jhi9","user.test@gmail.com","$2a$10$vUUe4wKvpvKSKbjxQt47jeMnx/W9xO2jn89vblUCLEhztv6kd8q7u", "Casablanca", location());
    }

    static UserArg userArg(){
        return new UserArg("user.test@gmail.com","123456789",  "Casablanca", location());
    }

    static String token(){
        return "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyLnRlc3RAZ21haWwuY29tIiwiZXhwIjoxNTk5Njk5NTA4LCJpYXQiOjE1OTk2NjcxMDh9.BBE2CkCtOHsE9s90sNG0ogICr-s0p1G9epSlWycKys3EP0K6gy-r5_P32VSQJorUB8NOVOpGcvihe8DEsWgGsQ";
    }

    static LoginArg loginArg(){
        return new LoginArg("user.test@gmail.com","123456789");
    }

    static Point location(){
        return new Point(-7.632852,33.608580);
    }

    static List<Shop> shops() {
        return Arrays.asList(
                new Shop("111shop11111111", "shop 1", "shop.1@email.com","image_shop_1", "city 1", "address 1", new Point(11111,22222),  Arrays.asList(new Reaction(ReactionType.LIKE, user().getId(), LocalDateTime.now()))),
                new Shop("222shop22222222", "shop 2", "shop.2@email.com","image_shop_2", "city 1", "address 2", new Point(11122,22233),   Arrays.asList(new Reaction(ReactionType.LIKE, user().getId(), LocalDateTime.now())))
        );
    }

}
