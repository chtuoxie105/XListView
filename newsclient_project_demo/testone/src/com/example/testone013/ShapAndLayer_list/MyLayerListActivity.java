package com.example.testone013.ShapAndLayer_list;

import com.example.testone001.loning.R;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;

/**
 * 自定义的seekbar
 */
public class MyLayerListActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_layer_list_seekbar);

	}

	
	public void sss() {
		Point point = new Point();
		getWindowManager().getDefaultDisplay().getSize(point);
	}
}
