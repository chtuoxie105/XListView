package com.example.testone028.fragment_one;

import com.example.testone001.loning.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class TabHostOneFragment extends Fragment implements OnCheckedChangeListener{
	private RadioGroup mFragmentRdrioGroup;
	private ListFragment mListFragment;
	private OneFragment mOneFragment;
	public static TabHostOneFragment newGragment(){
		return new TabHostOneFragment();
	}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_tabhost_layout, container,
				false);
		
		return v;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		View view = getView();
		mFragmentRdrioGroup = (RadioGroup) view.findViewById(R.id.fragment_tabhost_radiogroup);
		mFragmentRdrioGroup.setOnCheckedChangeListener(this);
		//默认选中第一项
		((RadioButton)mFragmentRdrioGroup.getChildAt(0)).toggle();
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
		hideFragment(beginTransaction);
		
		switch (checkedId) {
		case R.id.fragment_groupon:
			if(mListFragment == null){
				mListFragment = ListFragment.newFragment();
				beginTransaction.add(R.id.activity_fragment_content_layout, mListFragment);
				beginTransaction.commit();
			}else{
				beginTransaction.show(mListFragment);
			}
	
			break;
			
		case R.id.fragment_merchant:
			if(mOneFragment == null){
				mOneFragment = OneFragment.newFragment();
				beginTransaction.add(R.id.activity_fragment_content_layout, mOneFragment);
				beginTransaction.commit();
			}else{
				beginTransaction.show(mOneFragment);
			}

			break;
		case R.id.fragment_mine:
			if(mListFragment == null){
				mListFragment = ListFragment.newFragment();
				beginTransaction.add(R.id.activity_fragment_content_layout, mListFragment);
				beginTransaction.commit();
			}else{
				beginTransaction.show(mListFragment);
			}
			break;
		case R.id.fragment_more:
			if(mOneFragment == null){
				mOneFragment = OneFragment.newFragment();
				beginTransaction.add(R.id.activity_fragment_content_layout, mOneFragment);
				beginTransaction.commit();
			}else{
				beginTransaction.show(mOneFragment);
			}
			break;
		}
	
	}
	/**
	 * 隐藏各个Fragment页面，可以保存页面的数据
	 * 
	 */
	 
	public void hideFragment(FragmentTransaction fragmentTran){
		if(mListFragment != null){
			fragmentTran.hide(mListFragment);
		}
		if(mOneFragment != null){
			fragmentTran.hide(mOneFragment);
		}
		
	}
	
}
