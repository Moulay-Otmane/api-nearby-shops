package com.codingchallenge.api_nearby_shops.service.dto;

import com.codingchallenge.api_nearby_shops.model.Reaction;
import org.springframework.data.geo.Point;

import java.io.Serializable;
import java.util.List;

public class ShopDTO implements Serializable {

    private static final long serialVersionUID = -3828441884952914093L;

    private String id;
    private String name;
    private String email;
    private String picture;
    private String city;
    private double distance;
    private String address;
    private Point location;
    private List<Reaction> reactions;

    public ShopDTO(String id, String name, String email, String picture, String city, double distance, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.city = city;
        this.distance = distance;
        this.address = address;
    }

    public ShopDTO(String id, String name, String email, String picture, String city, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.city = city;
        this.address = address;
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
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
