package com.example.testone002.Serializables;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableToInter implements Parcelable {

	private String name;
	private String sex;
	private int age;
	private String score;

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel parcels, int flags) {
		parcels.writeString(name);
		parcels.writeString(sex);
		parcels.writeInt(age);
		parcels.writeString(score);
	}

	/**
	*用 public static final 修饰，CREATOR变量固定
	*/
	public static final Parcelable.Creator<ParcelableToInter> CREATOR = new Parcelable.Creator<ParcelableToInter>() {
		public ParcelableToInter createFromParcel(Parcel source) {
			ParcelableToInter pacelable = new ParcelableToInter();
			pacelable.name = source.readString();
			pacelable.sex = source.readString();
			pacelable.age = source.readInt();
			pacelable.score = source.readString();
			return pacelable;
		}

		public ParcelableToInter[] newArray(int size) {

			return new ParcelableToInter[size];
		}
	};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}
