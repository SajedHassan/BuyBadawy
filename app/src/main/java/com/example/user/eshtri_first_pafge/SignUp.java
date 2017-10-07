package com.example.user.eshtri_first_pafge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.eshtri_first_pafge.R.id;
import com.example.user.eshtri_first_pafge.R.layout;

/**
 * Created by user on 8/29/2017.
 */

/**
 * Class that handles signing up a new user.
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
        this.setContentView(layout.sign_up_form);
        this.name = (EditText) this.findViewById(id.name);
        this.userName = (EditText) this.findViewById(id.username);
        this.phone = (EditText) this.findViewById(id.phone);
        this.email = (EditText) this.findViewById(id.email);
        this.emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        this.userNamePattern = "[a-zA-Z0-9_-]{3,20}";
        this.namePattern = "[\\p{L} ]+";
        this.phonePattern = "[0][1][0-9]{9}";

        Button signUp = (Button) this.findViewById(id.signUpB);

        signUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp.this.emailVal = SignUp.this.email.getText().toString().trim();
                SignUp.this.usernameVal = SignUp.this.userName.getText().toString().trim();
                SignUp.this.phoneVal = SignUp.this.phone.getText().toString().trim();
                SignUp.this.nameVal = SignUp.this.name.getText().toString().trim();

                SignUp.this.validateInput();

                if (SignUp.this.emailValid && SignUp.this.userNameValid && SignUp.this.phoneValid
                        && SignUp.this.nameValid) {
                    // DataBaseHandelerForAccounts db = new
                    // DataBaseHandelerForAccounts(getBaseContext());
                    // db.addUser(new User(0, nameVal, phoneVal, emailVal,
                    // usernameVal));
                    String task = "register";
                    UserCDBH backgroundTask = new UserCDBH(SignUp.this);
                    backgroundTask.execute(task, SignUp.this.nameVal, SignUp.this.usernameVal, SignUp.this.phoneVal,
                            SignUp.this.emailVal);
                    // finish();
                }

            }
        });

    }

    /**
     * Checks the input fields.
     */
    protected void validateInput() {

        this.validateName();

        this.validateUserName();

        this.validatePhone();

        this.validateEmail();
    }

    /**
     * Validates the email to check if the user
     * has provided it.
     */
    protected void validateEmail() {

        if (this.emailVal.length() > 0 && !this.emailVal.matches(this.emailPattern)) {

            this.email.setError("Invalid Email Address !");
            this.email.setText("");

            this.emailValid = false;

        } else {
            this.email.setError(null);
            this.emailValid = true;
        }

    }

    /**
     * Method that validates the name to check if the user
     * had provided it.
     */
    protected void validateName() {
        this.email.setText(this.nameVal);
        if (this.nameVal.length() == 0) {

            this.name.setError("Missing Name !");
            this.name.setText("");

            this.nameValid = false;

        } else if (!this.nameVal.matches(this.namePattern)) {
            this.name.setError("Invalid Name !");
            this.name.setText("");

            this.nameValid = false;
        } else {
            this.name.setError(null);
            this.nameValid = true;
        }

    }

    /**
     * Checks whether the user has provided a username.
     */
    protected void validateUserName() {
        if (this.usernameVal.length() == 0) {

            this.userName.setError("Missing Username !");
            this.userName.setText("");
            this.userName.setHint("");

            this.userNameValid = false;

        } else if (!this.usernameVal.matches(this.userNamePattern)) {
            this.userName.setError("Invalid Username !");
            this.userName.setText("");
            this.userName.setHint("");

            this.userNameValid = false;
        } else {
            this.userName.setError(null);
            this.userNameValid = true;
        }

    }

    /**
     * Checks whetherr the user has provided a phone number.
     */
    protected void validatePhone() {

        if (this.phoneVal.length() == 0) {

            this.phone.setError("Missing Phone Number !");
            this.phone.setText("");

            this.phoneValid = false;

        } else if (!this.phoneVal.matches(this.phonePattern)) {
            this.phone.setError("Invalid Phone Number !");
            this.phone.setText("");

            this.phoneValid = false;
        } else {
            this.phone.setError(null);
            this.phoneValid = true;
        }

    }

}
