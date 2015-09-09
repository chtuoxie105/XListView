package com.example.testone022.servlet;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.testone001.loning.R;

public class MyAsynctaskActivity extends Activity {
	private ProgressBar mPregressBar;
	MyAsyncTask mTask;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asynctask_layout);

		mPregressBar = (ProgressBar) findViewById(R.id.asynctask_progressbar);

		// 注意每次需new一个实例,新建的任务只能执行一次,否则会出现异常
		mTask = new MyAsyncTask();
		mTask.execute("http://www.sina.com.cn/");

		new AsyncTask<Void, Integer, Boolean>() {
			
			protected void onPreExecute() {
				//预处理，第一个执行
			};
			
			protected Boolean doInBackground(Void... params) {
				for (int i = 0; i < 100; i++) {
					publishProgress(i);
				
				}
				return true;
			}

			protected void onProgressUpdate(Integer... values) {
			//	onProgressUpdate(Progress... values)，在调用publishProgress(Progress... values)时，
			//	此方法被执行，直接将进度信息更新到UI组件上。
				int n = values[0];
				mPregressBar.setProgress(n);
			};

			protected void onPostExecute(Boolean result) {
				super.onPostExecute(result);
				if (result == true) {
					Toast.makeText(MyAsynctaskActivity.this, "加载完成!", 0).show();
				}
			}
			//传递参数进来
		}.execute();

	}

}
