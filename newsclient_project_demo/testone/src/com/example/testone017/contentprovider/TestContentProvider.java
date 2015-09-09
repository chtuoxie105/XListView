package com.example.testone017.contentprovider;

import com.example.testone014.SQLiteDataBase.MySQLPackaging;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class TestContentProvider extends ContentProvider {
	public static String SS = "testprovader.user";
	public static Uri CONTENT_USER_URI =Uri.parse("content://"+SS);
	private static final String TABLE_NAME="User";
	SqlDataBaseHelper content;
	SQLiteDatabase db;
	public boolean onCreate() {
		content = new SqlDataBaseHelper(getContext());
		db = content.getReadableDatabase();
		return false;
	}

	public Cursor query(Uri uri, String[] projection, String selection,
		String[] selectionArgs, String sortOrder) {
		
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase db = content.getReadableDatabase();
        qb.setTables(TABLE_NAME);
        Cursor c = qb.query(db, projection, selection, null, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        
        return c;
	}

	public String getType(Uri uri) {

		return null;
	}

	public Uri insert(Uri uri, ContentValues values) {
		db.insert(MySQLPackaging.User.TABLE, null, values);

		return null;
	}

	public int delete(Uri uri, String selection, String[] selectionArgs) {

		return 0;
	}

	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {

		return 0;
	}

	/**
	 * 创建数据库和表，字段，属性
	 */
	public class SqlDataBaseHelper extends SQLiteOpenHelper {
		public static final String DATA_BASE_NAME = "test_provider.db";
		public static final int VERSION = 1;

		public SqlDataBaseHelper(Context context) {
			super(context, DATA_BASE_NAME, null, VERSION);

		}

		public void onCreate(SQLiteDatabase db) {
			String sql = "create table " + MySQLPackaging.User.TABLE + "("
					+ MySQLPackaging.User._ID
					+ " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
					+ MySQLPackaging.User.NAME + " TEXT NOT NULL,"
					+ MySQLPackaging.User.PASSWORD + " TEXT NOT NULL)";
			db.execSQL(sql);
		}

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

	}
}
