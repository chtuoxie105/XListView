package com.example.testone019.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.sax.StartElementListener;
import android.util.Log;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.testone005.mybaseadapter.s001.MybaseActivity;

/**
 * 广播(静态广播)——接收、过滤、并响应事件
 */
public class ReceiveBroadCast extends BroadcastReceiver {
	public String ACTION_SYATEM_SMS = "android.provider.Telephony.SMS_RECEIVED";
	public String ACTION_USER_BOOT = "android.intent.action.BOOT_COMPLETED";

	public void onReceive(Context context, Intent intent) {
		String s = intent.getAction();
		if (s.equals("android.provider.Telephony.SMS_RECEIVED")) {
			Toast.makeText(context, "短信广播监听..后台服务..静态", 0).show();
		}
		// else if (s.equals(MyBroadCast.ACTION_DEFINE)) {
		// Toast.makeText(context, "自定义广播....静态", 0).show();
		// }
		else if (s.equals(ACTION_USER_BOOT)) {
			Toast.makeText(context, "监听开机>1>动态注册", 0).show();
			Toast.makeText(context, "监听开机>2>动态注册", 0).show();
			Toast.makeText(context, "监听开机>3>动态注册", 0).show();
			Toast.makeText(context, "监听开机>4>动态注册", 0).show();
			Toast.makeText(context, "监听开机>5>动态注册", 0).show();
			Toast.makeText(context, "监听开机>6>动态注册", 0).show();
			Toast.makeText(context, "监听开机>7>动态注册", 0).show();
			Toast.makeText(context, "监听开机>8>动态注册", 0).show();
		
			Intent IntentService = new Intent(context,
					ActionUserBootService.class);
			IntentService.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startService(IntentService);
			abortBroadcast();//拦截不再向下传播
			Toast.makeText(context, "监听开机>监听短信>动态注册", 0).show();
		}

	}

}
