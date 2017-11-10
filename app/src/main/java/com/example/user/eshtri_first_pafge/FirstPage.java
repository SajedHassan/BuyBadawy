package com.example.user.eshtri_first_pafge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Class that runs the first page displayed.
 */
public class FirstPage extends AppCompatActivity {

    User activeUser;

    @Override
    public final void onResume() {
        super.onResume();
        if (Constants.closeAppRequested) {
            this.finish();
        }
    }

    @Override
    public final void onBackPressed() {
        final Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(homeIntent);
    }

    @Override
    protected final void onStart() {
        super.onStart();
        final SharedPreferences sharedPref = this.getSharedPreferences("users", Context.MODE_PRIVATE);
        final String id = sharedPref.getString("id", null);
        if (id != null) {
            final int idI = Integer.parseInt(id);
            final Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("id", idI);
            this.startActivity(intent);
        } else {
            this.setContentView(R.layout.activity_first_page);
        }
    }

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    /**
     * Shows the "Contact Us" page.
     *
     * @param v view.
     */
    public final void contactUs(final View v) {
        final Intent intent = new Intent(this, ContactUs.class);

        this.startActivity(intent);
    }

    /**
     * Shows the "About Us" page.
     *
     * @param v view.
     */
    public final void aboutUs(final View v) {
        final Intent intent = new Intent(this, AboutUs.class);

        this.startActivity(intent);
    }

    /**
     * Redirects to the signing up page.
     *
     * @param v view.
     */
    public final void register(final View v) {
        final Intent intent = new Intent(this, SignUp.class);

        this.startActivity(intent);
    }

    /**
     * Redirects to the page for signing in.
     *
     * @param v view
     */
    public final void signIn(final View v) {
        final Intent intent = new Intent(this, LoginActivity.class);

        this.startActivity(intent);
    }

}
