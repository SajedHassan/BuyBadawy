package com.example.user.eshtri_first_pafge;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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
                        +URLEncoder.encode("identifier_email","UTF-8")+"="+URLEncoder.encode(regEmail,"UTF-8");



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

                return "Successfully Registered " + regName;
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
                return  dataResponse;

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
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        } else if(actionSelector == LOGIN){
            String test = "false";
            String name = "";
            String email = "";
            Log.v("hnaaaaaaaaaaaaaaaaaa", s);
            String[] serverResponse = s.split("[,]");
            test = serverResponse[0];


            if(test.equals("true")){
                name = serverResponse[1];
                email = serverResponse[2];
                Intent intent = new Intent(context,MainActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
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
}
