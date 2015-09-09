package com.example.testone023.httpstudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

/**
 * http 3种连网模式
 */
public class HttpStudyConnect {
	public String httpClient(String url) {
		String str = null;
		InputStream input = null;
		BufferedReader reader = null;
		try {
			// HttpClient
			HttpClient httpClient = new DefaultHttpClient();
			// HttpGet创建get请求方式
			HttpGet httpGet = new HttpGet(url);
			// 取得HttpResponse,进行get请求
			HttpResponse response = httpClient.execute(httpGet);
			// 获取响应内容
			// str = EntityUtils.toString(response.getEntity());
			// Log.i("11", "返回的>>>:" + str);
			// HttpStatus.SC_OK 封装了过后的返回码，值时200

			int n = response.getStatusLine().getStatusCode();
			Log.i("11", "返回码>>>:" + n);
			if (n == HttpStatus.SC_OK) {

				input = response.getEntity().getContent();
				reader = new BufferedReader(new InputStreamReader(input,
						HTTP.UTF_8));

				StringBuilder builder = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				// String nsns =
				// EntityUtils.toString(response.getEntity(),"UTF-8");封装了读流的过程
				return builder.toString();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {

					input.close();
					input = null;
				}
				if (reader != null) {
					reader.close();
					reader = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/***
	 * java模式连接网络
	 */
	public String JavaConnectHttp(String s) {
		InputStream input = null;
		BufferedReader readerBuffer = null;
		try {
			URL url = new URL(s);
			HttpURLConnection httpConnect = (HttpURLConnection) url
					.openConnection();
			//
			httpConnect.setDoInput(true);
			// 发起get请求
			httpConnect.setRequestMethod("GET");
			// 设置允许输入流
			httpConnect.connect();
			int length = httpConnect.getContentLength();
			Log.i("11", "返回的>>>" + length);
			input = httpConnect.getInputStream();
			readerBuffer = new BufferedReader(new InputStreamReader(input,
					HTTP.UTF_8));
			
			String ctByte = null;

			StringBuilder builder = new StringBuilder();
			while ((ctByte = readerBuffer.readLine()) != null) {
				builder.append(ctByte);
			}
			return builder.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
					input = null;
				}
				if (readerBuffer != null) {
					readerBuffer.close();
					readerBuffer = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 发送 post请求
	 */
	public String postHttpClient(String addressUrl) {
		try {
			HttpPost httpPost = new HttpPost(addressUrl);
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("myServletName", "张瑜崳渝子"));
			list.add(new BasicNameValuePair("myServletPassword", "123321"));
		
			// 将封装参数的对象存入request中，并设置编码方式
			HttpEntity httpEntity = new UrlEncodedFormEntity(list, "utf-8");
			httpPost.setEntity(httpEntity);
			//如果服务端处理了，就不用加此编码处理
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			// 发起Post请求
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpPost);

			// 获得请求码
			int n = response.getStatusLine().getStatusCode();
			if (n == HttpURLConnection.HTTP_OK) {
				// 封装了读流的过程
				String line = EntityUtils.toString(response.getEntity(),"UTF-8");
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
