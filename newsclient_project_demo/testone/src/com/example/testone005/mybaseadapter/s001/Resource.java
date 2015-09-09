package com.example.testone005.mybaseadapter.s001;

import java.util.HashMap;

public class Resource {
	
	private String mTitleText;
	private HashMap<String, Object> map = new HashMap<String, Object>();

	
	public String getmTitleText() {
		return mTitleText;
	}
	public void setmTitleText(String mTitleText) {
		this.mTitleText = mTitleText;
	}
	public void setToMap(String s,Object j){	
		map.put(s, j);
	}
	public Object getToMap(String ss){
		
		Object n = map.get(ss);
		return n;
	}
}
