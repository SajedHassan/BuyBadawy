package com.example.user.eshtri_first_pafge;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AddNew extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        //get the spinner from the xml.
//        Spinner dropdown = (Spinner)findViewById(R.id.catList);
////create a list of items for the spinner.
//        String[] items = getResources().getStringArray(R.array.categories_array);
////create an adapter to describe how the items are displayed, adapters are used in several places in android.
////There are multiple variations of this, but this is the basic variant.
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
////set the spinners adapter to the previously created one.
//        dropdown.setAdapter(adapter);
        Button add = (Button) findViewById(R.id.button_submit);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new SendMail().execute("");

                TextView name = (TextView)findViewById(R.id.name);
                TextView details = (TextView)findViewById(R.id.details);
                TextView properties = (TextView)findViewById(R.id.description);
                TextView address = (TextView)findViewById(R.id.address);
                TextView price = (TextView)findViewById(R.id.price);

                String nameVal = name.getText().toString();
                String detailsVal = details.getText().toString();
                String propertiesVal = properties.getText().toString();
                String addressVal = address.getText().toString();
                String priceVal =  price.getText().toString();
                String catVal = "1";

                String task = "add";
                ProductCDBH addNewProductThread = new ProductCDBH(AddNew.this);
                addNewProductThread.execute(task, MainActivity.activeUser, nameVal, catVal, detailsVal, propertiesVal, addressVal, priceVal);
            }
        });
    }




}



