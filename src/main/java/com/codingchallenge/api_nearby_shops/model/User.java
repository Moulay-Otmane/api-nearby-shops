package com.codingchallenge.api_nearby_shops.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String email;
    private String password;
    private String city;
    private Point location;

    public User(){}

    public User(String email, String password, String city, Point location) {
        this.email = email;
        this.password = password;
        this.city = city;
        this.location = location;
    }

    public User(String id, String email, String password, String city, Point location) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.city = city;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
