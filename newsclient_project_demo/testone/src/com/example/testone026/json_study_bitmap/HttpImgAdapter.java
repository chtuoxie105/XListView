package com.example.testone026.json_study_bitmap;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.testone001.loning.R;
import com.example.testone001.tooloclass001.ToolClassStorBitmap;

public class HttpImgAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private String[] imgAddress;
	private Executor mExcu;
	private Context mContext;
	private ToolClassStorBitmap mToolClassStorBitmap;
	
	public HttpImgAdapter(Context context, Executor excus) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		mExcu = excus;
		
		mToolClassStorBitmap = ToolClassStorBitmap.getIntance();
		mToolClassStorBitmap.startMoreThread();
		mToolClassStorBitmap.getBitmapStorageSpace();
		
	}

	public void setData(String[] str) {
		imgAddress = str;
		notifyDataSetChanged();
	}

	public int getCount() {
		return imgAddress.length;
	}

	public Object getItem(int position) {
		return imgAddress[position];
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final ImageView imgView;
		if (convertView == null) {
			convertView = inflater.inflate(
					R.layout.img_gridview_adapter_layout, null);
			imgView = (ImageView) convertView
					.findViewById(R.id.img_gridview_adapter);
			convertView.setTag(imgView);
		} else {
			imgView = (ImageView) convertView.getTag();
		}

		String hhtpUrlImg = (String) getItem(position);

		// new AsyncTask<String, Void, Bitmap>() {
		// protected Bitmap doInBackground(String... params) {
		// String httpUrlImage =params[0];
		// Bitmap bitMapImg = getBitmapForHttp(httpUrlImage);
		//
		// return bitMapImg;
		// }
		//
		// protected void onPostExecute(Bitmap result) {
		// if(result != null){
		// imgView.setImageBitmap(result);
		// }
		// }
		// }.executeOnExecutor(mExcu,hhtpUrlImg);

		
		mToolClassStorBitmap.loadBitmap(mContext.getResources(),hhtpUrlImg,imgView, R.drawable.gridview_background2);
		return convertView;
	}
}
