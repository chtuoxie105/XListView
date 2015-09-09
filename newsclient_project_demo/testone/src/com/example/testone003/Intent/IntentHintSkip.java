package com.example.testone003.Intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testone001.loning.R;
import com.example.testone003.Intent.MyApplication.MyStack;

public class IntentHintSkip extends Activity {
		
	private Button mSkipBtu,mhintBtu,mPhoneBtu;
	
		protected void onCreate(Bundle savedInstanceState) {
			
			super.onCreate(savedInstanceState);			
			setContentView(R.layout.intent_hint_layout);
						
			MyApplication app = new MyApplication();
			MyStack sta = app.new MyStack();
			sta.sPush(this);
			
			mSkipBtu = (Button) findViewById(R.id.public_btu);
			mhintBtu = (Button) findViewById(R.id.hint_btu);
			mPhoneBtu = (Button) findViewById(R.id.phone_btu);
			
			mSkipBtu.setOnClickListener(new OnClickListener() {				
				public void onClick(View v) {
					hintSkipPhone();
				}
			});
			mhintBtu.setOnClickListener(new OnClickListener() {				
				public void onClick(View v) {
					hintSkip();
				}
			});
			mPhoneBtu.setOnClickListener(new OnClickListener() {				
				public void onClick(View v) {
					hintSkipBrowser();
				}
			});
		}
		/**
		 * 隐士调用其他类
		 */
		public void hintSkip(){
			Intent inte = new Intent();
			
			inte.setAction("com.test.hint");
			
			
			startActivity(inte);
		}
		/**
		 * 隐士调用打电话
		 */
		public void hintSkipPhone(){
			Uri uri = Uri.parse("tel:1008611");
			Intent inte = new Intent(Intent.ACTION_CALL);			
			inte.setAction("android.intent.action.DIAL");
			
			inte.setData(uri);
			
			startActivity(inte);
		}
		/**
		 * 隐士调用浏览器
		 */
		public void hintSkipBrowser(){
			Uri  uri = Uri.parse("");
			
			Intent inte = new Intent(Intent.ACTION_VIEW);
			startActivity(inte);
		}
	
	
}















