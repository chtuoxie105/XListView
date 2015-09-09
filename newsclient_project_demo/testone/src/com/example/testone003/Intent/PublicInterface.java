package com.example.testone003.Intent;

import com.example.testone001.loning.R;
import com.example.testone003.Intent.MyApplication.MyStack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class PublicInterface extends Activity {
		
		
		MyApplication app = new MyApplication();
		MyStack sta = app.new MyStack();
		
		
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.skip_layout);
			
			sta.sPush(this);
			
		}
		
		public boolean onKeyDown(int keyCode, KeyEvent event) {				
			if(keyCode == KeyEvent.KEYCODE_BACK){
				
				Log.v("и╬ЁЩ", "пео╒222222222");
				sta.sPop();
			}
			return super.onKeyDown(keyCode, event);
		}
}
