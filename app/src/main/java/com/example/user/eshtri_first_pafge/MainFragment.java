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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Fetches the data of products from the database.
 */
public class MainFragment extends Fragment {
    private static final String ARG_PAGE = "ARG_PAGE";
    private static View containerAvtivity;
    private ArrayList<Product> clothes;
    private ArrayList<Product> carpets;
    private ArrayList<Product> herbs;
    private ArrayList<Product> food;
    private ArrayList<Product> tours;
    private ArrayList<Product> others;
    private ArrayList<ArrayList<Product>> allCategoriesProducts;
    ListView lay;
    private ArrayList<SectionDataModel> allSampleData;
    private int mPage;
    static Context context;
    /**
     * Creates a new instance of this class with the arguments from the ARG_Page.
     * @param page number of the page.
     * @return the new instance.
     */
    public static MainFragment newInstance(final int page) {
        final Bundle args = new Bundle();
        args.putInt(MainFragment.ARG_PAGE, page);
        final MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPage = this.getArguments().getInt(MainFragment.ARG_PAGE);
    }


    @Override
    public final void onStart() {
        super.onStart();


        this.clothes = new ArrayList<Product>();
        this.carpets = new ArrayList<Product>();
        this.herbs = new ArrayList<Product>();
        this.food = new ArrayList<Product>();
        this.tours = new ArrayList<Product>();
        this.others = new ArrayList<Product>();
        this.allCategoriesProducts = new ArrayList<ArrayList<Product>>();

        final ProductCDBH gettingActiveUserProducts =
                new ProductCDBH(this.getActivity(), new AsyncResponse() {
            @Override
            public void processFinish(final String json) {
                Log.v("readAll", json);
                MainFragment.this.fetchData(json);
                MainFragment.this.setAllDataAdapter();
            }
        });

        final String task = "readAll";
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
    public final View onCreateView(final LayoutInflater inflater,
                                   final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_main, container, false);
        MainFragment.containerAvtivity = view;
        Toast.makeText(this.getActivity(), "Welcome back", Toast.LENGTH_SHORT).show();
        context = getActivity();
        return view;
    }

    @Override
    public final void onResume() {
        super.onResume();
        //Toast.makeText(this.getActivity(), "back", Toast.LENGTH_SHORT).show();
    }

    public final void backAfterDeletion(final Context context) {
        //repeated code from onstart
        this.clothes = new ArrayList<Product>();
        this.carpets = new ArrayList<Product>();
        this.herbs = new ArrayList<Product>();
        this.food = new ArrayList<Product>();
        this.tours = new ArrayList<Product>();
        this.others = new ArrayList<Product>();
        this.allCategoriesProducts = new ArrayList<ArrayList<Product>>();

        final ProductCDBH gettingActiveUserProducts =
                new ProductCDBH(context, true, new AsyncResponse() {
            @Override
            public void processFinish(final String json) {
                Log.v("readAll", json);
                MainFragment.this.fetchData(json);
                MainFragment.this.setAllDataAdapter();
            }
        });

        final String task = "readAll";
        gettingActiveUserProducts.execute(task, MainActivity.activeUser);
    }

    /**
     * Responsible of fetching the data from the JSON input.
     * @param json from the database.
     */
    private final void fetchData(final String json) {
        try {
            final JSONArray jsonarray = new JSONArray(json);
            for (int i = 0; i < jsonarray.length(); i++) {
                final JSONObject jsonobject = jsonarray.getJSONObject(i);

                final String owner = jsonobject.getString("owner");
                final String phone = jsonobject.getString("phone");
                final String name = jsonobject.getString("name");
                final int cat = Integer.parseInt(jsonobject.getString("category"));
                final String details = jsonobject.getString("details");
                final String properties = jsonobject.getString("properties");
                final String address = jsonobject.getString("address");
                final int price = Integer.parseInt(jsonobject.getString("price"));
                final String image = jsonobject.getString("image");

                switch (cat) {
                    case 0:
                        this.clothes.add(new Product(-1, name,
                                cat, details, properties, address, price, owner, phone, image));
                        break;
                    case 1:
                        this.carpets.add(new Product(-1, name,
                                cat, details, properties, address, price, owner, phone, image));
                        break;
                    case 2:
                        this.herbs.add(new Product(-1, name,
                                cat, details, properties, address, price, owner, phone, image));
                        break;
                    case 3:
                        this.food.add(new Product(-1, name,
                                cat, details, properties, address, price, owner, phone, image));
                        break;
                    case 4:
                        this.tours.add(new Product(-1, name,
                                cat, details, properties, address, price, owner, phone, image));
                        break;
                    case 5:
                        this.others.add(new Product(-1, name,
                                cat, details, properties, address, price, owner, phone, image));
                        break;
                    default:
                        //unreachable case
                }

                Log.v("hnaaa", name + "," + cat + "," + details + "," +
                        properties + "," + address + "," + price + " == " + jsonarray.length());


            }
            this.allCategoriesProducts.add(this.clothes);
            this.allCategoriesProducts.add(this.carpets);
            this.allCategoriesProducts.add(this.herbs);
            this.allCategoriesProducts.add(this.food);
            this.allCategoriesProducts.add(this.tours);
            this.allCategoriesProducts.add(this.others);


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (final Exception ignored) {
            // TODO
        }

        this.allSampleData = new ArrayList<SectionDataModel>();

        for (int i = 0; i < 6; i++) {

            final ArrayList<Product> section = this.allCategoriesProducts.get(i);
            final int numOfItemsInSection = section.size();

            if (numOfItemsInSection > 0) {
                final SectionDataModel dm = new SectionDataModel();

                switch (i) {
                    case 0:
                        dm.setHeaderTitle("Clothes");
                        break;
                    case 1:
                        dm.setHeaderTitle("Carpets");
                        break;
                    case 2:
                        dm.setHeaderTitle("Herbs");
                        break;
                    case 3:
                        dm.setHeaderTitle("Food");
                        break;
                    case 4:
                        dm.setHeaderTitle("Tours");
                        break;
                    case 5:
                        dm.setHeaderTitle("Others");
                        break;
                    default:
                        //unreachable case
                }

                final ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
                for (int j = 0; j < numOfItemsInSection; j++) {
                    singleItem.add(new SingleItemModel(section.get(j).owner,
                            section.get(j).phone, section.get(j).productN, section.get(j).description,
                            section.get(j).details, section.get(j).address, section.get(j).price + "", section.get(j).image));
                }

                dm.setAllItemsInSection(singleItem);

                this.allSampleData.add(dm);

            }

        }
    }

    private final void setAllDataAdapter() {


        final RecyclerView my_recycler_view =
                MainFragment.containerAvtivity.findViewById(R.id.my_recycler_view);

        my_recycler_view.setHasFixedSize(true);

        final RecyclerViewDataAdapter adapter =
                new RecyclerViewDataAdapter(context, this.allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this.getActivity(),
                LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);
    }
}