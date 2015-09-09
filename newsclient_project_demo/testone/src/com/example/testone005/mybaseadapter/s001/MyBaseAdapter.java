package com.example.testone005.mybaseadapter.s001;

import java.util.ArrayList;
import java.util.List;

import com.example.testone001.loning.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter {
	
	private List<Resource> list = new ArrayList<Resource>();
	private LayoutInflater inflater;
	public MyBaseAdapter(Context context,List<Resource> lists){
		
		list = lists;
		inflater = LayoutInflater.from(context);
	}
	//返回数据源的个数
	public int getCount() {
		return list.size();
	}

	//返回那一行对象
	public Object getItem(int position) {
		
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
	
		return position;
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		
		Text resou = null;
		if(convertView == null){
		convertView = inflater.inflate(R.layout.activity_linear_layout, null);
		resou = new Text();	
		resou.mTextTop = (TextView) convertView.findViewById(R.id.all_text_view);
			
		convertView.setTag(resou);
		}else{
			resou = (Text) convertView.getTag();
		}
		
		Resource soue = (Resource) getItem(position);
		resou.mTextTop.setText(soue.getmTitleText());
	
		
		return convertView;
	}
class Text{	
	TextView mTextTop;
}
}
