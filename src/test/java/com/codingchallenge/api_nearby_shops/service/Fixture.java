package com.codingchallenge.api_nearby_shops.service;

import com.codingchallenge.api_nearby_shops.model.User;
import com.codingchallenge.api_nearby_shops.service.arg.UserArg;

public class Fixture {

    static User user(){
        return new User("12abc145def678jhi9","user.test@gmail.com","$2a$10$vUUe4wKvpvKSKbjxQt47jeMnx/W9xO2jn89vblUCLEhztv6kd8q7u");
    }

    static UserArg userArg(){
        return new UserArg("user.test@gmail.com","123456789");
    }

    static String token(){
        return "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyLnRlc3RAZ21haWwuY29tIiwiZXhwIjoxNTk5Njk5NTA4LCJpYXQiOjE1OTk2NjcxMDh9.BBE2CkCtOHsE9s90sNG0ogICr-s0p1G9epSlWycKys3EP0K6gy-r5_P32VSQJorUB8NOVOpGcvihe8DEsWgGsQ";
    }


}
