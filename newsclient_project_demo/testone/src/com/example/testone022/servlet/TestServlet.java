package com.example.testone022.servlet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.testone001.loning.R;

public class TestServlet extends Activity {
	private WebView mWebView;
	String urlMine = "http://192.168.1.191:8080/Html/wanmei.html";

	@SuppressLint("JavascriptInterface")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.servlet_activity_layout);
		
		mWebView = (WebView) findViewById(R.id.servlet_webview_main);
		mWebView.loadUrl(urlMine);

		WebSettings setting = mWebView.getSettings();
		setting.setJavaScriptEnabled(true);
		mWebView.addJavascriptInterface(new ConnectHtml(), "androidConnectHtml");
	}

	public class ConnectHtml {
		public void androidPlayMusic() {

			Toast.makeText(TestServlet.this, "互调成功", 0).show();
		}

		/** 返回一个url地址给html文件里面的坚挺事件 */
		public String androidToHtmlUrl() {
			String url = "http://reg.jiayuan.com";
			return url;
		}

		/** 调用html里面的方法：先是html里面的一个控件调用次方法，然后用此方法调用html里面的方法，直接从activity跳转其他的网页 */
		public void toneHtmlMain() {
			mWebView.loadUrl("javascript:toneHtmlMain()");
		}
	}
}
