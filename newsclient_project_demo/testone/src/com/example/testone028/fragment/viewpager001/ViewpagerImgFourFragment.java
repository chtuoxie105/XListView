package com.example.testone028.fragment.viewpager001;

import com.example.testone001.loning.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewpagerImgFourFragment extends Fragment {
	
	public static ViewpagerImgFourFragment newFragment(){
		
		return new ViewpagerImgFourFragment();
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.viewpager_fragment_tabimg_four,
				container, false);
		return v;
	}
}
