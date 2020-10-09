package com.codingchallenge.api_nearby_shops.service.dto;

import com.codingchallenge.api_nearby_shops.model.Address;

import java.io.Serializable;
import java.util.List;

public class CityDTO implements Serializable {

    private static final long serialVersionUID = -7546469678093313842L;

    private String id;
    private String name;
    private List<Address> addressList;

    public CityDTO(String id, String name, List<Address> addressList) {
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
