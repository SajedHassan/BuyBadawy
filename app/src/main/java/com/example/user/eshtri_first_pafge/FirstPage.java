package com.example.user.eshtri_first_pafge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.user.eshtri_first_pafge.R.id;
import com.example.user.eshtri_first_pafge.R.layout;

import java.util.ArrayList;

import static com.example.user.eshtri_first_pafge.R.id.username;

public class FirstPage extends AppCompatActivity {

	// Our database
	int idCounter;
	ArrayList<User> users = new ArrayList<User>();

	int productCounter;
	ArrayList<Product> products = new ArrayList<Product>();

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

	public void contactUs(View v) {
		Intent intent = new Intent(this, ContactUs.class);

		this.startActivity(intent);
	}

	public void aboutUs(View v) {
		// TODO
	}

	public void register(View v) {
		Intent intent = new Intent(this, SignUp.class);

		this.startActivity(intent);
	}

	public void signIn(View v) {
		Intent intent = new Intent(this, LoginActivity.class);

		this.startActivity(intent);
	}

	public void functionUserYaSajed() {

		EditText nameText = (EditText) this.findViewById(id.name);
		String name = nameText.getText().toString();

		EditText usernameText = (EditText) this.findViewById(username);
		String username = usernameText.getText().toString();

		EditText phone_numberText = (EditText) this.findViewById(id.phone);
		String number = phone_numberText.getText().toString();

		EditText emailText = (EditText) this.findViewById(id.email);
		String email = emailText.getText().toString();

		this.users.add(this.idCounter, new User(this.idCounter, name, number, email, username));
		this.idCounter++;

	}

	public void functionProductYaSajed() {

		EditText nameText = (EditText) this.findViewById(id.name);
		String name = nameText.getText().toString();

		EditText descriptionText = (EditText) this.findViewById(id.description);
		String description = descriptionText.getText().toString();

		EditText detailsText = (EditText) this.findViewById(id.details);
		String details = detailsText.getText().toString();

		EditText addressText = (EditText) this.findViewById(id.address);
		String address = addressText.getText().toString();

		EditText priceText = (EditText) this.findViewById(id.price);
		String priceString = priceText.getText().toString();
		int price = Integer.parseInt(priceString);

		// products.add(productCounter, new Product(name, price, 0, address,
		// description, details, 1));
		// TODO
		this.productCounter++;

	}

}
