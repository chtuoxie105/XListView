package com.example.testone020.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.testone001.loning.R;
import com.example.testone012.TabActivity.MyTabHost;
import com.example.testone019.broadcast.ActionUserBootService;

public class NotificationActivity extends Activity implements OnClickListener {
	private Button mSendBtn, mUpdataBtn, mStopAppBtn, mProgressBarBtn,
			mMineBtn;
	private NotificationManager manger;
	private int RENEW_ID = 001;// 更新用的
	private int RENEW_ID_TO_SERVICE = 10;// 点击后停留在APP中的int，便于更新
	private int RENEW_ID_STOP = 20;// 点击后停留在APP中的int，便于更新
	private int RENEW_ID_PROGRESSBAR = 30;// 点击后停留在APP中的int，便于更新

	private Handler mHandler = new Handler();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_layout);
		mSendBtn = (Button) findViewById(R.id.notification_send_message);
		mUpdataBtn = (Button) findViewById(R.id.notification_updata_message);
		mStopAppBtn = (Button) findViewById(R.id.notification_stopapp_message);
		mProgressBarBtn = (Button) findViewById(R.id.notification_progressbar_message);
		mMineBtn = (Button) findViewById(R.id.notification_mine_message);
		// 实例化通知管理者，调此它的刷新方法>去刷新通知
		manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		mSendBtn.setOnClickListener(this);// 点击显示自己写一些属性，普通的通知
		mUpdataBtn.setOnClickListener(this);// 点击显示写一个启动服务的通知
		mStopAppBtn.setOnClickListener(this);// 点击显示一个通知，点进去后就停在这个APP里面，不会因为点击返回键就退出来
		mProgressBarBtn.setOnClickListener(this);// 点击显示一个通知，显示内容是一个进度条
		mMineBtn.setOnClickListener(this);// 点击显示一个通知，显示内容是一个进度条

	}

	/**
	 * 按钮的监听
	 */
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.notification_send_message:
			SystemNotificationTest();
			break;

		case R.id.notification_updata_message:
			StartServiceNotification();
			break;
		case R.id.notification_stopapp_message:
			NotificationStopTOAPP();
			break;
		case R.id.notification_progressbar_message:
			pregressBarNotification();
			break;
		case R.id.notification_mine_message:
			createNotification();
			break;
		}
	}

	/**
	 * 方法一:初次学习通知里面的属性，系统的界面通知>>模拟短信通知
	 */
	public void SystemNotificationTest() {
		// 构造通知，并设置通知里面在状态栏显示的提醒信息，显示给用户界面的图片、标题、内容、时间.点击就消失通知等....
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this);
		builder.setSmallIcon(R.drawable.m3);
		builder.setContentTitle("短信通知");
		builder.setContentText("你有1条未读短信，请点击查看");
		// builder.setAutoCancel(true);// 点击消失
		builder.setOngoing(true);// 设置为不可清除模式,流氓式通知
		builder.setTicker("你有新消息了!");// 任务栏里提示
		int kk = RENEW_ID++;
		builder.setNumber(kk);// 显示几条信息
		builder.setWhen(System.currentTimeMillis());// 显示当前时间

		Intent intentService = new Intent(this, MyTabHost.class);
		startService(intentService);

		PendingIntent pending = PendingIntent.getActivity(this, 0,
				intentService, PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(pending);
		// 刷新通知
		manger.notify(RENEW_ID, builder.build());
	}

	/**
	 * 方法二:穿插的去启动一个后台服务service
	 */
	public void StartServiceNotification() {
		// 构造通知，并设置通知里面在状态栏显示的提醒信息，显示给用户界面的图片、标题、内容、时间.点击就消失通知等....
		NotificationCompat.Builder builderService = new NotificationCompat.Builder(
				this);
		builderService.setSmallIcon(R.drawable.m3);
		builderService.setTicker("启动了一个后台服务");
		builderService.setContentTitle("启动服务通知");
		builderService.setContentText("启动了一个短信监听的通知");
		builderService.setWhen(System.currentTimeMillis());// 显示当前的时间
		builderService.setAutoCancel(true);// 点击后取消此通知
		// 启动服务
		Intent intentServ = new Intent(this, ActionUserBootService.class);
		intentServ.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startService(intentServ);

		// 实例化PendingIntent的方法中的四个属性(上下文，不知道直接放0就行，Intent,直接用PendingIntent点，里面有4个属性);
		PendingIntent pendingService = PendingIntent.getService(this, 0,
				intentServ, PendingIntent.FLAG_UPDATE_CURRENT);
		// 将此通知设置进去，PendingIntent和Intent的属性一样，但是不是立即跳转，未决的
		builderService.setContentIntent(pendingService);

		// 用途:刷新通知
		manger.notify(RENEW_ID_TO_SERVICE, builderService.build());
	}

	/**
	 * 方法三:点击通知后，进入跳转的界面，然后点击返回键就停留在那个App里面，不会直接退出来
	 */
	public void NotificationStopTOAPP() {
		NotificationCompat.Builder builderStop = new NotificationCompat.Builder(
				this);
		builderStop.setSmallIcon(R.drawable.m3);
		builderStop.setContentTitle("微信通知");
		builderStop.setContentText("你的好友xxx给你发送了一条消息!");
		builderStop.setTicker("你的微信发来一条消息通知!");
		builderStop.setAutoCancel(true);// 设置点击后取消通知

		Intent nextIntent = new Intent(this, MyTabHost.class);
		startActivity(nextIntent);
		// 停留在那个APP应用里
		TaskStackBuilder taskstack = TaskStackBuilder.create(this);
		taskstack.addParentStack(MyTabHost.class);
		taskstack.addNextIntent(nextIntent);

		PendingIntent pengdingStop = taskstack.getPendingIntent(0,
				PendingIntent.FLAG_CANCEL_CURRENT);
		builderStop.setContentIntent(pengdingStop);
		manger.notify(RENEW_ID_STOP, builderStop.build());
	}
	/**
	 * 方法五:自定义通知
	 */
	public void createNotification() {
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this);
		
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setTicker("欢迎使用播放器");// 任务栏里提示
//		builder.setOngoing(true);// 设置为不可清除模式,流氓式通知
		
		// ------自定义notification界面
		RemoteViews remoteView = new RemoteViews(getPackageName(),
				R.layout.activity_sharedpreferences_layout);
		builder.setContent(remoteView);
		//自定义通知里面按钮的事件处理
