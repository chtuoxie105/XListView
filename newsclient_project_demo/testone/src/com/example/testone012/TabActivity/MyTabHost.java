package com.example.testone012.TabActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.example.testone001.loning.R;
import com.example.testone006.myGridview.MyGridViewActivity;
import com.example.testone008.ProgressBar.MySeekBarActivity;
import com.example.testone010.Dialog.MyDialogActivity;
import com.example.testone011.ViewPager.MyViewPagerActivity;

public class MyTabHost extends TabActivity {

	private RadioGroup mRadioGroupToTabWidget;
	private TabHost mTabHost;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_tabhost_main_layout);
		mRadioGroupToTabWidget = (RadioGroup) findViewById(R.id.tab_radgrop_main);

		mTabHost = getTabHost();

		TabSpec s1 = mTabHost.newTabSpec("1");
		 s1.setIndicator(" ");
		s1.setContent(new Intent(this, MyViewPagerActivity.class));

		TabSpec s2 = mTabHost.newTabSpec("2");
		 s2.setIndicator(" ");
		s2.setContent(new Intent(this, MyDialogActivity.class));

		TabSpec s3 = mTabHost.newTabSpec("3");
		 s3.setIndicator(" ");
		s3.setContent(new Intent(this, MySeekBarActivity.class));

		TabSpec s4 = mTabHost.newTabSpec("4");
		 s4.setIndicator(" ");
		s4.setContent(new Intent(this, MyGridViewActivity.class));

		mTabHost.addTab(s1);
		mTabHost.addTab(s2);
		mTabHost.addTab(s3);
		mTabHost.addTab(s4);

		mRadioGroupToTabWidget
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.tab_radgrop_main_groupon:
							mTabHost.setCurrentTabByTag("1");
							break;
						case R.id.tab_radgrop_main_merchant:
							mTabHost.setCurrentTabByTag("2");
							break;
						case R.id.tab_radgrop_main_mine:
							mTabHost.setCurrentTabByTag("3");
							break;
						case R.id.tab_radgrop_main_more:
							mTabHost.setCurrentTabByTag("4");
							break;
						}

					}
				});
	}
}
