package com.example.testone011.ViewPager;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.testone001.loning.R;

public class MyViewPagerActivity extends Activity {
	private ViewPager mPager;
	private LayoutInflater mInfalter;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_viewpager_main_layout);

		mPager = (ViewPager) findViewById(R.id.main_pager);

		ArrayList<View> list = new ArrayList<View>();
		mInfalter = LayoutInflater.from(this);
		View onePage = mInfalter.inflate(
				R.layout.activity_main_pager_one_layout, null);
		View twoPage = mInfalter.inflate(
				R.layout.activity_main_pager_two_layout, null);
		View threePage = mInfalter.inflate(
				R.layout.activity_main_pager_three_layout, null);
		list.add(onePage);
		list.add(twoPage);
		list.add(threePage);

		MyPagerAdapter pagerAd = new MyPagerAdapter();
		pagerAd.setData(list);
		mPager.setAdapter(pagerAd);

	}

	/**
	 * 自定义适配器PagerAdapter
	 */
	public class MyPagerAdapter extends PagerAdapter {

		private ArrayList<View> listadapter = new ArrayList<View>();

		public int getCount() {

			return listadapter.size();
		}

		/**
		 * 优化适配器，并清理
		 * 
		 * @param listadapter
		 */
		public void setData(ArrayList<View> listadapter) {
			this.listadapter = listadapter;
			notifyDataSetChanged();
		}

		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0 == arg1;
		}

		/**
		 * 添加下一个要显示的界面
		 */
		public Object instantiateItem(ViewGroup container, int position) {
			View v = listadapter.get(position);
			container.addView(v);
			return v;
		}

		/**
		 * 滑动到下个界面时，移除上一个
		 */
		public void destroyItem(ViewGroup container, int position, Object object) {
			View v = listadapter.get(position);
			container.removeView(v);

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mPager != null) {
			mPager.removeAllViews();
			mPager = null;
		}
	}
}
