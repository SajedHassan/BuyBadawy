package com.example.user.eshtri_first_pafge;


import android.R.layout;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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


public class AddNew extends AppCompatActivity implements OnItemSelectedListener {

    private static final int RESULT_LOAD_IMAGE = 1;
    private ImageView imageView;
    private String chosenCategory;
    private Spinner spinner;

    public AddNew() {
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        spinner = (Spinner) findViewById(R.id.catList);
        spinner.setOnItemSelectedListener(this);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                array.categories, layout.simple_spinner_item);

        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        final Button add = (Button) findViewById(R.id.button_submit);
        final Button addPhoto = (Button) findViewById(R.id.AddPhoto);


        addPhoto.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View arg0) {

                Intent inte = new Intent(
                        Intent.ACTION_PICK,
                        Media.EXTERNAL_CONTENT_URI);


                startActivityForResult(inte, RESULT_LOAD_IMAGE);
            }
        });

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                //new SendMail().execute("");

                final TextView name = (TextView) findViewById(R.id.name);
                final TextView details = (TextView) findViewById(R.id.details);
                final TextView properties = (TextView) findViewById(R.id.description);
                final TextView address = (TextView) findViewById(R.id.address);
                final TextView price = (TextView) findViewById(R.id.price);

                final String nameVal = name.getText().toString();
                final String detailsVal = details.getText().toString();
                final String propertiesVal = properties.getText().toString();
                final String addressVal = address.getText().toString();
                final String priceVal = price.getText().toString();
                final String catVal = String.valueOf(spinner.getSelectedItemPosition());

                final String task = "add";
                ///Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                final ProductCDBH addNewProductThread = new ProductCDBH(AddNew.this, null);
                addNewProductThread.execute(task, MainActivity.activeUser, nameVal, catVal, detailsVal, propertiesVal, addressVal, priceVal);
            }
        });
    }


    @Override
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long l) {
        chosenCategory = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(final AdapterView<?> adapterView) {

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == AddNew.RESULT_LOAD_IMAGE) && (resultCode == RESULT_OK) && (data != null)) {
            final Uri imageUri = data.getData();
//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
            imageView = (ImageView) findViewById(R.id.imgView);
            try {
                imageView.setImageURI(imageUri);
            } catch (final RuntimeException ignored) {
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




