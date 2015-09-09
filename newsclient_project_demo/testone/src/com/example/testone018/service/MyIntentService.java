package com.example.testone018.service;

import android.app.IntentService;
import android.content.Intent;
/**
 * IntentService 里面已经封装好了一个线程.
 * @author Administrator
 *
 */
public class MyIntentService extends IntentService {

	public MyIntentService(String name) {
		super(name);
		
	}


	protected void onHandleIntent(Intent intent) {
		

	}

}
