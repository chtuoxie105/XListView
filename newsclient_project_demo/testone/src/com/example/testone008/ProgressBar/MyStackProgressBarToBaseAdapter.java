package com.example.testone008.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import com.example.testone001.loning.R;
import com.example.testone006.myGridview.MyGridView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyStackProgressBarToBaseAdapter extends BaseAdapter {
	private List<MyGridViewStack> list = new ArrayList<MyGridViewStack>();
	private LayoutInflater mInflater;
	public MyStackProgressBarToBaseAdapter(Context context) {
		
		mInflater = LayoutInflater.from(context);
	}
	public void setData(List<MyGridViewStack> list1){
		list = list1;
		//刷新数据源，并加载适配器
		notifyDataSetChanged();
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
			resou.mImage = (ImageView) convertView.findViewById(R.id.spinner_image);
			resou.mtext = (TextView) convertView.findViewById(R.id.spinner_text);
			convertView.setTag(resou);
		}else{
			resou = (Resous) convertView.getTag();
		}
		MyGridViewStack gridv = (MyGridViewStack) getItem(position);
		resou.mImage.setBackgroundResource(gridv.getmImage());
		resou.mtext.setText(gridv.getmText());
		
		return convertView;
	}
	class Resous{
		ImageView mImage;
		TextView mtext;
	}
}
