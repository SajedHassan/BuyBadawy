package com.example.user.eshtri_first_pafge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.user.eshtri_first_pafge.R.id.price;

/**
 * Created by user on 8/30/2017.
 */

public class MyProducts extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("عبايات حريمي", 10, 0, "none", "none", "none", 1));
        products.add(new Product("ثوب", 11, 0, "none", "none", "none", 1));
        products.add(new Product("عمة", 12, 0, "none", "none", "none", 1));
        products.add(new Product("mgm3z4", 13, 0, "none", "none", "none", 1));
        products.add(new Product("mgm3z5", 14, 0, "none", "none", "none", 1));
        products.add(new Product("mgm3z6", 15, 0, "none", "none", "none", 1));


        CA itemsAdapter = new CA(this, products);


        ListView lay = (ListView)findViewById(R.id.list);
        if (lay != null)
            Log.v("3hnaaaaaaaaa", " hnaaaaaaaaa");


        lay.setAdapter(itemsAdapter);

        lay.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                //String value = (String)adapter.getItemAtPosition(position);
                //((TextView)v.findViewById(R.id.productName)).setText("sajed");
                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
                Intent intent = new Intent(getBaseContext(), ProductPreview.class);
                startActivity(intent);
            }
        });

    }
}
