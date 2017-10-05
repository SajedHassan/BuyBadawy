package com.example.user.eshtri_first_pafge;

/**
 * Created by user on 10/1/2017.
 */
public class SingleItemModel {


    private String owner;
    private String phone;
    private String name;
    private String properties;
    private String details;
    private String address;
    private String price;

    public SingleItemModel() {
    }

    public SingleItemModel(String owner, String phone, String name, String properties, String details, String address, String price) {
        this.owner = owner;
        this.phone = phone;
        this.name = name;
        this.properties = properties;
        this.details = details;
        this.address = address;
        this.price = price;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String description) {
        this.details = description;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
