package com.example.user.eshtri_first_pafge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import android.support.design.widget.Snackbar;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    public final void onResume() {
        super.onResume();
        if (Constants.closeAppRequested) {
            this.finish();
        }
    }

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.login);

        final Button signIn = (Button) this.findViewById(R.id.button_submit_signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // DataBaseHandelerForAccounts db = new
                // DataBaseHandelerForAccounts(getBaseContext());

                final EditText username = (EditText)
                        LoginActivity.this.findViewById(R.id.username_input_for_sign_in);
                final EditText phoneNum = (EditText)
                        LoginActivity.this.findViewById(R.id.phone_num_for_sign_in);

                final String usernameVal = username.getText().toString().trim();
                final String phoneNumVal = phoneNum.getText().toString().trim();
                final String task = "login";

                final UserCDBH backgroundTask = new UserCDBH(LoginActivity.this);

                username.setText("");
                phoneNum.setText("");

                // execute the task
                // passes the paras to the backgroundTask
                // (param[0],param[1],param[2])
                backgroundTask.execute(task, usernameVal, phoneNumVal);

            }
        });
    }
}
