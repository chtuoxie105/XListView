package com.example.testone009.Popwindow001;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.testone001.loning.R;

public class PopupWindowActivity extends Activity {
	private ListView mListPop;
	private Button mPopBtn;
	private PopupWindow mPopWind;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_popup_layout);

		mPopBtn = (Button) findViewById(R.id.popupwindow_btn);
		// 实例化窗口，设置弹窗的大小
		mPopWind = new PopupWindow(getView(), 400, 400);

		// 下面这三行代码可以让你点击空白处来关闭PopWindow 弹出的窗口
		mPopWind.setTouchable(true);
		mPopWind.setOutsideTouchable(true);
		mPopWind.setBackgroundDrawable(new BitmapDrawable());

		// 点击按钮就弹出窗口
		mPopBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (mPopWind.isShowing()) {// 判断窗口是否已经弹出弹出
					// mPopWind.setOutsideTouchable(false);
					mPopWind.dismiss();// 关闭窗口
				} else {
					mPopWind.showAsDropDown(v);// 弹出窗口
				}
			}
		});
	}

	// 设置适配器并返回View 添加到PopWindow中去
	public View getView() {
		LayoutInflater mInfalter = LayoutInflater.from(this);
		View v = mInfalter.inflate(R.layout.activity_list_view, null);
		mListPop = (ListView) v.findViewById(R.id.list_view);
		PopupWindowBaseadapter adapter = new PopupWindowBaseadapter(this);
		adapter.setData(getData());// 优化适配器
		mListPop.setAdapter(adapter);
		return v;
	}

	// 设置数据源
	public List<PopWindowBean> getData() {
		List<PopWindowBean> list = new ArrayList<PopWindowBean>();
		PopWindowBean popBean = new PopWindowBean();
		popBean.setmImagePop(R.drawable.simple2);
		popBean.setmTextPop("摇一摇");
		list.add(popBean);
		popBean = new PopWindowBean();
		popBean.setmImagePop(R.drawable.simple2);
		popBean.setmTextPop("扫二维码");
		list.add(popBean);
		popBean = new PopWindowBean();
		popBean.setmImagePop(R.drawable.simple2);
		popBean.setmTextPop("查找附近的人");
		list.add(popBean);
		popBean = new PopWindowBean();
		popBean.setmImagePop(R.drawable.simple2);
		popBean.setmTextPop("查看地图");
		list.add(popBean);

		return list;
	}
}
