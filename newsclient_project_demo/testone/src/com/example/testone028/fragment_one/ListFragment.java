package com.example.testone028.fragment_one;

import com.example.testone001.loning.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListFragment extends Fragment {
	
	
	public static ListFragment newFragment(){
		return new ListFragment();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		 View v= inflater.inflate(R.layout.fragment_test_lauout_one, container,
				false);
		 
		 
		 return v;
	}
	
	/***÷±Ω”ÃÌº”fragment**/
	public void addFragment(){
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		OneFragment mWeixin = new OneFragment();
		transaction.add(R.id.id_content, mWeixin,"OneFragment");
		transaction.commit();
	}
}
