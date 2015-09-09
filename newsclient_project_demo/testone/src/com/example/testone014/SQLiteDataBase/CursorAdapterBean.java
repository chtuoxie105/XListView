package com.example.testone014.SQLiteDataBase;

import android.os.Parcel;
import android.os.Parcelable;

public class CursorAdapterBean implements Parcelable {
	private String name;
	private String number;
	private String sex;
	private String score;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int describeContents() {

		return 0;
	}

	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeString(name);
		parcel.writeString(number);
		parcel.writeString(sex);
		parcel.writeString(score);
		parcel.writeString(address);
	}

	/**
	 * 用 public static final 修饰，CREATOR变量固定,对象是实现Parcelable接口的类名
	 */
	public static final Parcelable.Creator<CursorAdapterBean> CREATOR = new Parcelable.Creator<CursorAdapterBean>() {
		public CursorAdapterBean createFromParcel(Parcel source) {
			CursorAdapterBean pacelable = new CursorAdapterBean();
			pacelable.name = source.readString();
			pacelable.number = source.readString();
			pacelable.sex = source.readString();
			pacelable.score = source.readString();
			pacelable.address = source.readString();
			return null;
		}

		public CursorAdapterBean[] newArray(int size) {

			return new CursorAdapterBean[size];
		}
	};
}
