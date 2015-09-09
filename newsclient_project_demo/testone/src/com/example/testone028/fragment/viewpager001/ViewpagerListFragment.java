package com.example.testone028.fragment.viewpager001;

import com.example.testone001.loning.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewpagerListFragment extends Fragment {
	
	public static ViewpagerListFragment newFragment(){
		
		return new ViewpagerListFragment();
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.viewpager_fragment_tablist_two,
				container, false);
		return v;
	}
}
