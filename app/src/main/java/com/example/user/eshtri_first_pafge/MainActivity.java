package com.example.user.eshtri_first_pafge;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    static int activeUser = -1;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,ChoosingLang.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent caller = getIntent();

        //TODO do not forget to get that btngam back to -1 after logging out
        if (activeUser == -1) {
            try {
                activeUser = caller.getIntExtra("id", -1);
            } catch (Exception e) {
                //TODO
            }
        }



        //logging out

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.homepage);
        Button logout = (Button)findViewById(R.id.log_out);
        logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getBaseContext().getSharedPreferences("users", 0).edit().clear().commit();
                Intent intent = new Intent(getBaseContext(),FirstPage.class);
                startActivity(intent);
            }
        });


        Button myProduct = (Button)findViewById(R.id.myProductsBtn);
        myProduct.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductCDBH gettingActiveUserProducts = new ProductCDBH(MainActivity.this);
                String task = "read";
                gettingActiveUserProducts.execute(task, MainActivity.activeUser);
            }
        });

        Button addNewP = (Button)findViewById(R.id.addprod);
        addNewP.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddNew.class);
                startActivity(intent);
            }
        });
//        // Find the View that shows the numbers category
//        TextView numbers = (TextView) findViewById(R.id.numbers);
//
//        //Log.i("sssssssssssssssss","dddddddddddddddddd");
//
//        // Set a click listener on that View
//        numbers.setOnClickListener(new OnClickListener() {
//            // The code in this method will be executed when the numbers category is clicked on.
//            @Override
//            public void onClick(View view) {
//                // Create a new intent to open the {@link NumbersActivity}
//                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
//
//                // Start the new activity
//                startActivity(numbersIntent);
//            }
//        });
//
//        // Find the View that shows the family category
//        TextView family = (TextView) findViewById(R.id.family);
//
//        // Set a click listener on that View
//        family.setOnClickListener(new OnClickListener() {
//            // The code in this method will be executed when the family category is clicked on.
//            @Override
//            public void onClick(View view) {
//                // Create a new intent to open the {@link FamilyActivity}
//                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
//
//                // Start the new activity
//                startActivity(familyIntent);
//            }
//        });
//
//        // Find the View that shows the colors category
//        TextView colors = (TextView) findViewById(R.id.colors);
//
//        // Set a click listener on that View
//        colors.setOnClickListener(new OnClickListener() {
//            // The code in this method will be executed when the colors category is clicked on.
//            @Override
//            public void onClick(View view) {
//                // Create a new intent to open the {@link ColorsActivity}
//                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
//
//                // Start the new activity
//                startActivity(colorsIntent);
//            }
//        });
//
//        // Find the View that shows the phrases category
//        TextView phrases = (TextView) findViewById(R.id.phrases);
//
//        // Set a click listener on that View
//        phrases.setOnClickListener(new OnClickListener() {
//            // The code in this method will be executed when the phrases category is clicked on.
//            @Override
//            public void onClick(View view) {
//                // Create a new intent to open the {@link PhrasesActivity}
//                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
//
//                // Start the new activity
//                startActivity(phrasesIntent);
//            }
//        });
    }
}
