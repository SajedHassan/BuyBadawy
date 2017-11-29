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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 8/30/2017.
 */
public class MyProducts extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.list);

        final ListView lay = (ListView) this.findViewById(R.id.list);
        final ArrayList<Product> products = new ArrayList<Product>();

        final String json = this.getIntent().getStringExtra("json");

        // products.add(new Product("عبايات حريمي", 10, 0, "none",
        // "none", "none", 1));

        try {
            final JSONArray jsonarray = new JSONArray(json);
            for (int i = 0; i < jsonarray.length(); i++) {
                final JSONObject jsonobject = jsonarray.getJSONObject(i);

                final String name = jsonobject.getString("name");
                final int cat = Integer.parseInt(jsonobject.getString("category"));
                final String details = jsonobject.getString("details");
                final String properties = jsonobject.getString("properties");
                final String address = jsonobject.getString("address");
                final int price = Integer.parseInt(jsonobject.getString("price"));
                final String image = jsonobject.getString("image");

                products.add(new Product(-1, name, cat, details, properties, address, price, image));

                Log.v("hnaaa", name + ',' + cat + ',' + details + ',' +
                        properties + ',' + address + ',' + price
                        + " == " + jsonarray.length());

            }

            final CA itemsAdapter = new CA(this, products);
            lay.setAdapter(itemsAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (final Exception ignored) {
            // TODO
        }

        // if (lay != null)
        // Log.v("3hnaaaaaaaaa", " hnaaaaaaaaa");

        lay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapter, final View v, final int position, final long arg3) {
                // String value = (String)adapter.getItemAtPosition(position);
                // ((TextView)v.findViewById(R.id.productName)).setText("sajed");
                // assuming string and if you want to get the value on click of
                // list item
                // do what you intend to do on click of listview row
                final Intent intent = new Intent(MyProducts.this.getBaseContext(), ProductPreview.class);
                MyProducts.this.startActivity(intent);
            }
        });

    }

}
