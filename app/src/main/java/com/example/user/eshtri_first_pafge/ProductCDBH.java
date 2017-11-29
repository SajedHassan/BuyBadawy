package com.example.user.eshtri_first_pafge;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import java.security.SecureRandom;

public class ProductCDBH extends AsyncTask<Object, Void, String> {

	final int ADDNEW = 0;
	final int READ = 1;
	final int READALL = 2;
	final int DELETE = 3;
	public AsyncResponse delegate;
	Bitmap image;
	boolean progressPermitted = true;
	int actionSelector;

	Context context;
	private ProgressDialog progressDialog;

	ProductCDBH(Context context, AsyncResponse interfaceObject) {
		this.context = context;
		delegate = interfaceObject;
	}


	ProductCDBH(Context context, boolean progressPermited, AsyncResponse interfaceObject) {
		progressPermitted = progressPermited;
		this.context = context;
		delegate = interfaceObject;
	}



	@Override
	protected String doInBackground(Object... params) {
        //uploadFile(sajedLhadMaftker);
		String urlRegistration = "https://eshtrybadawy.000webhostapp.com/products-addNew.php";
		String urlLogin = "https://eshtrybadawy.000webhostapp.com/products-read.php";
		String urlReadAllForMainActivity = "https://eshtrybadawy.000webhostapp.com/all_products_read.php";
		String urlDelete = "https://eshtrybadawy.000webhostapp.com/products-delete.php";

		String task = params[0].toString();

		if (task.equals("add")) {

			// ByteArrayOutputStream byteArrayOutputStream = new
			// ByteArrayOutputStream();
			// image.compress(Bitmap.CompressFormat.JPEG, 100,
			// byteArrayOutputStream);
			// String encodedImage =
			// Base64.encodeToString(byteArrayOutputStream.toByteArray(),
			// Base64.DEFAULT);

			String owner = params[1].toString();
			String name = params[2].toString();
			String cat = params[3].toString();
			String details = params[4].toString();
			String properties = params[5].toString();
			String address = params[6].toString();
			String price = params[7].toString();
			String picName = params[8].toString();

			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			String token = bytes.toString();

			Log.v("hnaaaaaaa", owner + " , " + name + " , " + cat + " , " + details + " , " + properties + " , "
					+ address + " , " + price);

			try {

				URL url = new URL(urlRegistration);
				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setDoInput(true);
				OutputStream outputStream = httpURLConnection.getOutputStream();
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				String myData = URLEncoder.encode("owner", "UTF-8") + "=" + URLEncoder.encode(owner, "UTF-8") + "&"
						+ URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
						+ URLEncoder.encode("cat", "UTF-8") + "=" + URLEncoder.encode(cat, "UTF-8") + "&"
						+ URLEncoder.encode("details", "UTF-8") + "=" + URLEncoder.encode(details, "UTF-8") + "&"
						+ URLEncoder.encode("properties", "UTF-8") + "=" + URLEncoder.encode(properties, "UTF-8") + "&"
						+ URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&"
						// +URLEncoder.encode("image","UTF-8")+"="+URLEncoder.encode(encodedImage,"UTF-8")+"&"
						+ URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8") + "&"
						+ URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode(price, "UTF-8") + "&"
						+ URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(picName, "UTF-8");


				bufferedWriter.write(myData);
				bufferedWriter.flush();
				bufferedWriter.close();
				outputStream.close();

				InputStream inputStream = httpURLConnection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String dataResponse = "";
				String inputLine = "";
				while ((inputLine = bufferedReader.readLine()) != null) {
					dataResponse += inputLine;
				}
				bufferedReader.close();
				inputStream.close();
				httpURLConnection.disconnect();

				Log.v("res", dataResponse);

				this.actionSelector = this.ADDNEW;

				return token + "," + dataResponse + "," + owner + "," + name + "," + cat + "," + details + ","
						+ properties + "," + address + "," + price + "," + picName;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if (task.equals("read")) {
			String activeUser = params[1].toString();
			try {
				URL url = new URL(urlLogin);
				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setDoInput(true);

				// send the email and password to the database
				OutputStream outputStream = httpURLConnection.getOutputStream();
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				String myData = URLEncoder.encode("activeUser", "UTF-8") + "=" + URLEncoder.encode(activeUser, "UTF-8");
				bufferedWriter.write(myData);
				bufferedWriter.flush();
				bufferedWriter.close();
				outputStream.close();

				// get response from the database
				InputStream inputStream = httpURLConnection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String dataResponse = "";
				String inputLine = "";
				while ((inputLine = bufferedReader.readLine()) != null) {
					dataResponse += inputLine;
				}
				bufferedReader.close();
				inputStream.close();
				httpURLConnection.disconnect();

				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				System.out.println(dataResponse);

				this.actionSelector = this.READ;
				// Log.v("hnaaaaaaa", dataResponse);
				return dataResponse;

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (task.equals("readAll")) {
			String activeUser = params[1].toString();
			try {
				URL url = new URL(urlReadAllForMainActivity);
				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setDoInput(true);

				// send the email and password to the database
				OutputStream outputStream = httpURLConnection.getOutputStream();
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				String myData = URLEncoder.encode("activeUser", "UTF-8") + "=" + URLEncoder.encode(activeUser, "UTF-8");
				bufferedWriter.write(myData);
				bufferedWriter.flush();
				bufferedWriter.close();
				outputStream.close();

				// get response from the database
				InputStream inputStream = httpURLConnection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String dataResponse = "";
				String inputLine = "";
				while ((inputLine = bufferedReader.readLine()) != null) {
					dataResponse += inputLine;
				}
				bufferedReader.close();
				inputStream.close();
				httpURLConnection.disconnect();

				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				System.out.println(dataResponse);

				this.actionSelector = this.READALL;
				// Log.v("hnaaaaaaa", dataResponse);
				return dataResponse;

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (task.equals("delete")) {
			String productID = params[1].toString();
			Log.v("check deleted id", productID);

			try {
				URL url = new URL(urlDelete);
				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setDoInput(true);

				// send the email and password to the database
				OutputStream outputStream = httpURLConnection.getOutputStream();
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				String myData = URLEncoder.encode("productID", "UTF-8") + "=" + URLEncoder.encode(productID, "UTF-8");
				bufferedWriter.write(myData);
				bufferedWriter.flush();
				bufferedWriter.close();
				outputStream.close();

				// get response from the database
				InputStream inputStream = httpURLConnection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String dataResponse = "";
				String inputLine = "";
				while ((inputLine = bufferedReader.readLine()) != null) {
					dataResponse += inputLine;
				}
				bufferedReader.close();
				inputStream.close();
				httpURLConnection.disconnect();

				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				System.out.println(dataResponse);

				this.actionSelector = this.DELETE;
				Log.v("hnaaaaaaa", dataResponse);
				return dataResponse;

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		// progress permitted could be removed BUT WITH ALL IT"S EXISTANCE
		if (this.progressPermitted) {
			this.progressDialog = new ProgressDialog(this.context);
			this.progressDialog.setMessage("Loding...");
			this.progressDialog.setCancelable(false);
			this.progressDialog.show();
		}
		this.progressPermitted = true;
	}

	@Override
	protected void onPostExecute(String s) {
		if (this.actionSelector == this.ADDNEW) {

			String[] params = null;
			String token = null;
			try {
				params = s.split("[,]");
				token = params[0];
			} catch (Exception e) {
				context.startActivity(new Intent(context, NoInternetAccess.class));
                Toast.makeText(context, "No internet connection!", Toast.LENGTH_SHORT).show();
                return;
			}
				String id = params[1];
				String owner = params[2];
				String name = params[3];
				String cat = params[4];
				String details = params[5];
				String properties = params[6];
				String address = params[7];
				String price = params[8];
				String picName = params[9];



			String link = "https://eshtrybadawy.000webhostapp.com/products-confirmation.php?sentToken=" + token + "&id="
					+ id;

			ProductCDBH.SendMail activationMail = new ProductCDBH.SendMail();
			activationMail.execute(link, owner, name, cat, details, properties, address, price, picName);

			Intent intent = new Intent(this.context, MainActivity.class);
			// display("Login Failed...", "That email and password do not match
			// our records :(.");
			if (this.progressDialog.isShowing()) {
				this.progressDialog.dismiss();
			}
			this.context.startActivity(intent);
		} else if (this.actionSelector == this.READ) {

			if (this.progressDialog.isShowing()) {
				this.progressDialog.dismiss();
			}
			this.delegate.processFinish(s);
			// Intent intent = new Intent(context, MyProducts.class);
			// intent.putExtra("json", s);
			// context.startActivity(intent);

		} else if (this.actionSelector == this.READALL) {
			if (this.progressDialog != null && this.progressDialog.isShowing()) {
				this.progressDialog.dismiss();
			}
			this.delegate.processFinish(s);
		} else if (this.actionSelector == this.DELETE) {
			if (this.progressDialog.isShowing()) {
				this.progressDialog.dismiss();
			}
			this.delegate.processFinish(s);
		} else {
			if (this.progressDialog.isShowing()) {
				this.progressDialog.dismiss();
			}
			this.display("Can't get the products...", "Something weird happened :(.");
		}
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
	}

	public void display(String title, String message) {
		Builder builder = new Builder(this.context);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();
	}

	public class SendMail extends AsyncTask<String, Void, Integer> {

		// private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// progressDialog = ProgressDialog.show(AddNew.this, "Please wait",
			// "Sending Product Request", true, false);
		}

		@Override
		protected void onPostExecute(Integer res) {
			super.onPostExecute(res);
			if (res == 1) {
				ProductCDBH.this.display("Done", "Request Was Sent successfully.");
			} else {
				ProductCDBH.this.display("Request Failed", "Please Check Your Internet Connection.");
			}
			// progressDialog.dismiss();

		}

		@Override
		protected Integer doInBackground(String... params) {
			String link = params[0];
			String owner = params[1];
			String name = params[2];
			String cat = params[3];
			String details = params[4];
			String properties = params[5];
			String address = params[6];
			String price = params[7];
			String imageName = params[8];

			Mail m = new Mail("Eshtery.Badawy@gmail.com", "BedouinMafia#2017");
			String[] toArr = { "hassanalmorsy1959@gmail.com" };
			// , "heshammedhat5@gmail.com", "aya_aly_abouzeid@yahoo.com",
			// "promohamed5hater@gmail.com"
			m.setTo(toArr);
			m.setFrom("Eshtery.Badawy@gmail.com");
			m.setSubject("Eshtery Badawy | New Product Request");

			m.setBody("A user with id: " + owner + " has added a product with name: " + name + " and category: " + cat
					+ " with details: " + details + " properties: " + properties + " and address: " + address
					+ " price: " + price + "$    to confirm it click the link below: " + link + " and uploadded the following picture:" + "http://eshtrybadawy.000webhostapp.com/uploads/" + imageName);

			try {
				if (m.send()) {
					// Toast.makeText(AddNew.this, "Request Was Sent
					// Successfully.", Toast.LENGTH_LONG).show();
					return 1;
				} else {
					// Toast.makeText(AddNew.this, "Please Check Your Internet
					// Connection.", Toast.LENGTH_LONG).show();
					return 0;

				}
			} catch (Exception e) {
				Log.e("MailApp", "Could not send email", e);
			}
			return null;
		}

	}
}