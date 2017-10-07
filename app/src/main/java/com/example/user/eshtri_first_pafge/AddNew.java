package com.example.user.eshtri_first_pafge;


import android.R.layout;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.eshtri_first_pafge.R.array;
import com.example.user.eshtri_first_pafge.R.id;


public class AddNew extends AppCompatActivity implements OnItemSelectedListener {

    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView imageView;
    String chosenCategory;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.add_product);

        this.spinner = (Spinner) this.findViewById(id.catList);
        this.spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                array.categories, layout.simple_spinner_item);

        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);

        this.spinner.setAdapter(adapter);


        Button add = (Button) this.findViewById(id.button_submit);
        Button addPhoto = (Button) this.findViewById(id.AddPhoto);


        addPhoto.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        Media.EXTERNAL_CONTENT_URI);


                AddNew.this.startActivityForResult(i, AddNew.RESULT_LOAD_IMAGE);
            }
        });

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //new SendMail().execute("");

                TextView name = (TextView) AddNew.this.findViewById(id.name);
                TextView details = (TextView) AddNew.this.findViewById(id.details);
                TextView properties = (TextView) AddNew.this.findViewById(id.description);
                TextView address = (TextView) AddNew.this.findViewById(id.address);
                TextView price = (TextView) AddNew.this.findViewById(id.price);

                String nameVal = name.getText().toString();
                String detailsVal = details.getText().toString();
                String propertiesVal = properties.getText().toString();
                String addressVal = address.getText().toString();
                String priceVal = price.getText().toString();
                String catVal = String.valueOf(AddNew.this.spinner.getSelectedItemPosition());

                String task = "add";
                ///Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ProductCDBH addNewProductThread = new ProductCDBH(AddNew.this, null);
                addNewProductThread.execute(task, MainActivity.activeUser, nameVal, catVal, detailsVal, propertiesVal, addressVal, priceVal);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.chosenCategory = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AddNew.RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri imageUri = data.getData();
//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
            this.imageView = (ImageView) this.findViewById(id.imgView);
            try {
                this.imageView.setImageURI(imageUri);
            } catch (Exception e) {
                //Somt Images is not being selected and cases the app to crash
            }
//            try {
//                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
//
//                ImageView imageView = (ImageView) findViewById(R.id.imgView);
//                imageView.setImageBitmap(selectedImage);
//                //Toast.makeText(getBaseContext(), picturePath, Toast.LENGTH_SHORT).show();
//
//            } catch (Exception e) {
//
//            }


        }
    }
}




