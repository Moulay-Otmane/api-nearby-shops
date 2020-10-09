package com.codingchallenge.api_nearby_shops.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "city")
public class City {

    @Id
    private String id;
    private String name;
    private List<Address> addressList;

    public City() {
    }

    public City(String name, List<Address> addressList) {
        this.name = name;
        this.addressList = addressList;
    }

    public City(String id, String name, List<Address> addressList) {
        this.id = id;
        this.name = name;
        this.addressList = addressList;
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

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
