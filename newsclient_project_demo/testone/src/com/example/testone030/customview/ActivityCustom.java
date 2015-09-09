package com.example.testone030.customview;

import com.example.testone001.loning.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ActivityCustom extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_custom_test_one_layout);
	}
}
