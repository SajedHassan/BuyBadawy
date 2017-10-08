package com.example.user.eshtri_first_pafge;

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
        super();
        // empty
    }

    /**
     * Constructor that generates the user object with its data.
     *
     * @param id       id of the user.
     * @param name     personal name of the user.
     * @param number   phone number.
     * @param email    might be used for user verification.
     * @param username username for login.
     */
    public User(final int id, final String name, final String number, final String email, final String username) {
        super();
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
     *
     * @return username of the user.
     */
    public final String getUsername() {
        return this.username;
    }
}
