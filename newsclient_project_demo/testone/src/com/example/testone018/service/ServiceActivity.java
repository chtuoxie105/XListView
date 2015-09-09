package com.example.testone018.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.testone001.loning.R;
import com.example.testone018.service.MyService.RealizeOnContent;

public class ServiceActivity extends Activity {
	private Button mBindServiceBtn, mSetServiceBtn, mGetServiceBtn;
	private EditText mconteneEdit;
	private Switch mSwitchThread;
	private RealizeOnContent mRealizeOnContent;

	private ServiceConnection mServiceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName name, IBinder service) {
			mRealizeOnContent = (RealizeOnContent) service;

		}

		public void onServiceDisconnected(ComponentName name) {
			mRealizeOnContent = null;
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_one_layout);
		mBindServiceBtn = (Button) findViewById(R.id.service_bind_start_service);
		mSetServiceBtn = (Button) findViewById(R.id.service_set_contene);
		mGetServiceBtn = (Button) findViewById(R.id.service_get_contene);
		mconteneEdit = (EditText) findViewById(R.id.service_edit_contene);
		mSwitchThread = (Switch) findViewById(R.id.service_switch);
		mSwitchThread.setChecked(true);
		// 启动服务
		mBindServiceBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent(ServiceActivity.this,
						MyService.class);
				bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
			}
		});
		mSetServiceBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String s = mconteneEdit.getText().toString();
				int contents = Integer.parseInt(s);
				mRealizeOnContent.setContents(contents);
			}
		});
		mGetServiceBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				int n = mRealizeOnContent.getContents();
				Toast.makeText(ServiceActivity.this, "当前计数为 :" + n, 0).show();
				unbindService(mServiceConnection);
			}
		});

		mSwitchThread
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						mRealizeOnContent.switchThread(true);
						if (isChecked) {
							
							Toast.makeText(ServiceActivity.this,
									"开启计数线程" + isChecked, 0).show();
						}
						else{
							mRealizeOnContent.switchThread(false);
							Toast.makeText(ServiceActivity.this,
									"关闭计数线程" + isChecked, 0).show();
						}
					}
				});
	}

	protected void onDestroy() {
		super.onDestroy();
		if(mRealizeOnContent != null){
			unbindService(mServiceConnection);
			mRealizeOnContent = null;
			Log.i("11","销毁了>>mRealizeOnContent");
		}
	}
}
