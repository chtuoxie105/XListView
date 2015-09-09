package com.example.testone018.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * service--组件之-->服务
 * 
 * @author Administrator
 *
 */
public class MyService extends Service {
	private Boolean temp = true;
	private int mCount;

	public interface OnContents {
		public void setContents(int content);
		public int getContents();
		public void switchThread(boolean n);
	}
/**
 * 
 * @author Administrator
 *
 */
	public class RealizeOnContent extends Binder implements OnContents {
		public void setContents(int content) {
			mCount = content;

		}

		public int getContents() {

			return mCount;
		}

	
		public void switchThread(boolean n) {
			temp = n;
		
		}
	}

	private RealizeOnContent mRealizeOnContent = new RealizeOnContent();

	public IBinder onBind(Intent intent) {

		return mRealizeOnContent;
	}

	public void onCreate() {
		super.onCreate();
		
		new Thread(new Runnable() {			
			public void run() {
				while (true) {
					if(temp){
						mCount++;
						try {
							Thread.sleep(1000);
							Log.i("11", "计数器:" + mCount);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("11", "销毁>onDestroy>>");
		
		temp = false;
	}
}
