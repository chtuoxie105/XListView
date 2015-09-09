package com.example.testone009.Popwindow001;

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

public class PopupWindowBaseadapter extends BaseAdapter {
	private List<PopWindowBean> list = new ArrayList<PopWindowBean>();
	private LayoutInflater mInfalter;
	public PopupWindowBaseadapter(Context context){
		mInfalter = LayoutInflater.from(context);
	}
	
	public void setData(List<PopWindowBean> lists){
		list = lists;
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
		Reasou rea;
		if(convertView == null){
			convertView = mInfalter.inflate(R.layout.activity_popwindow_view,null);
			rea = new Reasou();
			rea.mImageRea = (ImageView) convertView.findViewById(R.id.popwindow_listview_image);
			rea.mTextRea = (TextView) convertView.findViewById(R.id.popwindow_listview_text);
			convertView.setTag(rea);
		}else{
			rea = (Reasou) convertView.getTag();
		}
		
		PopWindowBean popBean = (PopWindowBean) getItem(position);
		rea.mImageRea.setBackgroundResource(popBean.getmImagePop());
		rea.mTextRea.setText(popBean.getmTextPop());
		
		return convertView;
	}
	class Reasou{
		ImageView mImageRea;
		TextView mTextRea;
	}
}
