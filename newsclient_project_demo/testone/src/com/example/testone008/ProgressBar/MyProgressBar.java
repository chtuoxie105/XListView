package com.example.testone008.ProgressBar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.testone001.loning.R;

public class MyProgressBar extends Activity {
	private ProgressBar mProgressBar;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.progressbar_layout);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		
		new Thread(new Runnable() {
			int h =50;
			public void run() {
				
					try {
						for (int i = 0; i < 10; i++) {
						Thread.sleep(1000);
						mProgressBar.setProgress(++h);
					//	 mProgressBar.incrementProgressBy(10);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		}).start();

	}
}
