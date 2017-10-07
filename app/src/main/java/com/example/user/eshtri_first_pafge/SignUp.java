package com.example.user.eshtri_first_pafge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by user on 8/29/2017.
 */

public class SignUp extends AppCompatActivity {

    private boolean nameValid = true;
    private boolean userNameValid = true;
    private boolean emailValid = true;
    private boolean phoneValid = true;

    private EditText userName;
    private String usernameVal;
    private EditText phone;
    private String phoneVal;
    private EditText name;
    private String nameVal;
    private EditText email;
    private String emailVal;
    private String emailPattern;
    private String userNamePattern;
    private String namePattern;
    private String phonePattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_form);
        name = (EditText) findViewById(R.id.name);
        userName = (EditText) findViewById(R.id.username);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        userNamePattern = "[a-zA-Z0-9_-]{3,20}";
        namePattern = "[\\p{L} ]+";
        phonePattern = "[0][1][0-9]{9}";


        Button signUp = (Button) findViewById(R.id.signUpB);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailVal = email.getText().toString().trim();
                usernameVal = userName.getText().toString().trim();
                phoneVal = phone.getText().toString().trim();
                nameVal = name.getText().toString().trim();

                validateInput();


                if (emailValid && userNameValid && phoneValid && nameValid) {
//                   DataBaseHandelerForAccounts db = new DataBaseHandelerForAccounts(getBaseContext());
//                   db.addUser(new User(0, nameVal, phoneVal, emailVal, usernameVal));
                    String task = "register";
                    UserCDBH backgroundTask = new UserCDBH(SignUp.this);
                    backgroundTask.execute(task, nameVal, usernameVal, phoneVal, emailVal);
                    //finish();
                }


            }
        });

    }


    protected void validateInput() {

        validateName();

        validateUserName();

        validatePhone();

        validateEmail();
    }

    protected void validateEmail() {

        if (emailVal.length() > 0 && !emailVal.matches(emailPattern)) {

            email.setError("Invalid Email Address !");
            email.setText("");

            emailValid = false;

        } else {
            email.setError(null);
            emailValid = true;
        }

    }

    protected void validateName() {
        email.setText(nameVal);
        if (nameVal.length() == 0) {

            name.setError("Missing Name !");
            name.setText("");

            nameValid = false;

        } else if (!nameVal.matches(namePattern)) {
            name.setError("Invalid Name !");
            name.setText("");

            nameValid = false;
        } else {
            name.setError(null);
            nameValid = true;
        }

    }

    protected void validateUserName() {
        if (usernameVal.length() == 0) {

            userName.setError("Missing Username !");
            userName.setText("");
            userName.setHint("");

            userNameValid = false;

        } else if (!usernameVal.matches(userNamePattern)) {
            userName.setError("Invalid Username !");
            userName.setText("");
            userName.setHint("");

            userNameValid = false;
        } else {
            userName.setError(null);
            userNameValid = true;
        }

    }

    protected void validatePhone() {

        if (phoneVal.length() == 0) {

            phone.setError("Missing Phone Number !");
            phone.setText("");

            phoneValid = false;

        } else if (!phoneVal.matches(phonePattern)) {
            phone.setError("Invalid Phone Number !");
            phone.setText("");

            phoneValid = false;
        } else {
            phone.setError(null);
            phoneValid = true;
        }


    }


}
