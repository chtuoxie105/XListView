package com.example.testone010.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testone001.loning.R;

public class MyAlertDialog extends AlertDialog {
	public Button mYesBtn, mNoBtn;
	private LayoutInflater mInfalter;
	private Context mContext;

	protected MyAlertDialog(Context context) {
		super(context);

		mContext = context;
		mInfalter = LayoutInflater.from(context);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		View v = mInfalter.inflate(R.layout.activity_alert_dialog_layout, null);
//		setContentView(v);
		setContentView(R.layout.activity_alert_dialog_layout);
		
		mYesBtn = (Button) v.findViewById(R.id.alert_yes_btn);
		mNoBtn = (Button) v.findViewById(R.id.alert_no_btn);

		mYesBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(mContext, "退出成功", 1).show();
				dismiss();//销毁对话框

			}
		});
		mNoBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(mContext, "取消成功", 1).show();
				dismiss();

			}
		});
	}
}
