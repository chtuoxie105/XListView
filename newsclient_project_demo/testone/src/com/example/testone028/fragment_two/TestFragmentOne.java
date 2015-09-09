package com.example.testone028.fragment_two;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.testone001.loning.R;

public class TestFragmentOne extends Fragment implements OnClickListener {
	private Button trueBtn;
	private BackMsg mBackMsg;

	public interface BackMsg {
		public void backMsgToActivity(String str);
	}

	
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mBackMsg = (BackMsg) activity;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragmenttwo_test_one_layout,
				container, false);
		return v;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View view = getView();
		trueBtn = (Button) view.findViewById(R.id.test_fragment_one_btn);

		trueBtn.setOnClickListener(this);
	}

	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.test_fragment_one_btn:
			mBackMsg.backMsgToActivity("第一个fragment的数据");
			break;
		}

	}
}
