package com.example.testone024.Http_callback;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.testone001.loning.R;

public class HttpToolActivity extends Activity implements OnItemClickListener {
	private ListView httpStudyList;
	private EditText mHttpNameText, mHttpPasswordText;
	AsynctaskAndHttpClientToolClass mAsynctaskAndHttpClientToolClass;
	String urls = "http://192.168.1.191:8080/WebRoot/wanmei.html";
	HashMap<String, Object> map = new HashMap<String, Object>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http_tool_main_layout);

		mAsynctaskAndHttpClientToolClass = new AsynctaskAndHttpClientToolClass();

		String[] s = { "get连接", "post连接" };
		httpStudyList = (ListView) findViewById(R.id.http_tool_listview);
		mHttpNameText = (EditText) findViewById(R.id.http_tool_name_text);
		mHttpPasswordText = (EditText) findViewById(R.id.http_tool_password_text);
		map.put("name", mHttpNameText.getText().toString());
		map.put("password", mHttpPasswordText.getText().toString());

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, s);
		httpStudyList.setAdapter(adapter);

		httpStudyList.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:

			if (map.size() > 0 && !urls.equals("")) {
				mAsynctaskAndHttpClientToolClass.execute(urls, map, "GET",
						new AsynctaskAndHttpClientToolClass.NotifiReceive() {
							public void backContent(String backString) {
								Log.i("11", backString);
								startActivity(new Intent(HttpToolActivity.this,
										WedingActivity.class));
							}
						});
			}
			break;
		case 1:
			if (map.size() > 0 && !urls.equals("")) {
				mAsynctaskAndHttpClientToolClass.execute(urls, map, "POST",
						new AsynctaskAndHttpClientToolClass.NotifiReceive() {
							public void backContent(String backString) {
								Log.i("11", backString);
								startActivity(new Intent(HttpToolActivity.this,
										WedingActivity.class));

							}
						});
			}
			break;
		}

	}
}
