package com.example.user.eshtri_first_pafge;

import android.Manifest;
import android.R.layout;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.example.user.eshtri_first_pafge.R.array;

import org.json.JSONException;
import org.json.JSONObject;

public class AddNew extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ActivityCompat.OnRequestPermissionsResultCallback{
    private ImageView imageView;
    private Button btnChoose, btnUpload;
    private ProgressBar progressBar;

    public static String BASE_URL = "https://eshtrybadawy.000webhostapp.com/upload.php";
    static final int PICK_IMAGE_REQUEST = 1;
    String filePath;
    private ProgressDialog progressDialog;


    private String chosenCategory = null;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        spinner = (Spinner) findViewById(R.id.catList);
        spinner.setOnItemSelectedListener(this);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                array.categories, layout.simple_spinner_item);

        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        Boolean hasPermission = (ContextCompat.checkSelfPermission(AddNew.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(AddNew.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    112);
        }else {

        }
        imageView = (ImageView) findViewById(R.id.imgView);
        btnChoose = (Button) findViewById(R.id.AddPhoto);
        btnUpload = (Button) findViewById(R.id.button_submit);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageBrowse();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (filePath != null) {
                    try {
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

                        String[] pathSplitted = filePath.split("/");
                        String picName = pathSplitted[pathSplitted.length - 1];

                        addNewProductThread.execute(task, MainActivity.activeUser, nameVal, catVal, detailsVal, propertiesVal, addressVal, priceVal, picName );
                    } catch (Exception e) {
                        Toast.makeText(AddNew.this, "Invalid product data", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Image not selected!", Toast.LENGTH_LONG).show();
                }

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

    private void imageBrowse() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 112: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //reload my activity with permission granted or use the features what required the permission
                } else
                {
                    Toast.makeText(AddNew.this, "You must give access to storage.", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if(requestCode == PICK_IMAGE_REQUEST){
                Uri picUri = data.getData();

                filePath = getPath(picUri);




                Log.d("picUri", picUri.toString());
                Log.d("filePath", filePath);

                imageView.setImageURI(picUri);
                imageUpload(filePath);
                this.progressDialog = new ProgressDialog(AddNew.this);
                this.progressDialog.setMessage("Uploading image...");
                this.progressDialog.setCancelable(false);
                this.progressDialog.show();
            }

        }

    }

    private void imageUpload(final String imagePath) {

        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        try {
                            JSONObject jObj = new JSONObject(response);
                            String message = jObj.getString("message");

                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });

        smr.addFile("image", imagePath);
        UploadToServer.getInstance().addToRequestQueue(smr);

    }

    private String getPath(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

}
