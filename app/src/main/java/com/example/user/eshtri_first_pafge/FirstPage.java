package com.example.user.eshtri_first_pafge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.user.eshtri_first_pafge.R.layout;

/**
 * Class that runs the first page displayed.
 */
public class FirstPage extends AppCompatActivity {

    User activeUser;

    @Override
    public void onResume() {
        super.onResume();
        if (Constants.closeAppRequested) {
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(homeIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = this.getSharedPreferences("users", Context.MODE_PRIVATE);
        String id = sharedPref.getString("id", null);
        if (id != null) {
            int idI = Integer.parseInt(id);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("id", idI);
            this.startActivity(intent);
        } else {
            this.setContentView(layout.activity_first_page);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    /**
     * Shows the "Contact Us" page.
     * @param v view.
     */
    public void contactUs(final View v) {
        Intent intent = new Intent(this, ContactUs.class);

        this.startActivity(intent);
    }

    /**
     * Redirects to the signing up page.
     * @param v view.
     */
    public void register(final View v) {
        Intent intent = new Intent(this, SignUp.class);

        this.startActivity(intent);
    }

    /**
     * Redirects to the page for signing in.
     * @param v
     */
    public void signIn(final View v) {
        Intent intent = new Intent(this, LoginActivity.class);

        this.startActivity(intent);
    }


}
