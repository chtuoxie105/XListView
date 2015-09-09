package com.example.testone017.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.example.testone001.loning.R;
import com.example.testone014.SQLiteDataBase.MySQLPackaging;

public class ContentResplvreActivity extends Activity implements
		OnClickListener {
	private EditText mNameUserProviderEdit, mPasswordUserProvider;
	private Button mInsertBtn, mUpdataBtn;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_provider_sqlite_layout);

		mNameUserProviderEdit = (EditText) findViewById(R.id.test_provider_username_edittext);
		mPasswordUserProvider = (EditText) findViewById(R.id.test_provider_userpassword_edittext);

		mInsertBtn = (Button) findViewById(R.id.test_provider_insert_btn);
		mUpdataBtn = (Button) findViewById(R.id.test_provider_update_btn);

		ContentResolver resolver = getContentResolver();
		insertRasolver(resolver);
		readResolver(resolver);
		mInsertBtn.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.test_provider_insert_btn:
			ContentValues values = new ContentValues();
			values.put(MySQLPackaging.User.NAME, mNameUserProviderEdit
					.getText().toString());
			values.put(MySQLPackaging.User.PASSWORD, mPasswordUserProvider
					.getText().toString());

			ContentResolver reso = getContentResolver();
			reso.insert(TestContentProvider.CONTENT_USER_URI, values);

			Log.i("111", "添加成功");
			break;
		case R.id.test_provider_update_btn:
			break;
		}
	}

	/**
	 * 读取联系人
	 */
	public void readResolver(ContentResolver resolver) {
		Cursor cur = resolver.query(Contacts.People.CONTENT_URI, null, null,
				null, null);
		while (cur.moveToNext()) {
			String name = cur.getString(cur
					.getColumnIndex(Contacts.People.NAME));
			int n = cur.getInt(cur.getColumnIndex(Contacts.People.NOTES));

			Log.i("11", "name :" + name + "号码 :" + n);
		}
	}

	/**
	 * 添加联系人
	 */
	public void insertRasolver(ContentResolver resolver) {
		ContentValues values = new ContentValues();
		values.put(Contacts.People.NAME, "穿着拖鞋去上学");
		values.put(Contacts.People.NOTES, 10086111);

		Uri uri = Contacts.People.CONTENT_URI;
		resolver.insert(uri, values);
	}

}
