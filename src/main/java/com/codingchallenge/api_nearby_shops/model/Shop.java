package com.codingchallenge.api_nearby_shops.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
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
    private String address;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Point location;

    private List<Reaction> reactions;

    public Shop() {
    }

    public Shop(String name, String email, String picture, String city, String address, Point location, List<Reaction> reactions) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.city = city;
        this.address = address;
        this.location = location;
        this.reactions = reactions;
    }

    public Shop(String id, String name, String email, String picture, String city, String address, Point location, List<Reaction> reactions) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.city = city;
        this.address = address;
        this.location = location;
        this.reactions = reactions;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }
}
