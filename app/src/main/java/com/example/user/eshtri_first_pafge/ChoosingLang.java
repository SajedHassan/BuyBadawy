package com.example.user.eshtri_first_pafge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.user.eshtri_first_pafge.R.id;
import com.example.user.eshtri_first_pafge.R.layout;

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
		this.startActivity(homeIntent);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (Constants.closeAppRequested) {
			this.finish();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences sharedPref = this.getSharedPreferences("users", Context.MODE_PRIVATE);
		String id = sharedPref.getString("lang", null);
		if (id != null) {
			int choosenLanguage = Integer.parseInt(id);
			switch (choosenLanguage) {
			case 0: {
				Intent intent = new Intent(this.getBaseContext(), FirstPage.class);
				Locale locale = new Locale("ar");
				Locale.setDefault(locale);
				Resources res = this.getBaseContext().getResources();
				Configuration config = new Configuration(res.getConfiguration());
				config.locale = locale;
				res.updateConfiguration(config, res.getDisplayMetrics());
				this.startActivity(intent);

				break;
			}
			case 1:
				Intent intent = new Intent(this.getBaseContext(), FirstPage.class);
				Locale locale = new Locale("en");
				Locale.setDefault(locale);
				Resources res = this.getBaseContext().getResources();
				Configuration config = new Configuration(res.getConfiguration());
				config.locale = locale;
				res.updateConfiguration(config, res.getDisplayMetrics());
				this.startActivity(intent);
				break;
			}
		} else {
			this.setContentView(layout.choosing_language);
			Button eng = (Button) this.findViewById(id.engB);
			Button arab = (Button) this.findViewById(id.arabB);
			eng.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					SharedPreferences sharedPref = ChoosingLang.this.getSharedPreferences("users",
							Context.MODE_PRIVATE);
					Editor editor = sharedPref.edit();
					editor.putString("lang", Integer.toString(1));
					editor.apply();

					Intent intent = new Intent(ChoosingLang.this.getBaseContext(), FirstPage.class);
					Locale locale = new Locale("en");
					Locale.setDefault(locale);
					Resources res = ChoosingLang.this.getBaseContext().getResources();
					Configuration config = new Configuration(res.getConfiguration());
					config.locale = locale;
					res.updateConfiguration(config, res.getDisplayMetrics());
					ChoosingLang.this.startActivity(intent);
				}
			});

			arab.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					SharedPreferences sharedPref = ChoosingLang.this.getSharedPreferences("users",
							Context.MODE_PRIVATE);
					Editor editor = sharedPref.edit();
					editor.putString("lang", Integer.toString(0));
					editor.apply();

					Intent intent = new Intent(ChoosingLang.this.getBaseContext(), FirstPage.class);
					Locale locale = new Locale("ar");
					Locale.setDefault(locale);
					Resources res = ChoosingLang.this.getBaseContext().getResources();
					Configuration config = new Configuration(res.getConfiguration());
					config.locale = locale;
					res.updateConfiguration(config, res.getDisplayMetrics());
					ChoosingLang.this.startActivity(intent);
				}
			});

		}

	}

}
