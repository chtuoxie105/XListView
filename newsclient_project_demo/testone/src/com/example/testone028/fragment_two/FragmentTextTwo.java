package com.example.testone028.fragment_two;

import com.example.testone001.loning.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class FragmentTextTwo extends Activity implements
		TestFragmentOne.BackMsg {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_text_two_layout);

		getFragmentManager().beginTransaction()
				.add(R.id.fragment_test_two_one, new TestFragmentOne())
				.commit();

	}

	public void backMsgToActivity(String str) {
		FragmentTransaction tran = getFragmentManager().beginTransaction();
		TestFragmentTwo m = TestFragmentTwo.newFragment(str);

		tran.add(R.id.fragment_test_two_two, m);
		//把当前的fragment体添加到后台堆栈
		tran.addToBackStack(null);
		//弹栈
//		tran.disallowAddToBackStack();
		tran.commit();
	}

}
