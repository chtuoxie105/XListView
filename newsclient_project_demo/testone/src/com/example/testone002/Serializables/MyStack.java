package com.example.testone002.Serializables;

import java.util.Stack;

import android.app.Activity;

public class MyStack extends Stack<Activity> {
		
	Stack<Activity> list = new Stack<Activity>();
	
	public void sPush(Activity acti){
		list.push(acti);
	}
	public Activity sPop(){
		Activity acti = list.pop();
		return acti;
	}
	
}
