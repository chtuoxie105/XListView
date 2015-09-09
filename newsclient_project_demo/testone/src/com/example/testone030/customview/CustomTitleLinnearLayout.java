package com.example.testone030.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.testone001.loning.R;

public class CustomTitleLinnearLayout extends LinearLayout {

	public CustomTitleLinnearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(
				R.layout.all_activity_title_andfor_custom_view_layout, this);
		
		Button buttonBack = (Button) findViewById(R.id.custom_titlelayout_btn);
		
		buttonBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
					Toast.makeText(getContext(),"自定义布局的返回按键",0).show();
			}
		});
		
	}
}
