package com.example.user.eshtri_first_pafge;

/**
 * Created by user on 8/29/2017.
 */

public class User {

    public String name;

    public String number;

    public int id;

    public String email;

    public boolean hasEmail;

    //public boolean isConfirmed;

    public String username;

    public String getUsername() {
        return username;
    }

    public User(){
        //empty
    }

    public User(int id, String name, String number, String email, String username) {
        this.name = name;
        this.number = number;
        this.id = id;
        this.email = email;
        this.username = username;
        if (email == null) {
            hasEmail = false;
        }
    }
}
