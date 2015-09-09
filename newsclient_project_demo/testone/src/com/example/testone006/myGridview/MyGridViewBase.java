package com.example.testone006.myGridview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testone001.loning.R;
/**
 * 自定义适配器用于  MyGridViewActivity()类.
 */
public class MyGridViewBase extends BaseAdapter{
	private List<MyGridView> list;
	private LayoutInflater mInflater;
	
	public MyGridViewBase(Context context,List<MyGridView> data){
		mInflater = LayoutInflater.from(context);
		list=data;
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
		Resou reso = null ;
		if(convertView == null){
	
			convertView = mInflater.inflate(R.layout.gird_view_two_layout, null);
			reso = new Resou();
			reso.mImage = (ImageView) convertView.findViewById(R.id.gridview_img);
			reso.mText = (TextView) convertView.findViewById(R.id.gridview_text);
			convertView.setTag(reso);
			
		}else{
			reso = (Resou) convertView.getTag();
		}
	
		MyGridView MyGrid = (MyGridView) getItem(position);
		reso.mImage.setBackgroundResource(MyGrid.getmImage());
		reso.mText.setText(MyGrid.getmText());
		
		return convertView;
	}
	
}
class Resou{
	ImageView mImage;
	TextView mText;
}
