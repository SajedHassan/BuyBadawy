package com.example.user.eshtri_first_pafge;

/**
 * Model class for the single data item.
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
        super();
    }

    public SingleItemModel(final String owner, final String phone, final String name,
                           final String properties, final String details, final String address,
                           final String price) {
        super();
        this.owner = owner;
        this.phone = phone;
        this.name = name;
        this.properties = properties;
        this.details = details;
        this.address = address;
        this.price = price;
    }

    public final String getProperties() {
        return this.properties;
    }

    public final void setProperties(final String properties) {
        this.properties = properties;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getDetails() {
        return this.details;
    }

    public final void setDetails(final String description) {
        this.details = description;
    }

    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(final String address) {
        this.address = address;
    }

    public final String getPrice() {
        return this.price;
    }

    public final void setPrice(final String price) {
        this.price = price;
    }

    public final String getOwner() {
        return this.owner;
    }

    public final void setOwner(final String owner) {
        this.owner = owner;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final void setPhone(final String phone) {
        this.phone = phone;
    }

}
