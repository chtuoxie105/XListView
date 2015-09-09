package com.example.testone028.slidefragment003;

import android.os.Bundle;
import android.view.Window;

import com.example.testone001.loning.R;
import com.example.testone028.fragment_one.OneFragment;
import com.warmtel.slidingmenu.lib.SlidingMenu;
import com.warmtel.slidingmenu.lib.app.SlidingFragmentActivity;

public class TestSlidingActivity extends SlidingFragmentActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sliding_menu_test_layout);

		inintSlidingMenu();

	}

	public void inintSlidingMenu() {
		setBehindContentView(R.layout.sliding_menu_one_layout);
		getFragmentManager().beginTransaction()
				.add(R.id.menu_sliding_fragment, OneFragment.newFragment())
				.commit();

		SlidingMenu slidingMenu = getSlidingMenu();
		// 设置向左滑出
		slidingMenu.setMode(SlidingMenu.LEFT);
		// 设置触摸的模式
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// 设置滑动菜单视图的宽度,设置的是滑出来的界面的边缘离界面边缘的距离
		slidingMenu.setBehindOffsetRes(R.dimen.sliding_offset);
//		slidingMenu.setBehindWidth(R.dimen.sliding_weith);
		// 设置渐入渐出效果的值,值越大越明显
		slidingMenu.setFadeDegree(0.20f);
		//设置弹出侧滑的菜单,最好用于按钮的点击事件里
//		toggle();
		//设置二级侧滑菜单
		slidingMenu.setSecondaryMenu(R.layout.viewpager_fragment_tabmine_three);
		
	}

}
