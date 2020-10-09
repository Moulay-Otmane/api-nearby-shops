package com.codingchallenge.api_nearby_shops.service.arg;

import org.springframework.data.geo.Point;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserArg {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String city;

    @NotNull
    private Point location;

    public UserArg() {
    }

    public UserArg(@NotNull @Email String email, @NotNull String password, @NotNull String city, @NotNull Point location) {
        this.email = email;
        this.password = password;
        this.city = city;
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
