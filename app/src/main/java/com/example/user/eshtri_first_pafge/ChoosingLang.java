package com.example.user.eshtri_first_pafge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.util.Locale;
import android.widget.Toast;

import static android.R.attr.button;
import static android.R.attr.valueTo;

/**
 * Created by user on 8/29/2017.
 */

public class ChoosingLang extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_language);
    final Configuration config = new Configuration(getResources().getConfiguration());

        final Button eng= (Button) findViewById(R.id.engB);
        Button arab= (Button) findViewById(R.id.arabB);
        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
