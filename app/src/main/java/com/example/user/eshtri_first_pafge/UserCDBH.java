package com.example.user.eshtri_first_pafge;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

/**
 * Created by user on 9/15/2017.
 */

public class UserCDBH extends AsyncTask<Object, Void, String> {

    final int REGISTER = 0;
    final int LOGIN = 1;

    int actionSelector;

    Context context;

    UserCDBH(Context context){
        this.context = context;
    }


    @Override
    protected String doInBackground(Object... params) {

        String urlRegistration = "https://eshtrybadawy.000webhostapp.com/LoginAndRegister-register.php";
        String urlLogin  = "https://eshtrybadawy.000webhostapp.com/LoginAndRegister-login.php";
        String task = params[0].toString();

        if(task.equals("register")){
            String regName = params[1].toString();
            String regUsername = params[2].toString();
            String regPhone = params[3].toString();
            String regEmail = params[4].toString();

            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);
            String token = bytes.toString();

            Log.v("hnaaaaaaa", regName + " , " + regUsername + " , " + regPhone+ " , " + regEmail);

            try {

                URL url = new URL(urlRegistration);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("identifier_name","UTF-8")+"="+URLEncoder.encode(regName,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_username","UTF-8")+"="+URLEncoder.encode(regUsername,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_phone","UTF-8")+"="+URLEncoder.encode(regPhone,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_email","UTF-8")+"="+URLEncoder.encode(regEmail,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_token","UTF-8")+"="+URLEncoder.encode(token,"UTF-8");



                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String dataResponse = "";
                String inputLine = "";
                while((inputLine = bufferedReader.readLine()) != null){
                    dataResponse += inputLine;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                Log.v("res", dataResponse);

                actionSelector = REGISTER;

                return token + "," + dataResponse;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(task.equals("login")){
            String loginUsername = params[1].toString();
            String loginPhone = params[2].toString();
            try {
                URL url = new URL(urlLogin);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                //send the email and password to the database
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("identifier_loginUsername","UTF-8")+"="+URLEncoder.encode(loginUsername,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_loginPhone","UTF-8")+"="+URLEncoder.encode(loginPhone,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //get response from the database
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String dataResponse = "";
                String inputLine = "";
                while((inputLine = bufferedReader.readLine()) != null){
                    dataResponse += inputLine;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println(dataResponse);

                actionSelector = LOGIN;
                return  dataResponse + "," + loginUsername + "," + loginPhone;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        if(actionSelector == REGISTER) {
            String[] params = s.split("[,]");
            String token = params[0];
            String id = params[1];
            SendMail activationMail = new SendMail();
            activationMail.execute("https://eshtrybadawy.000webhostapp.com/LoginAndRegister-activation.php?sentToken="+token+"&id="+id);
            Intent intent = new Intent(context, FirstPage.class);
            context.startActivity(intent);
        } else if(actionSelector == LOGIN){
            String test = "false";
            int id;
            Log.v("hnaaaaaaaaaaaaaaaaaa", s);
            String[] serverResponse = s.split("[,]");
            test = serverResponse[0];


            if(test.equals("true")){
                id = Integer.parseInt(serverResponse[1]);
                String userName = serverResponse[2];
                String phone = serverResponse[3];

                Intent intent = new Intent(context,MainActivity.class);
                intent.putExtra("id", id);

                SharedPreferences sharedPref = context.getSharedPreferences("users", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString("id", Integer.toString(id));
                editor.apply();


                context.startActivity(intent);
            }else{
                display("Login Failed...", "That email and password do not match our records :(.");
            }
        }else{
            display("Login Failed...","Something weird happened :(.");
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public class SendMail extends AsyncTask<String, Void, Integer> {

        //private ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressDialog = ProgressDialog.show(AddNew.this, "Please wait", "Sending Product Request", true, false);
        }

        @Override
        protected void onPostExecute(Integer res) {
            super.onPostExecute(res);
            if (res == 1) {
                display("Done", "Go to your enail to activate your account");
            } else {
                display("Request Failed", "Please Check Your Internet Connection.");
            }
            //progressDialog.dismiss();

        }

        protected Integer doInBackground(String... params) {

            String link = params[0];

            Mail m = new Mail("Eshtery.Badawy@gmail.com", "BedouinMafia#2017");
            String[] toArr = {"hassanalmorsy1959@gmail.com", "heshammedhat5@gmail.com", "aya_aly_abouzeid@yahoo.com", "promohamed5hater@gmail.com"};
            m.setTo(toArr);
            m.setFrom("Eshtery.Badawy@gmail.com");
            m.setSubject("Eshtery Badawy | Account activation");
            m.setBody("<h1>press on the link to activate your account </h1>" + link);

            try {
                if(m.send()) {
                    //Toast.makeText(AddNew.this, "Request Was Sent Successfully.", Toast.LENGTH_LONG).show();
                    return 1;
                } else {
                    //Toast.makeText(AddNew.this, "Please Check Your Internet Connection.", Toast.LENGTH_LONG).show();
                    return 0;

                }
            } catch(Exception e) {
                Log.e("MailApp", "Could not send email", e);
            }
            return null;
        }

    }
}
