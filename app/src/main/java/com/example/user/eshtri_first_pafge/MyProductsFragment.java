package com.example.user.eshtri_first_pafge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyProductsFragment extends Fragment{
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    ArrayList<Product> products;
    ListView lay;
    View containerActivity;

    public static MyProductsFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MyProductsFragment fragment = new MyProductsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public void onStart() {
        super.onStart();
        products = new ArrayList<Product>();
        lay = (ListView)containerActivity.findViewById(R.id.list);

        ProductCDBH gettingActiveUserProducts = new ProductCDBH(getActivity(), new AsyncResponse() {
            @Override
            public void processFinish(String json) {
                Log.v("hnaaaaaaaaaaaaa", json);

                try  {
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

                    CA itemsAdapter = new CA(getActivity(), products);
                    lay.setAdapter(itemsAdapter);

                } catch (Exception e) {
                    // TODO
                }
            }
        });

        String task = "read";
        gettingActiveUserProducts.execute(task, MainActivity.activeUser);


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
                Intent intent = new Intent(getActivity(), ProductPreview.class);
                startActivity(intent);
            }
        });
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_products, container, false);
        containerActivity = view;

//        final Context ctx = getActivity();



        //Floating Button to open AddNew activity
        FloatingActionButton addNew = (FloatingActionButton)view.findViewById(R.id.addNewProductFloatingButton);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddNew.class);
                startActivity(intent);
            }
        });

//        products.add(new Product("عبايات حريمي", 10, 0, "none", "none", "none", 1));







//        if (lay != null)
//            Log.v("3hnaaaaaaaaa", " hnaaaaaaaaa");





        return view;
    }






}