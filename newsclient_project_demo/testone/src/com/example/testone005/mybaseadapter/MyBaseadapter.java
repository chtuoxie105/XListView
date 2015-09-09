package com.example.testone005.mybaseadapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testone001.loning.R;

public class MyBaseadapter extends BaseAdapter {

	private List<ResourceBean> mData = new ArrayList<ResourceBean>();
	private LayoutInflater mInflater;
	private int mResource;

	public MyBaseadapter(Context context, List<ResourceBean> list) {
		mData = list;
		mInflater = LayoutInflater.from(context);

	}

	public int getCount() {
		return mData.size();
	}

	public Object getItem(int position) {
		return mData.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		Contorl cont = null;
		Log.e("VIEW对象", "新建的" + convertView);
		if (convertView == null ) {
			convertView = mInflater.inflate(R.layout.simple_adapter_layout,null);
			cont = new Contorl();
			cont.image = (ImageView) convertView
					.findViewById(R.id.simple_image);
			cont.titleText = (TextView) convertView
					.findViewById(R.id.simple_text);
			cont.conutText = (TextView) convertView
					.findViewById(R.id.simple_below_text);
			
			convertView.setTag(cont);

		} else {
			cont = (Contorl)convertView.getTag();
			Log.e("VIEW对象", "复用的的" + convertView);
		}

		ResourceBean resou = (ResourceBean) getItem(position);
		cont.image.setBackgroundResource(resou.getmImage());
		cont.titleText.setText(resou.getmTitle());
		cont.conutText.setText(resou.getmContent());

		return convertView;
	}

	class Contorl {
		ImageView image;
		TextView titleText;
		TextView conutText;
	}
}
