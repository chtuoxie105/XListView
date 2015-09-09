package com.example.testone028.fragment_one;

import com.example.testone001.loning.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * ÉúÃüÖÜÆÚ::
 * @author Administrator
 *
 */
public class OneFragment extends Fragment {

	public static OneFragment newFragment() {
		return new OneFragment();
	}

	public void onAttach(Activity activity) {
		Log.i("11", "onAttach");
		super.onAttach(activity);
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.test_one_fragment_layout, container,
				false);

	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public void onStart() {
		super.onStart();
	}

	public void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}

	public void onStop() {
		super.onStop();
	}

	public void onDestroyView() {
		super.onDestroyView();
	}

	public void onDestroy() {
		super.onDestroy();
	}

	public void onDetach() {
		super.onDetach();
	}
}
