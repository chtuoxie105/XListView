package com.example.testone028.fragment_one;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testone001.loning.R;

public class MainFragmentActivity extends Activity {

	private ListFragment mListFragment;
	private Button mBtn;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_fragment_layout);
		
		mBtn = (Button) findViewById(R.id.test_fragment_btn_one);
		
//		setDefaultFragment();		
						
		mBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				test();
			}
		});
	}
	/***直接添加fragment**/
	public void addFragment(){
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		OneFragment mWeixin = new OneFragment();
		
//		transaction.disallowAddToBackStack();
//		transaction.addToBackStack();
		
		transaction.add(R.id.id_content, mWeixin,"OneFragment");
		transaction.commit();
	}
	
	
	/**默认呢的fragment*/
	private void setDefaultFragment() {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		OneFragment mWeixin = new OneFragment();
		transaction.replace(R.id.id_content, mWeixin);
		transaction.commit();
	}
	/**覆盖上一个默认的:fragment*/
	public void test() {
		FragmentManager frgManger = getFragmentManager();
		// 开启一个事务
		FragmentTransaction tran = frgManger.beginTransaction();

		mListFragment = new ListFragment();

		tran.replace(R.id.id_content, mListFragment);

		tran.commit();

	}
}
