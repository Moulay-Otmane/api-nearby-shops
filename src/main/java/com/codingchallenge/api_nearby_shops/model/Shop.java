package com.codingchallenge.api_nearby_shops.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "shop")
public class Shop {

    @Id
    private String id;
    private String name;
    private String email;
    private String picture;
    private String city;
    private Location location;
    private List<Reaction> Reactions;

    public Shop(){}

    public Shop(String id, String name, String email, String picture, String city, Location location, List<Reaction> reactions) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.city = city;
        this.location = location;
        Reactions = reactions;
    }

    public Shop(String id, String name, String email, String picture, String city, Location location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.city = city;
        this.location = location;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Reaction> getReactions() {
        return Reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        Reactions = reactions;
    }
}
