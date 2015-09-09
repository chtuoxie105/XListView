package com.example.testone028.fragment_one;

import com.example.testone001.loning.R;
import com.example.testone028.fragment_two.MyDialogFragmet;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class MusicFragmentActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_tabhost_layout);

		// ·ÀÖ¹ÆÁÄ»Ðý×ªµÄ
		if (savedInstanceState == null) {

			FragmentManager manger = getFragmentManager();
			FragmentTransaction tran = manger.beginTransaction();

			TabHostOneFragment m = TabHostOneFragment.newGragment();

			tran.replace(R.id.fragment_tabhost_test, new TabHostOneFragment());
			tran.commit();
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			getFragmentManager().beginTransaction()
					.add(new MyDialogFragmet(), "tag").commit();

		}
		return false;
	}
}