//		remoteView.setOnClickPendingIntent(R.id.main_sharedpreferences_btn, pendingIntent);
		// ----自定义notification事件处理
		Intent intent = new Intent(this, MyTabHost.class);
		
		PendingIntent pendIntent = PendingIntent.getActivity(this, 01, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		remoteView.setOnClickPendingIntent(R.id.main_sharedpreferences_btn, pendIntent);
		
//		builder.setContentIntent(pendIntent);
		int notificationId= 7;// 版本更新用
		NotificationManager manger6 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manger6.notify(notificationId, builder.build());
	
	}

	/**
	 * 方法四:显示一个进度条的通知，并且把进度条的加载放到主程序去执行，才不会影响你在界面里进行其他的操作，
	 */
	public void pregressBarNotification() {
		final NotificationCompat.Builder progressBarActionBuilder = new NotificationCompat.Builder(
				this);
		progressBarActionBuilder.setTicker("XX已加入下载!")
				.setSmallIcon(R.drawable.m3).setContentTitle("XX正在下载...")
				.setContentText("正在下载，请稍后....")
				.setWhen(System.currentTimeMillis());
		progressBarActionBuilder.setAutoCancel(true);
		new Thread(new Runnable() {
			public void run() {
				for (int progress = 0; progress < 101; progress++) {
					try {
						Thread.sleep(100);
						progressBarActionBuilder.setProgress(100, progress,
								false);

						// 刷新消息最好放到主线程去执行
						mHandler.post(new Runnable() {
							public void run() {
								manger.notify(RENEW_ID_PROGRESSBAR,
										progressBarActionBuilder.build());
							}
						});

					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				// 刷新消息最好放到主线程去执行
				mHandler.post(new Runnable() {
					public void run() {
						progressBarActionBuilder.setProgress(0, 0, false);
						progressBarActionBuilder.setContentTitle("下载完成!");
						progressBarActionBuilder.setContentText("请点击安装");
						manger.notify(RENEW_ID_PROGRESSBAR,
								progressBarActionBuilder.build());
					}
				});
			}
		}).start();

		// //模拟下载完成后发布通知信息
		//
		// manger.notify(RENEW_ID_PROGRESSBAR,
		// progressBarActionBuilder.build());
		//

	}
}
