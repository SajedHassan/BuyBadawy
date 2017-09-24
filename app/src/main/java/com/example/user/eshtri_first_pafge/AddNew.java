package com.example.user.eshtri_first_pafge;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;


public class AddNew extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


String chosenCategory;
    private static int RESULT_LOAD_IMAGE = 1;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

         spinner = (Spinner) findViewById(R.id.catList);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        Button add = (Button) findViewById(R.id.button_submit);
        Button addPhoto = (Button) findViewById(R.id.AddPhoto);


        addPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

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


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        chosenCategory = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
}}




