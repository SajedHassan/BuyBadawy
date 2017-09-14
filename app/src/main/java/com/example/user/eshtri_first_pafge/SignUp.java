package com.example.user.eshtri_first_pafge;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.user.eshtri_first_pafge.R.id.phone;
import static java.lang.Integer.parseInt;


/**
 * Created by user on 8/29/2017.
 */

public class SignUp extends AppCompatActivity {

   private boolean valid = true;
//    private EditText name = (EditText)findViewById(R.id.name);
    private EditText userName ;
//    private String nameVal = name.getText().toString().trim();
    private String usernameVal;
//    private EditText phone = (EditText)findViewById(R.id.phone);
//    private String phoneVal = name.getText().toString().trim();
    private EditText email;
    private String emailVal;
    private String emailPattern;
    private String userNamePattern;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_form);
        userName = (EditText)findViewById(R.id.username);
        usernameVal = userName.getText().toString().trim();
        email = (EditText)findViewById(R.id.email);
        emailVal = email.getText().toString().trim();
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        userNamePattern = "^[a-z0-9_-]{3,20}$";


        Button signUp = (Button)findViewById(R.id.signUpB);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailVal = email.getText().toString().trim();
                usernameVal = userName.getText().toString().trim();

                validateInput();


                if(valid) {
//                    DataBaseHandelerForAccounts db = new DataBaseHandelerForAccounts(getBaseContext());
//                    db.addUser(new User(0, nameVal, phoneVal, emailVal, usernameVal));

                Intent intent = new Intent(getBaseContext(), SignUp.class);

                startActivity(intent);

                }

            }
        });

    }
protected void  validateInput(){

     validateName();

    validateUserName();

     validatePhone();

    validateEmail();


}

    protected void validateEmail(){

        if (  emailVal.length() > 0  &&!emailVal.matches(emailPattern)) {

            email.setError("Invalid Email Address !");
            email.setText("");

            valid = false;

        }
        else{
            email.setError(null);
            valid = true;
        }

    }

    protected void validateName(){


    }

    protected void validateUserName(){
        if (!usernameVal.matches(userNamePattern)) {

            userName.setError("Invalid Username !");
            userName.setText("");
            userName.setHint("0");

            valid = false;

        }
        else{
            userName.setError(null);
            valid = true;
        }

    }
    protected void validatePhone(){


    }



}
