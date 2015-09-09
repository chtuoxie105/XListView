package com.example.testone027.xml_parsers;

import com.example.testone001.loning.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class BookActivity extends Activity {
	private Button mReadBookXMLBtn, mWriteBookXMLBtn;
	private TextView mShowBookXMLText;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_book_layout);

		mReadBookXMLBtn = (Button) findViewById(R.id.read_book_for_xml_btn);
		mWriteBookXMLBtn = (Button) findViewById(R.id.write_book_for_xml_btn);
		mShowBookXMLText = (TextView) findViewById(R.id.show_book_to_xml_text);
	}
}
