package com.codingchallenge.api_nearby_shops.service.dto;

import java.io.Serializable;

public class JwtTokenDTO implements Serializable {

    private static final long serialVersionUID = 3876391738838366053L;
    private final String token;

    public JwtTokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
