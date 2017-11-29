package com.example.user.eshtri_first_pafge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.user.eshtri_first_pafge.R.id;
import com.example.user.eshtri_first_pafge.R.layout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Class that deals with the products to fetch from the database.
 */
public class MyProductsFragment extends Fragment {
    private static final String ARG_PAGE = "ARG_PAGE";
    private ArrayList<Product> products;
    private ListView lay;
    private View containerActivity;
    private int mPage;

    public static MyProductsFragment newInstance(final int page) {
        final Bundle args = new Bundle();
        args.putInt(MyProductsFragment.ARG_PAGE, page);
        final MyProductsFragment fragment = new MyProductsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPage = this.getArguments().getInt(MyProductsFragment.ARG_PAGE);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.products = new ArrayList<Product>();
        this.lay = this.containerActivity.findViewById(id.list);

        final ProductCDBH gettingActiveUserProducts = new ProductCDBH(this.getActivity(), new AsyncResponse() {
            @Override
            public void processFinish(final String json) {
                Log.v("kaaaaaaaa", json);
                Log.v("poooo", "ka");

                try {
                    Log.v("poooo", "0");
                    final JSONArray jsonarray = new JSONArray(json);
                    Log.v("poooo", "1");
                    for (int i = 0; i < jsonarray.length(); i++) {
                        Log.v("poooo", "2");
                        final JSONObject jsonobject = jsonarray.getJSONObject(i);
                        Log.v("poooo", "3");

                        final int productId = Integer.parseInt(jsonobject.getString("id"));
                        Log.v("poooo", "4");

                        Log.v("my product id", productId + "");
                        final String name = jsonobject.getString("name");
                        // String image = jsonobject.getString("image");
                        final int cat = Integer.parseInt(jsonobject.getString("category"));
                        final String details = jsonobject.getString("details");
                        final String properties = jsonobject.getString("properties");
                        final String address = jsonobject.getString("address");
                        final int price = Integer.parseInt(jsonobject.getString("price"));
                        final String image = jsonobject.getString("image");

                        MyProductsFragment.this.products
                                .add(new Product(productId, name, cat, details, properties, address, price, image));

                        Log.v("saaaaaaaaaaaaaa", name + "," + cat + "," + details + "," + properties + "," + address
                                + "," + price + " == " + jsonarray.length());

                    }

                    final CA itemsAdapter = new CA(MyProductsFragment.this.getActivity(), MyProductsFragment.this.products);
                    MyProductsFragment.this.lay.setAdapter(itemsAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (final Exception e) {
                    // TODO
                    Log.v("pooo", e.getMessage());
                }
            }
        });

        final String task = "read";
        gettingActiveUserProducts.execute(task, MainActivity.activeUser);

        this.lay.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapter, final View v, final int position, final long arg3) {
                // String value = (String)adapter.getItemAtPosition(position);
                // ((TextView)v.findViewById(R.id.productName)).setText("sajed");
                // assuming string and if you want to get the value on click of
                // list item
                // do what you intend to do on click of listview row
                final Product clickedItem = MyProductsFragment.this.products.get(position);
                final Intent intent = new Intent(MyProductsFragment.this.getActivity(), ProductPreview.class);

                // intent.putExtra("image", clickedItem.image);
                intent.putExtra("name", clickedItem.productN);
                intent.putExtra("det", clickedItem.details);
                intent.putExtra("pro", clickedItem.description);
                intent.putExtra("address", clickedItem.address);
                intent.putExtra("price", clickedItem.price + "");
                intent.putExtra("image", clickedItem.image);

                MyProductsFragment.this.startActivity(intent);
            }
        });
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(layout.my_products, container, false);
        this.containerActivity = view;

        // final Context ctx = getActivity();

        // Floating Button to open AddNew activity
        final FloatingActionButton addNew = view.findViewById(id.addNewProductFloatingButton);
        addNew.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Intent intent = new Intent(MyProductsFragment.this.getActivity(), AddNew.class);
                MyProductsFragment.this.startActivity(intent);
            }
        });

        // products.add(new Product("عبايات حريمي", 10, 0, "none",
        // "none", "none", 1));

        // if (lay != null)
        // Log.v("3hnaaaaaaaaa", " hnaaaaaaaaa");

        return view;
    }

}