package com.example.user.eshtri_first_pafge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.eshtri_first_pafge.R.id;
import com.example.user.eshtri_first_pafge.R.layout;
import com.squareup.picasso.Picasso;

/**
 * Created by user on 8/30/2017.
 */

public class ProductPreview extends AppCompatActivity {
    private Bitmap bmap;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.product_preview);

        final Intent clickedItem = this.getIntent();

        final String name = clickedItem.getStringExtra("name");
        final String det = clickedItem.getStringExtra("det");
        final String pro = clickedItem.getStringExtra("pro");
        final String address = clickedItem.getStringExtra("address");
        final String price = clickedItem.getStringExtra("price");
        String image = clickedItem.getStringExtra("image");
        final boolean gettingPublicProduct = clickedItem.getBooleanExtra("publicProduct", false);
        final String phone;
        final String owner;

        if (!gettingPublicProduct) {
            final SharedPreferences sharedPref = this.getSharedPreferences("users", Context.MODE_PRIVATE);
            phone = sharedPref.getString("phone", null);
            owner = sharedPref.getString("name", null);
        } else {
            phone = clickedItem.getStringExtra("phone");
            owner = clickedItem.getStringExtra("owner");
        }

        // F stands for field
        final ImageView imageView = (ImageView) this.findViewById(id.myp_roduct_image);
        final TextView nameF = (TextView) this.findViewById(id.product_name);
        final TextView phoneF = (TextView) this.findViewById(id.num);
        final TextView addressF = (TextView) this.findViewById(id.address);
        final TextView priceF = (TextView) this.findViewById(id.price);
        final TextView detF = (TextView) this.findViewById(id.details);
        final TextView proF = (TextView) this.findViewById(id.dec);
        final TextView ownerF = (TextView) this.findViewById(id.owner);

        Picasso.with(this).load(
                "http://eshtrybadawy.000webhostapp.com/uploads/" + image).into(imageView);


        nameF.setText("Name: " + name);
        phoneF.setText("Phone: " + phone);
        addressF.setText("Address: " + address);
        detF.setText(det);
        proF.setText(pro);
        priceF.setText("Price: " + price);
        ownerF.setText("Owner: " + owner);

    }


}
