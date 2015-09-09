package com.example.testone001.loning;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.test.AndroidTestCase;
import android.util.Log;

public class Test extends AndroidTestCase{
	String urlAddress = "http://192.168.1.203/json/around";
	public void testgetHttpData(String url) {
		Executor mExcutor = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS,
				new LinkedBlockingDeque<Runnable>());
		
		new AsyncTask<String, Void, String>() {
			protected String doInBackground(String... params) {
				String str = httpGetMesg(params[0]);
				return str;
			}
			protected void onPostExecute(String result) {
					try {
						JSONObject jsonObkect = new JSONObject(result);
						JSONObject json = jsonObkect.getJSONObject("merchantKey");
						Log.i("11","¶ÔÏó>>>:"+json);
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
			}
		}.executeOnExecutor(mExcutor, url);
	}

	public String httpGetMesg(String urls) {
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(urls);
			HttpResponse response = httpClient.execute(httpGet);
			int n = response.getStatusLine().getStatusCode();
			if (n == HttpStatus.SC_OK) {
				String line = EntityUtils.toString(response.getEntity(),
						"UTF-8");
				return line;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
