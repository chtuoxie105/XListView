package com.example.testone002.Serializables;

import java.io.Serializable;



public class StudentToSerializable implements Serializable {
	private String mName;
	private String mSex;
	private int mAge;
	private String mScore;
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmSex() {
		return mSex;
	}
	public void setmSex(String mSex) {
		this.mSex = mSex;
	}
	public int getmAge() {
		return mAge;
	}
	public void setmAge(int mAge) {
		this.mAge = mAge;
	}
	public String getmScore() {
		return mScore;
	}
	public void setmScore(String mScore) {
		this.mScore = mScore;
	}
}
