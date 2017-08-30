package com.example.user.eshtri_first_pafge;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_form);


        Button signUp = (Button)findViewById(R.id.signUpB);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText)findViewById(R.id.name);
                String nameVal = name.getText().toString();

                EditText userName = (EditText)findViewById(R.id.username);
                String usernameVal = name.getText().toString();

                EditText phone = (EditText)findViewById(R.id.phone);
                String phoneVal = name.getText().toString();

                EditText email = (EditText)findViewById(R.id.email);
                String emailVal = name.getText().toString();

                DataBaseHandelerForAccounts db = new DataBaseHandelerForAccounts(getBaseContext());
                db.addUser(new User(0, nameVal, phoneVal, emailVal, usernameVal));

                Intent intent = new Intent(getBaseContext(), MainActivity.class);


                startActivity(intent);


            }
        });

    }



}
