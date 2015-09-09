package com.example.testone031.viewstub.valueanimator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;

import com.example.testone001.loning.R;

/**
 * 我们如何才能让这些不常用的元素仅在需要时才去加载呢？Android为此提供了一种非常轻量级的控件，
 * ViewStub。ViewStub虽说也是View的一种但是它没有大小，没有绘制功能，也不参与布局，
 * 资源消耗非常低，将它放置在布局当中基本可以认为是完全不会影响性能的。
 */
public class MyViewStubActivity extends Activity {
	private Button showViewStubBtn;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewstub_layout);

		showViewStubBtn = (Button) findViewById(R.id.test_viewstub_btn);
		showViewStubBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//实例化隐藏的布局
				ViewStub viewstub = (ViewStub) findViewById(R.id.test_viewstub);
				View falter = viewstub.inflate();
				if (falter != null) {
					//实例化隐藏布局的控件,然后再进行相关的操作
					EditText editOne = (EditText) falter
							.findViewById(R.id.viewstub_hide_edit_one);
					EditText editTwo = (EditText) falter
							.findViewById(R.id.viewstub_hide_edit_two);
					Button btnThree = (Button) falter
							.findViewById(R.id.viewstub_hide_btn_three);
				}

			}
		});
	}
}
