package com.example.testone028.fragmentimg.viewpager002;

import com.example.testone001.loning.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

public class ImgViewpagerFragment extends FragmentActivity {
	private ViewPager mViewPager;
	//String数组里面放的都是图片的地址
	private String[] str = {
			"http://img0.imgtn.bdimg.com/it/u=1773957434,886389142&fm=23&gp=0.jpg",
			"http://img0.imgtn.bdimg.com/it/u=3215986528,313107872&fm=21&gp=0.jpg",
			"http://img4.imgtn.bdimg.com/it/u=2961903531,2356716617&fm=23&gp=0.jpg",
			"http://img5.imgtn.bdimg.com/it/u=212291197,106167076&fm=23&gp=0.jpg",
			"http://img0.imgtn.bdimg.com/it/u=2852088603,1294175307&fm=23&gp=0.jpg",
			"http://img4.imgtn.bdimg.com/it/u=2615301497,2857071345&fm=23&gp=0.jpg",

			"http://img5.imgtn.bdimg.com/it/u=2562855756,997269017&fm=23&gp=0.jpg",
			"http://img0.imgtn.bdimg.com/it/u=3923730445,4290343773&fm=23&gp=0.jpg",
			"http://img3.imgtn.bdimg.com/it/u=2303620788,3237781573&fm=23&gp=0.jpg",
			"http://img1.imgtn.bdimg.com/it/u=1228856741,1148143928&fm=23&gp=0.jpg",
			"http://img2.imgtn.bdimg.com/it/u=1388498650,2529921706&fm=23&gp=0.jpg",

			"http://img3.imgtn.bdimg.com/it/u=4112503054,402357639&fm=21&gp=0.jpg",
			"http://img5.imgtn.bdimg.com/it/u=2060202349,1894455556&fm=21&gp=0.jpg",
			"http://img0.imgtn.bdimg.com/it/u=480577464,2087856000&fm=21&gp=0.jpg",
			"http://img0.imgtn.bdimg.com/it/u=1854849069,386582757&fm=21&gp=0.jpg",
			"http://img2.imgtn.bdimg.com/it/u=3533835925,1606113287&fm=21&gp=0.jpg",

			"http://img4.imgtn.bdimg.com/it/u=3792695135,1835603517&fm=21&gp=0.jpg",
			"http://img0.imgtn.bdimg.com/it/u=1384073263,1841819903&fm=21&gp=0.jpg",
			"http://img5.imgtn.bdimg.com/it/u=308115112,4038812993&fm=21&gp=0.jpg",
			"http://img4.imgtn.bdimg.com/it/u=571366870,564155952&fm=21&gp=0.jpg",

			"http://img2.imgtn.bdimg.com/it/u=2347688765,3840613615&fm=23&gp=0.jpg",
			"http://img1.imgtn.bdimg.com/it/u=116993252,4160792345&fm=23&gp=0.jpg",
			"http://img5.imgtn.bdimg.com/it/u=2504529905,3816354928&fm=23&gp=0.jpg",
			"http://img3.imgtn.bdimg.com/it/u=2170208609,2083385650&fm=23&gp=0.jpg",
			"http://img3.imgtn.bdimg.com/it/u=951312881,1556099872&fm=21&gp=0.jpg" };
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragmentviewpager_http_img_layout);
		
		mViewPager = (ViewPager) findViewById(R.id.fragment_viewpager_http);
		
		//FragmentPagerAdapter适配器
		MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(adapter);
		adapter.setData(str);
		
	}

	public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		private String[] imgUrl = new String[] {};

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public void setData(String[] imgUrls) {
			imgUrl = imgUrls;
			notifyDataSetChanged();
		}

		public int getCount() {
			return imgUrl.length;
		}

		public Fragment getItem(int arg0) {
			return ImgHttpFramentActivity.newFragment(imgUrl[arg0]);
		}

	}

}
