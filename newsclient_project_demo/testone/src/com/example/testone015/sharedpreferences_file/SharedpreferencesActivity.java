package com.example.testone015.sharedpreferences_file;

import java.util.ArrayList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.testone001.loning.R;

public class SharedpreferencesActivity extends Activity {
	private EditText mEditShared;
	private Button mConfirmBtnShared;
	private String NAME = "com.example.edit_name_to_sharedpreferences";// 文件名
	private SharedPreferences shared;
	private String[] str = { "nameone", "nametwo", "namethree", "namefour",
			"namefive", "namesix", "nameseven" };
	ArrayList<String> list = new ArrayList<String>();
	int i = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sharedpreferences_layout);

		mEditShared = (EditText) findViewById(R.id.main_sharedpreferences_edit);
		mConfirmBtnShared = (Button) findViewById(R.id.main_sharedpreferences_btn);

		//创建shared_prefs文件夹(data-->data-->文件下面)
		shared = getSharedPreferences(NAME, MODE_PRIVATE);

		for (int n = 0; n < str.length; n++) {
			list.add(str[n]);
		}

		
			mConfirmBtnShared.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					//以键值对的方式存数据，借助于Editor的putString()方法；commit()提交存储的信息;clear()删除存储的数据remove(key)通过键名移除
					Editor edits = shared.edit();
					edits.putString(list.get(i), mEditShared.getText()
							.toString());
					edits.commit();
				
					String s = shared.getString(list.get(0), "");
					Log.e("11aaa", "数据个数: "+s);
					i++;
				}
			});
			
			//将上一次输入的信息发送出来
			String s = shared.getString(list.get(0), "");
			if (!s.equals("")) {
				mEditShared.setText(s);
				Log.e("11", s);
			
		}
	}
}
