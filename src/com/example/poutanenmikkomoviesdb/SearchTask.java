package com.example.poutanenmikkomoviesdb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.os.AsyncTask;


public class SearchTask extends AsyncTask<String, Void, String> {
	public AsyncResponse delegate = null;
	private StringBuffer sb;
	
	protected String doInBackground(String... params) {
		String url = params[0];
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);
			BufferedReader in = new BufferedReader(new
					InputStreamReader(
							response.getEntity().getContent()));
			sb = new StringBuffer("");
			String line = "";
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	protected void onPostExecute(String result) {
		delegate.processFinish(result);
	}
}
