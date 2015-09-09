package com.example.testone010.Dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.testone001.loning.R;

public class MyDialogActivity extends Activity {
	private Button mProgressBtn, mDataBtn, mTimeBtn;
	private ProgressDialog budlliProg;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_dialog_layout);

		mProgressBtn = (Button) findViewById(R.id.dialog_main_progress_btn);
		mDataBtn = (Button) findViewById(R.id.dialog_main_data_btn);
		mTimeBtn = (Button) findViewById(R.id.dialog_main_time_btn);

		budlliProg = new ProgressDialog(MyDialogActivity.this);
		budlliProg.setTitle("进度条窗口");
		budlliProg.setMessage("信息加载中...");
		budlliProg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 风格，不设置就是默认的格式

		mProgressBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				budlliProg.show();

				new Thread(new Runnable() {
					public void run() {
						int n = 0;
						try {
							while (n < 100) {
								Thread.sleep(100);
								n++;
								// budlliProg.incrementProgressBy(10);
								budlliProg.setProgress(n);

							}

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();

			}
		});
		mDataBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String[] databaseList = {};

				// DatePickerDialog dateDislog= new DatePickerDialog(this,
				// databaseList, 2015, 7, 1);

			}
		});
		mTimeBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

			}
		});
	}

	// 监听自定义的微信退出对话框
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			MyAlertDialog dialog = new MyAlertDialog(this);

			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = size.x;
			
			int px = width -20;
			
			
			dialog.show();
			
			
			
		}
		return true;
	}

}
