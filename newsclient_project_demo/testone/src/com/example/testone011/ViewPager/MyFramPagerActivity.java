package com.example.testone011.ViewPager;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testone001.loning.R;

public class MyFramPagerActivity extends Activity {
	private TextView mFramThreeText, mFramOneText;
	private ViewPager mPager;
	private LayoutInflater minfalFram;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_frame_viewpager_layout);
		mPager = (ViewPager) findViewById(R.id.main_pager);

		ArrayList<View> list = new ArrayList<View>();
		minfalFram = LayoutInflater.from(this);
		View v = minfalFram.inflate(R.layout.activity_frame_viewpager_layout,
				null);
		list.add(v);
		Custom tom = new Custom();
		tom.setData(list);
		mPager.setAdapter(tom);
		
//		mFramOneText = (TextView) findViewById(R.id.fram_pager_text_three);

	}

	/**
	 * ◊‘∂®“Â  ≈‰∆˜
	 */
	public class Custom extends PagerAdapter {

		ArrayList<View> list = new ArrayList<View>();

		public int getCount() {
			return list.size();
		}

		public void setData(ArrayList<View> list) {
			this.list = list;
			notifyDataSetChanged();
		}

		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0 == arg1;
		}

		public Object instantiateItem(ViewGroup container, int position) {
			View v = list.get(position);
			container.addView(v);
			return v;
		}

		public void destroyItem(ViewGroup container, int position, Object object) {
			View v = list.get(position);
			container.removeView(v);

		}
	}
}
