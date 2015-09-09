package com.example.testone014.SQLiteDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteHelper extends SQLiteOpenHelper {
	private static String name = "meituan";
	private static int version = 2;
	public static SqliteHelper sqlHelper = null;

	/**
	 * 单例模式
	 */
	public static SqliteHelper geiInsentence(Context context) {
		if (sqlHelper == null) {
			sqlHelper = new SqliteHelper(context);
		}
		return sqlHelper;
	}

	public SqliteHelper(Context context) {
		super(context, name, null, version);
	}

	/**
	 * 建表，用户使用表，密码表
	 */
	public void onCreate(SQLiteDatabase db) {
		String sqlUser = "create table " + MySQLPackaging.Student.TABLE + "("
				+ MySQLPackaging.Student._ID
				+ " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ MySQLPackaging.Student.NAME + " TEXT NOT NULL,"
				+ MySQLPackaging.Student.NUMBER + " TEXT NOT NULL,"
				+ MySQLPackaging.Student.SEX + " TEXT)";

		String sqlPassword = "create table " + MySQLPackaging.Teacher.TABLE
				+ "(" + MySQLPackaging.Teacher._ID
				+ " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ MySQLPackaging.Teacher.NAME + " TEXT NOT NULL,"
				+ MySQLPackaging.Teacher.NUMBER + "TEXT NOT NULL)";
		
		db.execSQL(sqlUser);
		db.execSQL(sqlPassword);
		Log.e("1111", "1111111111>>>onCreate建表");
		
	}

	/**
	 * 版本更新时调用
	 */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.e("1111", "1111111111>>>onUpgrade更新");
		if (oldVersion <= 2) {
			String sql = "Alter table " + MySQLPackaging.Student.TABLE
					+ " add Column " + MySQLPackaging.Student.SCORE + " TEXT ";
			db.execSQL(sql);
		}
		String sql1 = "Alter table " + MySQLPackaging.Student.TABLE
				+ " add Column " + MySQLPackaging.Student.ADDRESS + " TEXT ";
		db.execSQL(sql1);
	}

}
