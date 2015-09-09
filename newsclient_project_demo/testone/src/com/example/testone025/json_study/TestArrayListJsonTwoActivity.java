package com.example.testone025.json_study;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.testone001.loning.R;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

public class TestArrayListJsonTwoActivity extends Activity {
	private ListView mListView;
	private ProgressBar mProgressBar;
	String url = "http://192.168.1.203/json/json_x";
	List<HashMap<String, String>> mJsonListToMapList;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json_arraylist_layout);
		mListView = (ListView) findViewById(R.id.json_list_arraylist);
		mProgressBar = (ProgressBar) findViewById(R.id.json_before_progressbar);

		mJsonListToMapList = new ArrayList<HashMap<String, String>>();

		// 采用了异步任务去联网取数据，否则可能堵塞主线程
		new AsyncTask<String, Void, String>() {
			protected String doInBackground(String... params) {
				try {
					String banckStr = getHttpToJson(params[0]);
					JSONArray jsonList = new JSONArray(banckStr);
					
					for (int i = 0; i < jsonList.length(); i++) {
						JSONObject jsonObject = jsonList.getJSONObject(i);
						String title = jsonObject.getString("title");
						String description = jsonObject
								.getString("description");
						String image = jsonObject.getString("image");
						String comment = jsonObject.getString("comment");
						Log.i("11", "地址>>:" + image);
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("title", title);
						map.put("description", description);
						map.put("comment", comment + "条评论");
						mJsonListToMapList.add(map);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return null;
			}
		}.execute(url);
		/**
		 * 弄了一个适配器，添加到ListView控件上,注意的是：在适配器里面添加ArrayList的时候可能List数组为空，
		 * 因为主线程和子线程的时间并不能确定
		 * .如果你用自己封装的网络取数据的类，出现空指针，就把给适配器设置数据源的那段代码放到遍历Json循环外面那。
		 */
		//ProgressBar模拟加载界面，让子线程去网络取数据，取完就隐藏ProgressBar
		mProgressBar.setVisibility(View.GONE);
		SimpleAdapter adapter = new SimpleAdapter(this, mJsonListToMapList,
				R.layout.listview_json_arraylist_layout, new String[] {
						"title", "description", "comment" }, new int[] {
						R.id.json_arraylist_title, R.id.json_arraylist_content,
						R.id.json_arraylist_number });
		mListView.setAdapter(adapter);

	}

	/**
	 * 进行Get请求网络连接，返回JSON格式的数据源
	 */
	public String getHttpToJson(String abc) {
		try {
			HttpClient httpClient = new DefaultHttpClient();
			// HttpGet创建get请求方式
			HttpGet httpGet = new HttpGet(abc);
			// 取得HttpResponse,进行get请求
			HttpResponse response = httpClient.execute(httpGet);
			// HttpStatus.SC_OK 封装了过后的返回码，值时200
			int n = response.getStatusLine().getStatusCode();
			//判断返回码，200才是连接成功的标识
			if (n == HttpStatus.SC_OK) {
				String line = EntityUtils.toString(response.getEntity(),
						"UTF-8");
				return line;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
