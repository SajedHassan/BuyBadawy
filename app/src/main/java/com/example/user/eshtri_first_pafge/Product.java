package com.example.user.eshtri_first_pafge;

/**
 * Created by user on 8/29/2017.
 */

public class Product {

    /**
     * The username of the product's seller.
     */
    public String productN;

    public User seller;

    public int price;

    //public int picture;

    public String address;

    public String description;

    public String details;

    public int key;

    public int category;

    public Product() {
        //empty
    };

    public Product (final String productN,   final int category, final String details, final String description,  final String address, final int price) {
        this.productN = productN;
        //this.seller = seller;
        this.price = price;
        //this.picture = picture;
        this.address = address;
        this.description = description;
        this.details = details;
        this.category = category;
    }

    public String getNumber() {
        return seller.number;
    }

    public String getEmail() {
        return seller.email;
    }
}
