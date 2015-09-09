package com.example.testone025.json_study;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.testone001.loning.R;

public class JsonStudyOneActivity extends Activity implements OnClickListener {
	private Button mGetJSONBtn, mResolveJsonBtn;
	private TextView mGetJSONContentText, mResolveJsonContentText;
	private String URL = "http://www.weather.com.cn/data/sk/101010100.html";
	
	String backContent = "";
	private Handler hander = new Handler(){
		public void handleMessage(android.os.Message msg) {
			int n = msg.what;
			switch (n) {
			case 1:
				
				String sss = msg.toString();
				mGetJSONContentText.setText(sss);
				break;
			}

		}
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_json_study_layout);
		mGetJSONBtn = (Button) findViewById(R.id.get_json_match_stydy);
		mResolveJsonBtn = (Button) findViewById(R.id.resolve_json_match);
		mGetJSONContentText = (TextView) findViewById(R.id.get_json_content);
		mResolveJsonContentText = (TextView) findViewById(R.id.get_resolve_json_content);

		mGetJSONBtn.setOnClickListener(this);
		mResolveJsonBtn.setOnClickListener(this);
	}
	/**
	 * 监听事件一：联网取数据采用异步处理，刷新显示，数据采用hander消息机制，主线程刷新        
	 * 监听事件二：用了个全局变量String(backContent)，去获得返回的内容，然后再进行分解成具体的需求.
	 */
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.get_json_match_stydy:
			new AsyncTask<String, Void, String>() {

				protected String doInBackground(String... params) {
					String s1 = params[0];
					String backStr = httpGetData(s1);
					Log.i("11",backStr);
					
					
					Message msg = hander.obtainMessage();
					msg.obj = backStr;
					msg.what = 1;
					hander.sendMessage(msg);
					return backStr;

				}

				protected void onPostExecute(String result) {
					super.onPostExecute(result);
					backContent = result;

				}
			}.execute(URL);

			break;

		case R.id.resolve_json_match:
			resolveContent(backContent);
			break;
		}
	}
	/**
	 * 取得具体需要的数据，用于显示
	 * @param ss
	 */
	public void resolveContent(String ss){
		try {
			JSONObject jsonObj = new JSONObject(ss);

			JSONObject json = jsonObj.getJSONObject("weatherinfo");
			String city = json.getString("city");
			String cityid = json.getString("cityid");
			String wind = json.getString("WD");
			String time = json.getString("time");

			mResolveJsonContentText.setText("城市:" + city + "  城市ID"
					+ cityid + "  时间" + time + "  风力:" + wind);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 初步练习
	 */
	public void testHson() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "张三");
		map.put("password", "121314");
		JSONObject json = new JSONObject(map);
		String jsonString = json.toString();
		Log.i("11","MAP转换成JSON对象"+jsonString);
	}
	/**
	 * 自己写JSON格式，JSON对象添加数据PUT（）方法
	 */
	public void creatJson() {
		try {
			JSONObject json = new JSONObject();
			json.put("name", "麻子");
			json.put("passeord", "121314");
			
			Log.i("11", json.toString());

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get请求联网，求json数据：气象
	 * 
	 */
	public String httpGetData(String url) {
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);

			int n = response.getStatusLine().getStatusCode();
			if (n == HttpStatus.SC_OK) {
				String s = EntityUtils.toString(response.getEntity());
				return s;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
