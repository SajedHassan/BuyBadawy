package com.example.user.eshtri_first_pafge;


import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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

    }


}
