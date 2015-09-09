package com.example.testone026.json_study_bitmap;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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

import com.example.testone001.loning.R;
import com.example.testone001.tooloclass001.ToolClassStorBitmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridView;

public class BitmapGridViewActivity extends Activity {

	String[] str = {
			"http://img0.imgtn.bdimg.com/it/u=3215986528,313107872&fm=21&gp=0.jpg",
			"http://img4.imgtn.bdimg.com/it/u=2961903531,2356716617&fm=23&gp=0.jpg",
			"http://img5.imgtn.bdimg.com/it/u=212291197,106167076&fm=23&gp=0.jpg",
			"http://img0.imgtn.bdimg.com/it/u=2852088603,1294175307&fm=23&gp=0.jpg",
			"http://img4.imgtn.bdimg.com/it/u=2615301497,2857071345&fm=23&gp=0.jpg",

			"http://img5.imgtn.bdimg.com/it/u=2562855756,997269017&fm=23&gp=0.jpg",
			"http://img0.imgtn.bdimg.com/it/u=3923730445,4290343773&fm=23&gp=0.jpg",
			"http://img3.imgtn.bdimg.com/it/u=2303620788,3237781573&fm=23&gp=0.jpg",
			"http://img1.imgtn.bdimg.com/it/u=1228856741,1148143928&fm=23&gp=0.jpg",
			"http://img2.imgtn.bdimg.com/it/u=1388498650,2529921706&fm=23&gp=0.jpg",

			"http://img3.imgtn.bdimg.com/it/u=4112503054,402357639&fm=21&gp=0.jpg",
			"http://img5.imgtn.bdimg.com/it/u=2060202349,1894455556&fm=21&gp=0.jpg",
			"http://img0.imgtn.bdimg.com/it/u=480577464,2087856000&fm=21&gp=0.jpg",
			"http://img0.imgtn.bdimg.com/it/u=1854849069,386582757&fm=21&gp=0.jpg",
			"http://img2.imgtn.bdimg.com/it/u=3533835925,1606113287&fm=21&gp=0.jpg",

			"http://img4.imgtn.bdimg.com/it/u=3792695135,1835603517&fm=21&gp=0.jpg",
			"http://img0.imgtn.bdimg.com/it/u=1384073263,1841819903&fm=21&gp=0.jpg",
			"http://img5.imgtn.bdimg.com/it/u=308115112,4038812993&fm=21&gp=0.jpg",
			"http://img4.imgtn.bdimg.com/it/u=571366870,564155952&fm=21&gp=0.jpg",

			"http://img2.imgtn.bdimg.com/it/u=2347688765,3840613615&fm=23&gp=0.jpg",
			"http://img1.imgtn.bdimg.com/it/u=116993252,4160792345&fm=23&gp=0.jpg",
			"http://img5.imgtn.bdimg.com/it/u=2504529905,3816354928&fm=23&gp=0.jpg",
			"http://img3.imgtn.bdimg.com/it/u=2170208609,2083385650&fm=23&gp=0.jpg",
			"http://img3.imgtn.bdimg.com/it/u=951312881,1556099872&fm=21&gp=0.jpg" };
	private GridView mGridViewImage;
	private Executor mExcutor;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview_image_layout);
		mGridViewImage = (GridView) findViewById(R.id.image_httpclient_gridview);

		mExcutor = new ThreadPoolExecutor(10, 150, 10, TimeUnit.SECONDS,
				new LinkedBlockingDeque<Runnable>());
		
		HttpImgAdapter adapter = new HttpImgAdapter(this, mExcutor);
		adapter.setData(str);
		mGridViewImage.setAdapter(adapter);

	}

	public void httpGetJSONMap(String urlStr) {
		new AsyncTask<String, Void, Bitmap>() {
			protected Bitmap doInBackground(String... params) {
				Bitmap bitmap = getImageForhttp(params[0]);
				return bitmap;
			}
			protected void onPostExecute(Bitmap result) {
				
			};
		}.executeOnExecutor(mExcutor, urlStr);
	}

	/**
	 * 从网络上获得图片
	 * 
	 */

	public Bitmap getImageForhttp(String s) {
		try {
			URL url = new URL(s);
			InputStream input = url.openStream();
			Bitmap bitmap = BitmapFactory.decodeStream(input);
			return bitmap;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
