package com.example.testone008.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.testone001.loning.R;
import com.example.testone006.myGridview.MyGridView;

public class MySotckProgressBar extends Activity {
	private ListView mListViewForBar;
	private LinearLayout mLinearFroBar;
	public MyStackProgressBarToBaseAdapter adspToBase;
	
	public Handler mHandle = new Handler(){
		public void handleMessage(android.os.Message msg) {
			//	不用再取 Message  对象，直接用msg 调取里面的内容
			//	mLinearFroBar.setVisibility(View.GONE);//隐藏加载的界面
//			int n = msg.what;
			
			adspToBase.setData((List<MyGridViewStack>) msg.obj);//优化后的适配器
			
		};
	};
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stack_myprogressbar_layout);

		mListViewForBar = (ListView) findViewById(R.id.stack_bar_list_view);
		mLinearFroBar = (LinearLayout) findViewById(R.id.linear_stack_bar);
		
		mListViewForBar.setEmptyView(mLinearFroBar);//根据ListView是否为空来判断显示或者隐藏	mLinearFroBar 界面
		
		adspToBase = new MyStackProgressBarToBaseAdapter(this);//加载自定义的适配器
		mListViewForBar.setAdapter(adspToBase);
		
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(5000);
					
					List<MyGridViewStack> list = getdata();					
					Message age = Message.obtain();				
					age.obj = list;
					age.what = 0;					
					mHandle.sendMessage(age);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}

	public List<MyGridViewStack> getdata() {
		List<MyGridViewStack> list = new ArrayList<MyGridViewStack>();
		MyGridViewStack grid = new MyGridViewStack();

		grid.setmImage(R.drawable.wmgj);
		grid.setmText( "不干了，跳楼大减价,完美国际大片");
		list.add(grid);
		
		grid = new MyGridViewStack();
		grid.setmImage(R.drawable.simple1);
		grid.setmText(  "2D观影票1张，3D影片需加10元");
		list.add(grid);
		
		grid = new MyGridViewStack();
		grid.setmImage(R.drawable.simple2);
		grid.setmText( "仅售39元！价值58元的昭化古城门票，休闲时刻，共享欢乐.");
		list.add(grid);

		return list;
	}

}
