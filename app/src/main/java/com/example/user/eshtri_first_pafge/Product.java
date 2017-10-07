package com.example.user.eshtri_first_pafge;

/**
 * Created by user on 8/29/2017.
 */

public class Product {

	/**
	 * The username of the product's seller.
	 */
	public String productN;

	public int productId;

	public String owner;

	public String image;

	public String phone;

	public int price;

	// public int picture;

	public String address;

	public String description;

	public String details;

	public int key;

	public int category;

	public Product() {
		// empty
	}

	public Product(int productId, String productN, int category, String details, String description, String address,
			int price) {
		this.productN = productN;
		this.productId = productId;
		this.price = price;
		this.address = address;
		this.description = description;
		this.details = details;
		this.category = category;
	}

	// public Product (final int productId, final String productN, final int
	// category, final String details, final String description, final String
	// address, final int price, String image) {
	// this.productN = productN;
	// this.productId = productId;
	// this.price = price;
	// this.address = address;
	// this.description = description;
	// this.details = details;
	// this.category = category;
	// this.image = image;
	// }

	public Product(int productId, String productN, int category, String details, String description, String address,
			int price, String owner, String phone) {
		this.productN = productN;
		this.productId = productId;
		this.price = price;
		this.address = address;
		this.description = description;
		this.details = details;
		this.category = category;
		this.owner = owner;
		this.phone = phone;
	}

	// public String getNumber() {
	// return seller.number;
	// }
	//
	// public String getEmail() {
	// return seller.email;
	// }
}
