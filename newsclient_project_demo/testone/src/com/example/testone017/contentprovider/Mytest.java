package com.example.testone017.contentprovider;

import com.example.testone014.SQLiteDataBase.MySQLPackaging;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class Mytest extends ContentProvider {
	private OpenHelp mOpenHelp;
	SQLiteDatabase db;

	@Override
	public boolean onCreate() {
		mOpenHelp = new OpenHelp(getContext());
		db = mOpenHelp.getReadableDatabase();
		return false;
	}
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		return 0;
	}

	@Override
	public String getType(Uri uri) {

		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		db.insert(MySQLPackaging.TestSQL.TABLE, null, values);
		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
		SQLiteDatabase ss = mOpenHelp.getReadableDatabase();
		builder.setTables("TestSQL");
		Cursor cu = builder.query(ss, projection, selection, selectionArgs,
				null, null, null, sortOrder);

		return cu;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		
		return 0;
	}

	public class OpenHelp extends SQLiteOpenHelper {
		private static final String mySQltest = "my_sql_test.db";
		private static final int current = 1;

		public OpenHelp(Context context) {
			super(context, mySQltest, null, current);

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = "create table " + MySQLPackaging.TestSQL.TABLE + "("
					+ MySQLPackaging.TestSQL._ID
					+ "INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+ MySQLPackaging.TestSQL.NAME + "TEXT NOT NULL,"
					+ MySQLPackaging.TestSQL.NOTE + "TEXT NOT NULL)";
			db.execSQL(sql);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

	}

}
