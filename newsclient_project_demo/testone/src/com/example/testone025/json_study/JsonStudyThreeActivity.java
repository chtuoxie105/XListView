package com.example.testone025.json_study;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.testone024.Http_callback.AsynctaskAndHttpClientToolClass;

public class JsonStudyThreeActivity extends Activity {
	AsynctaskAndHttpClientToolClass mAsynctaskAndHttpClientToolClass;
	String url = "http://baidu.com";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAsynctaskAndHttpClientToolClass = new AsynctaskAndHttpClientToolClass();

	}

	public void writeJson() {
		mAsynctaskAndHttpClientToolClass.execute(url, null, "GET",
				new AsynctaskAndHttpClientToolClass.NotifiReceive() {
					public void backContent(String backString) {
						Log.i("11", "返回的数据>>>" + backString);
					}
				});
	}

	public static String createJsonString(Object value) {
		String alibabaJson = JSON.toJSONString(value);
		return alibabaJson;
	}

}
