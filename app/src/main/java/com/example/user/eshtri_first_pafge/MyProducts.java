package com.example.user.eshtri_first_pafge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 8/30/2017.
 */

public class MyProducts extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ListView lay = (ListView)findViewById(R.id.list);
        ArrayList<Product> products = new ArrayList<Product>();

        String json = getIntent().getStringExtra("json");

//        products.add(new Product("عبايات حريمي", 10, 0, "none", "none", "none", 1));


        try {
            JSONArray jsonarray = new JSONArray(json);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);

                String name = jsonobject.getString("name");
                int cat = Integer.parseInt(jsonobject.getString("category"));
                String details = jsonobject.getString("details");
                String properties = jsonobject.getString("properties");
                String address = jsonobject.getString("address");
                int price = Integer.parseInt(jsonobject.getString("price"));

                products.add(new Product(name, cat, details, properties, address, price));

                Log.v("hnaaa", name + "," + cat + "," + details + "," + properties + "," + address + "," + price + " == " + jsonarray.length());


            }

            CA itemsAdapter = new CA(this, products);
            lay.setAdapter(itemsAdapter);

        } catch (Exception e) {
            // TODO
        }




//        if (lay != null)
//            Log.v("3hnaaaaaaaaa", " hnaaaaaaaaa");




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
