package com.example.testone014.SQLiteDataBase;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.testone001.loning.R;
/**
 * 自定义适配器，用于数据库的适配器，
 */
public class MyCursorAdapter extends CursorAdapter {
	private LayoutInflater mInfalter;

	public MyCursorAdapter(Context context, Cursor c) {
		super(context, c);
		mInfalter = LayoutInflater.from(context);
	}

	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = mInfalter.inflate(R.layout.activity_sqlite_adapter_layout,
				null);
		return view;
	}

	public void bindView(View view, Context context, Cursor cursor) {
		Reasou rea = null;
		if (rea == null) {
			rea = new Reasou();
			rea.nameTxt = (TextView) view
					.findViewById(R.id.activity_sqlite_cursoradapter__name_text);
			rea.numberTxt = (TextView) view
					.findViewById(R.id.activity_sqlite_cursoradapter__number_text);
			rea.sexTxt = (TextView) view
					.findViewById(R.id.activity_sqlite_cursoradapter__sex_text);
			rea.scoreTxt = (TextView) view
					.findViewById(R.id.activity_sqlite_cursoradapter__score_text);
			rea.addressTxt = (TextView) view
					.findViewById(R.id.activity_sqlite_cursoradapter__address_text);
			view.setTag(rea);
		} else {
			rea = (Reasou) view.getTag();
		}

		// String name = cursor.getString(cursor.getColumnIndex("name"));

		rea.nameTxt.setText(cursor.getString(cursor.getColumnIndex("name")));
		rea.numberTxt
				.setText(cursor.getString(cursor.getColumnIndex("number")));
		rea.sexTxt.setText(cursor.getString(cursor.getColumnIndex("sex")));
		rea.scoreTxt.setText(cursor.getString(cursor.getColumnIndex("score")));
		rea.addressTxt.setText(cursor.getString(cursor
				.getColumnIndex("address")));

	}

	public class Reasou {
		TextView nameTxt;
		TextView numberTxt;
		TextView sexTxt;
		TextView scoreTxt;
		TextView addressTxt;
	}
}
