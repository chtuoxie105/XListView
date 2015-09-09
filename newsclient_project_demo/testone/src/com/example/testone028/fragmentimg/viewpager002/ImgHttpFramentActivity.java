package com.example.testone028.fragmentimg.viewpager002;

import com.example.testone001.loning.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImgHttpFramentActivity extends Fragment {
	private ImageView mImageView;
	private static String IMG_KEY = "IMG_URL";
	
	/**
	 * 传入图片的地址
	 * @param imgString
	 * @return
	 */
	public static ImgHttpFramentActivity newFragment(String imgString) {
		ImgHttpFramentActivity mImgHttpFramentActivity = new ImgHttpFramentActivity();
		Bundle bund = new Bundle();
		bund.putString(IMG_KEY, imgString);

		mImgHttpFramentActivity.setArguments(bund);
		return mImgHttpFramentActivity;
	}
	/**
	 * 加载我们的布局
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.img_http_fragment_pageradapter_layout,
				container, false);
	}
	/**
	 * 实例化控件、取获取图片的地址操作
	 */
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mImageView = (ImageView) getView().findViewById(
				R.id.http_img_fragment_viewpager);
		
		Bundle bund = getArguments();
		String url = bund.getString(IMG_KEY);		
		new HttpImg(url,mImageView);
	}

}
