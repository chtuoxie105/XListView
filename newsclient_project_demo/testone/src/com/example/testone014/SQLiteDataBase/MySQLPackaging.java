package com.example.testone014.SQLiteDataBase;

import android.provider.BaseColumns;

public class MySQLPackaging {
	public static final class Student implements BaseColumns {
		public static String TABLE = "student";// 版本1
		public static String NAME = "name";// 版本1
		public static String NUMBER = "number";// 版本1
		public static String SEX = "sex";// 版本1
		public static String SCORE = "score";// 版本2升级加入字段
		public static String ADDRESS = "address";// 版本3升级加入字段
	}

	public static final class Teacher implements BaseColumns {
		public static String TABLE = "teacher";
		public static String NAME = "name";
		public static String NUMBER = "number";
	}

	public static final class User implements BaseColumns {
		public static String TABLE = "user";
		public static String NAME = "name";
		public static String PASSWORD = "password";
	}
	public static final class TestSQL implements BaseColumns{
		public static String TABLE = "kugou";
		public static String NAME = "name";
		public static String NOTE  ="words";
	}
}






