package com.example.testone019.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testone001.loning.R;

/**
 * 1、借助于(IntentFilter)类进行动态广播的注册,注销。
 * 注册:registerReceiver()、销毁:unregisterReceiver().
 * 2、动态广播只在当前Activity里面有用，所以，注册和注销放在Activity的前置生命周期里面创建和销毁.
 * 3、即onResume()和onPause()里面 .
 * 4、广播的生命周期只有10秒左右的时间，所以不能处理繁琐的事件，否则会报错，
 * 5、只是负责对发送出来的广播进行过滤、接收、响应的组件，并不负责处理事件. 
 * 6、广播的对象是意图(intent).
 */
public class MyBroadCast extends Activity implements OnClickListener {
	private EditText mBroadCastEdit;
	private Button mStartBroadCastBtn, mCancelBroadCastBtn;
	public static final String ACTION_DEFINE = "android.content.BroadcastReceiver.MyBroadCast";
	public String ACTION_DEFINE_TRENDS = "android.content.BroadcastReceiver.trends";
	public String ACTION_SYATEM_SMS = "android.provider.Telephony.SMS_RECEIVED";
	public String ACTION_USER_BOOT = "android.intent.action.BOOT_COMPLETED";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_broadcast_layout);

		mBroadCastEdit = (EditText) findViewById(R.id.broadcast_edittext);
		mStartBroadCastBtn = (Button) findViewById(R.id.start_broadcast_btn);
		mCancelBroadCastBtn = (Button) findViewById(R.id.cancel_broadcast_btn);

		mStartBroadCastBtn.setOnClickListener(this);
	}

	/**
	 * 按钮的监听处理事件
	 */
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start_broadcast_btn:
			Intent intent = new Intent(ACTION_DEFINE);
			// sendBroadcast(intent);
			sendOrderedBroadcast(intent, null);
			Log.i("11", "点击成功");
			break;
		}
	}

	/**
	 * 封装动态注册
	 */
	public void trendsRegister(String s) {
		IntentFilter filter = new IntentFilter();
		filter.addAction(s);
		registerReceiver(receiver, filter);
	}

	/**
	 * 注册广播
	 */
	protected void onResume() {
		super.onResume();
		trendsRegister(ACTION_DEFINE_TRENDS);// 自定义动态注册
		trendsRegister(ACTION_SYATEM_SMS);// 自定义动态注册短信监听
		trendsRegister(ACTION_DEFINE);// 自定义广播，有序发送
		trendsRegister(ACTION_USER_BOOT);// 自定义广播，有序发送
	}
	/**
	 * 动态注册广播的接收
	 */
	BroadcastReceiver receiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(ACTION_DEFINE_TRENDS)) {
				Toast.makeText(context, "动态注册广播>自定义", 0).show();
			} else if (action.equals(ACTION_SYATEM_SMS)) {
				Toast.makeText(context, "动态注册广播监听短信>自定义", 0).show();
				abortBroadcast();// 终止广播的继续发送
			} else if (action.equals(ACTION_DEFINE)) {
				Toast.makeText(context, "自定义广播>.>动态注册", 0).show();
				abortBroadcast();// 终止广播的继续发送
			} else if (action.equals(ACTION_USER_BOOT)) {
				Toast.makeText(context, "监听开机>>动态注册", 0).show();
				trendsRegister(ACTION_SYATEM_SMS);// 自定义动态注册短信监听

				Intent service = new Intent(MyBroadCast.this,
						ActionUserBootService.class);
				startService(service);
				Toast.makeText(context, "监听开机>监听短信>动态注册", 0).show();
			}
		}
	};

	/**
	 * 注销动态广播
	 */
	protected void onPause() {
		if (receiver != null) {
			unregisterReceiver(receiver);
			receiver = null;
		}
	};
}
