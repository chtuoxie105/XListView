package com.example.testone028.fragment_two;

import com.example.testone001.loning.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TestFragmentTwo extends Fragment {
	private TextView textView;
	
	public static TestFragmentTwo newFragment(String str){
		TestFragmentTwo m = new TestFragmentTwo();

		Bundle bund = new Bundle();
		bund.putString("FRAGMENT_BUNG", str);
		m.setArguments(bund);
		
		return m;
	}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragmenttwo_test_two_layout,
				container, false);

		return v;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		textView = (TextView) getView().findViewById(
				R.id.fragmenttwo_test_edittext);
		Bundle bund = getArguments();
		String s = bund.getString("FRAGMENT_BUNG");
		textView.setText(s);
	}

}
