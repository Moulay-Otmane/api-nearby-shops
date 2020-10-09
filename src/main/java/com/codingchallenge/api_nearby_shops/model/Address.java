package com.codingchallenge.api_nearby_shops.model;

import org.springframework.data.geo.Point;

public class Address {

    private String addressLine;
    private Point location;

    public Address() {
    }

    public Address(String addressLine, Point location) {
        this.addressLine = addressLine;
        this.location = location;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
