package com.example.testone028.fragment.viewpager001;

import com.example.testone001.loning.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewpagerImgFragment extends Fragment {
	
	public static ViewpagerImgFragment newFragment(){
		
		return new ViewpagerImgFragment();
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.viewpager_fragment_tabimg_one,
				container, false);
		return v;
	}
}
