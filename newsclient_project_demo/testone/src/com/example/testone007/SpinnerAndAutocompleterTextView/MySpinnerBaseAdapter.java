package com.example.testone007.SpinnerAndAutocompleterTextView;

import java.util.List;

import com.example.testone001.loning.R;
import com.example.testone006.myGridview.MyGridView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySpinnerBaseAdapter extends BaseAdapter {
	private List<MyGridView> list;
	private LayoutInflater mInflater;
	public MySpinnerBaseAdapter(Context context,List<MyGridView> lists) {
		list = lists;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		Resous resou ;
		if(convertView == null ){
			convertView = mInflater.inflate(R.layout.spinner_two_layout, null);
			resou = new Resous();
			resou.mImage = (ImageView) convertView.findViewById(R.id.simple_image);
			resou.mtext = (TextView) convertView.findViewById(R.id.spinner_text);
			convertView.setTag(resou);
		}else{
			resou = (Resous) convertView.getTag();
		}
		MyGridView gridv = (MyGridView) getItem(position);
		resou.mImage.setBackgroundResource(gridv.getmImage());
		resou.mtext.setText(gridv.getmText());
		
		return convertView;
	}
	class Resous{
		ImageView mImage;
		TextView mtext;
	}
}
