package com.example.user.eshtri_first_pafge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

/**
 * Created by user on 8/29/2017.
 */

public class ChoosingLang extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Constants.closeAppRequested) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("users", Context.MODE_PRIVATE);
        String id = sharedPref.getString("lang", null);
        if (id != null) {
            int choosenLanguage = Integer.parseInt(id);
            switch (choosenLanguage) {
                case 0: {
                    Intent intent = new Intent(getBaseContext(), FirstPage.class);
                    Locale locale = new Locale("ar");
                    Locale.setDefault(locale);
                    Resources res = getBaseContext().getResources();
                    Configuration config = new Configuration(res.getConfiguration());
                    config.locale = locale;
                    res.updateConfiguration(config, res.getDisplayMetrics());
                    startActivity(intent);

                    break;
                }
                case 1: {
                    Intent intent = new Intent(getBaseContext(), FirstPage.class);
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Resources res = getBaseContext().getResources();
                    Configuration config = new Configuration(res.getConfiguration());
                    config.locale = locale;
                    res.updateConfiguration(config, res.getDisplayMetrics());
                    startActivity(intent);
                    break;
                }
            }
        } else {
            setContentView(R.layout.choosing_language);
            final Button eng = (Button) findViewById(R.id.engB);
            final Button arab = (Button) findViewById(R.id.arabB);
            eng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPref = getSharedPreferences("users", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("lang", Integer.toString(1));
                    editor.apply();

                    Intent intent = new Intent(getBaseContext(), FirstPage.class);
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Resources res = getBaseContext().getResources();
                    Configuration config = new Configuration(res.getConfiguration());
                    config.locale = locale;
                    res.updateConfiguration(config, res.getDisplayMetrics());
                    startActivity(intent);
                }
            });

            arab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPref = getSharedPreferences("users", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("lang", Integer.toString(0));
                    editor.apply();

                    Intent intent = new Intent(getBaseContext(), FirstPage.class);
                    Locale locale = new Locale("ar");
                    Locale.setDefault(locale);
                    Resources res = getBaseContext().getResources();
                    Configuration config = new Configuration(res.getConfiguration());
                    config.locale = locale;
                    res.updateConfiguration(config, res.getDisplayMetrics());
                    startActivity(intent);
                }
            });

        }


    }


}
