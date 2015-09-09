package com.example.testone022.servlet;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

/**
 * 
 * 三种泛型类型分别代表“启动任务执行的输入参数”、“后台任务执行的进度”、“后台计算结果的类型”。 在特定场合下，
 * 并不是所有类型都被使用，如果没有被使用，可以用java.lang.Void类型代替。
 *
 */
public class MyAsyncTask extends AsyncTask<String, Integer, String> {

	protected String doInBackground(String... params) {
		String httpurl = params[0];
		String str = null;
		// HttpPost连接对象

		try {
			HttpPost httpRequest = new HttpPost(httpurl);
			// 设置参数
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("one", ""));
			param.add(new BasicNameValuePair("options", "dododo"));
			param.add(new BasicNameValuePair("two", ""));
			// 设置字符集,以及编码方式
			HttpEntity httpEntity = new UrlEncodedFormEntity(param, "utf-8");
			// 请求HttpRequest
			httpRequest.setEntity(httpEntity);
			// 取得默认的HttpClient
			HttpClient httpClient = new DefaultHttpClient();
			// 取得HttpResponse
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			str = EntityUtils.toString(httpResponse.getEntity());
			// HttpStatus.SC_OK表示连接成功
			Log.v("gzg", "" + httpResponse.getStatusLine().getStatusCode());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return str;
	}

	protected void onPostExecute(String result) {
		// ((TextView) findViewById(R.id.a)).setText("异步操作执行结束"+result);
		// //异步操作执行完后，在TextView中显示出来，也可以执行别的操作。

	};

	protected void onProgressUpdate(Integer... values) {

	};
}
