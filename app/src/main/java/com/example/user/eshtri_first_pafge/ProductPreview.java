package com.example.user.eshtri_first_pafge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by user on 8/30/2017.
 */

public class ProductPreview extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_preview);

        Intent clickedItem = getIntent();

        String name = clickedItem.getStringExtra("name");
        String det = clickedItem.getStringExtra("det");
        String pro = clickedItem.getStringExtra("pro");
        String address = clickedItem.getStringExtra("address");
        String price = clickedItem.getStringExtra("price");
        boolean gettingPublicProduct = clickedItem.getBooleanExtra("publicProduct", false);
        String phone;
        String owner;

        if (!gettingPublicProduct) {
            SharedPreferences sharedPref = getSharedPreferences("users", Context.MODE_PRIVATE);
            phone = sharedPref.getString("phone", null);
            owner = sharedPref.getString("name", null);
        } else {
            phone = clickedItem.getStringExtra("phone");
            owner = clickedItem.getStringExtra("owner");
        }


        // F stands for field
        TextView nameF = (TextView)findViewById(R.id.product_name);
        TextView phoneF = (TextView)findViewById(R.id.num);
        TextView addressF = (TextView)findViewById(R.id.address);
        TextView priceF = (TextView)findViewById(R.id.price);
        TextView detF = (TextView)findViewById(R.id.details);
        TextView proF = (TextView)findViewById(R.id.dec);
        TextView ownerF = (TextView)findViewById(R.id.owner);


        nameF.setText("name: " + name);
        phoneF.setText("Phone: " + phone);
        addressF.setText("Address: " + address);
        detF.setText(det);
        proF.setText(pro);
        priceF.setText("Price: " + price);
        ownerF.setText("Owner: " + owner);





    }
}
