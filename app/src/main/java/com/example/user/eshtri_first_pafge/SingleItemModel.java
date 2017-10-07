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

	public SingleItemModel(String owner, String phone, String name, String properties, String details, String address,
			String price) {
		this.owner = owner;
		this.phone = phone;
		this.name = name;
		this.properties = properties;
		this.details = details;
		this.address = address;
		this.price = price;
	}

	public String getProperties() {
		return this.properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String description) {
		details = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
