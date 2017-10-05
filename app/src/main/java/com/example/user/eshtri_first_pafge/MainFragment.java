package com.example.user.eshtri_first_pafge;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    static private View containerAvtivity;
    ArrayList<Product> clothes;
    ArrayList<Product> carpets;
    ArrayList<Product> herbs;
    ArrayList<Product> food;
    ArrayList<Product> tours;
    ArrayList<Product> others;


    ArrayList<ArrayList<Product>> allCategoriesProducts;

    ListView lay;

    ArrayList<SectionDataModel> allSampleData;

    public static MainFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MainFragment fragment = new MainFragment();
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


        clothes = new ArrayList<Product>();
        carpets = new ArrayList<Product>();
        herbs = new ArrayList<Product>();
        food = new ArrayList<Product>();
        tours = new ArrayList<Product>();
        others = new ArrayList<Product>();
        allCategoriesProducts = new ArrayList<ArrayList<Product>>();

        ProductCDBH gettingActiveUserProducts = new ProductCDBH(getActivity(), new AsyncResponse() {
            @Override
            public void processFinish(String json) {
                Log.v("readAll", json);
                fetchData(json);
                setAllDataAdabter();
            }
        });

        String task = "readAll";
        gettingActiveUserProducts.execute(task, MainActivity.activeUser);





//        lay.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> adapter, View v, int position,
//                                    long arg3)
//            {
//                //String value = (String)adapter.getItemAtPosition(position);
//                //((TextView)v.findViewById(R.id.productName)).setText("sajed");
//                // assuming string and if you want to get the value on click of list item
//                // do what you intend to do on click of listview row
//                Intent intent = new Intent(getActivity(), ProductPreview.class);
//                startActivity(intent);
//            }
//        });
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        containerAvtivity = view;
        Toast.makeText(getActivity(), "Welcome back", Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "back", Toast.LENGTH_SHORT).show();
    }

    public void backAfterDeletion(final Context context) {
        //repeated code from onstart
        clothes = new ArrayList<Product>();
        carpets = new ArrayList<Product>();
        herbs = new ArrayList<Product>();
        food = new ArrayList<Product>();
        tours = new ArrayList<Product>();
        others = new ArrayList<Product>();
        allCategoriesProducts = new ArrayList<ArrayList<Product>>();

        ProductCDBH gettingActiveUserProducts = new ProductCDBH(context,true ,new AsyncResponse() {
            @Override
            public void processFinish(String json) {
                Log.v("readAll", json);
                fetchData(json);
                setAllDataAdabter();
            }
        });

        String task = "readAll";
        gettingActiveUserProducts.execute(task, MainActivity.activeUser);
    }




    public void fetchData(String json) {
        try {
            JSONArray jsonarray = new JSONArray(json);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);

                String owner = jsonobject.getString("owner");
                String phone = jsonobject.getString("phone");
                String name = jsonobject.getString("name");
                int cat = Integer.parseInt(jsonobject.getString("category"));
                String details = jsonobject.getString("details");
                String properties = jsonobject.getString("properties");
                String address = jsonobject.getString("address");
                int price = Integer.parseInt(jsonobject.getString("price"));

                switch (cat) {
                    case 0: {
                        clothes.add(new Product(-1, name, cat, details, properties, address, price, owner, phone));
                        break;
                    }
                    case 1: {
                        carpets.add(new Product(-1, name, cat, details, properties, address, price, owner, phone));
                        break;
                    }
                    case 2: {
                        herbs.add(new Product(-1, name, cat, details, properties, address, price, owner, phone));
                        break;
                    }
                    case 3: {
                        food.add(new Product(-1, name, cat, details, properties, address, price, owner, phone));
                        break;
                    }
                    case 4: {
                        tours.add(new Product(-1, name, cat, details, properties, address, price, owner, phone));
                        break;
                    }
                    case 5: {
                        others.add(new Product(-1, name, cat, details, properties, address, price, owner, phone));
                        break;
                    }
                    default: {
                        //unreachable case
                    }
                }

                Log.v("hnaaa", name + "," + cat + "," + details + "," + properties + "," + address + "," + price + " == " + jsonarray.length());


            }
            allCategoriesProducts.add(clothes);
            allCategoriesProducts.add(carpets);
            allCategoriesProducts.add(herbs);
            allCategoriesProducts.add(food);
            allCategoriesProducts.add(tours);
            allCategoriesProducts.add(others);



        } catch (Exception e) {
            // TODO
        }

        allSampleData = new ArrayList<SectionDataModel>();

        for (int i = 0; i < 6; i++) {

            ArrayList<Product> section = allCategoriesProducts.get(i);
            int numOfItemsInSection = section.size();

            if (numOfItemsInSection > 0) {
                SectionDataModel dm = new SectionDataModel();

                switch (i) {
                    case 0: {
                        dm.setHeaderTitle("Clothes");
                        break;
                    }
                    case 1: {
                        dm.setHeaderTitle("Carpets");
                        break;
                    }
                    case 2: {
                        dm.setHeaderTitle("Herbs");
                        break;
                    }
                    case 3: {
                        dm.setHeaderTitle("Food");
                        break;
                    }
                    case 4: {
                        dm.setHeaderTitle("Tours");
                        break;
                    }
                    case 5: {
                        dm.setHeaderTitle("Others");
                        break;
                    }
                    default: {
                        //unreachable case
                    }
                }

                ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
                for (int j = 0; j < numOfItemsInSection; j++) {
                    singleItem.add(new SingleItemModel(section.get(j).owner, section.get(j).phone, section.get(j).productN, section.get(j).description, section.get(j).details, section.get(j).address, section.get(j).price + ""));
                }

                dm.setAllItemsInSection(singleItem);

                allSampleData.add(dm);

            }

        }
    }
    public void setAllDataAdabter() {




        RecyclerView my_recycler_view = (RecyclerView) containerAvtivity.findViewById(R.id.my_recycler_view);

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(getActivity(), allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);
    }
}