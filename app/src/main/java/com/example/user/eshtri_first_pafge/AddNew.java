package com.example.user.eshtri_first_pafge;


import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import javax.mail.MessagingException;


public class AddNew extends AppCompatActivity {

    Context context;



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
                new SendMail().execute("");
            }
        });
    }

    private class SendMail extends AsyncTask<String, Integer, Void> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(AddNew.this, "Please wait", "Sending Product Request", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        protected Void doInBackground(String... params) {
            Mail m = new Mail("Eshtery.Badawy@gmail.com", "BedouinMafia#2017");
            String[] toArr = {"aya_aly_abouzeid@yahoo.com"};
            m.setTo(toArr);
            m.setFrom("Eshtery.Badawy@gmail.com");
            m.setSubject("Eshtery Badawy | New Product Request");
            m.setBody("Testing");

            try {
                if(m.send()) {
//                    Toast.makeText(AddNew.this, "Request Was Sent Successfully.", Toast.LENGTH_LONG).show();
                    display("Done", "Request Was Sent successfully.");
                } else {
//                    Toast.makeText(AddNew.this, "Please Check Your Internet Connection.", Toast.LENGTH_LONG).show();
                    display("Request Failed", "Please Check Your Internet Connection.");

                }
            } catch(Exception e) {
                Log.e("MailApp", "Could not send email", e);
            }
            return null;
        }
        public void display(String title, String message){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.show();
        }
    }


}



