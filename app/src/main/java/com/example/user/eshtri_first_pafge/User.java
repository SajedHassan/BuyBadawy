package com.example.user.eshtri_first_pafge;

/**
 * Created by user on 8/29/2017.
 */

/**
 * Class that represents the user.
 */
public class User {

	public String name;

	public String number;

	public int id;

	public String email;

	public boolean hasEmail;

	// public boolean isConfirmed;

	public String username;

	/**
	 * Empty constructor.
	 */
	public User() {
		// empty
	}

	/**
	 * Constructor that generates the user object with its data.
	 * @param id id of the user.
	 * @param name personal name of the user.
	 * @param number phone number.
	 * @param email might be used for user verification.
	 * @param username username for login.
	 */
	public User(int id, String name, String number, String email, String username) {
		this.name = name;
		this.number = number;
		this.id = id;
		this.email = email;
		this.username = username;
		if (email == null) {
			this.hasEmail = false;
		}
	}

	/**
	 * Getter for the username
	 * @return username of the user.
	 */
	public String getUsername() {
		return this.username;
	}
}
