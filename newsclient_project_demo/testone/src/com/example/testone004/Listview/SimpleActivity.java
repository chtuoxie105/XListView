package com.example.testone004.Listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.testone001.loning.R;

public class SimpleActivity extends Activity {

	private ListView mSimpleList;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.simple_linear_layout);

		mSimpleList = (ListView) findViewById(R.id.simple_linear_text_view);
		
		String[] from = new String[] { "img", "title", "titlebelow" };
		int[] to = new int[] { R.id.simple_image, R.id.simple_text,
				R.id.simple_below_text };

		SimpleAdapter adapter = new SimpleAdapter(this, getdata(),
				R.layout.simple_adapter_layout, from, to);
		mSimpleList.setAdapter(adapter);
		
		mSimpleList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					
			}
		});
	}

	public List<HashMap<String, Object>> getdata() {
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("img", R.drawable.wmgj);
		map.put("title", "西北大葱");
		map.put("titlebelow", "不干了，跳楼大减价,完美国际大片");
		data.add(map);

		map = new HashMap<String, Object>();
		map.put("img", R.drawable.simple1);
		map.put("title", "【成都】太平洋电影城");
		map.put("titlebelow", "2D观影票1张，3D影片需加10元");
		data.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img", R.drawable.simple2);
		map.put("title", "【元坝区】昭化古城门票");
		map.put("titlebelow", "仅售39元！价值58元的昭化古城门票，休闲时刻，共享欢乐.");
		data.add(map);
		
		return data;
	}
}
