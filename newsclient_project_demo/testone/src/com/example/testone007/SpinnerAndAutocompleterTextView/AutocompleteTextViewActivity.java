package com.example.testone007.SpinnerAndAutocompleterTextView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.testone001.loning.R;

public class AutocompleteTextViewActivity extends Activity {
	private AutoCompleteTextView mAutoText;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autocomplete_layout);
		mAutoText = (AutoCompleteTextView) findViewById(R.id.auto_Text);

		mAutoText.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String sss = mAutoText.getText().toString();
				if (sss.equals("m")) {
					String[] s2 = { "mmm", "mss", "wsss", "ms" };
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							AutocompleteTextViewActivity.this,
							android.R.layout.simple_expandable_list_item_1, s2);
					mAutoText.setAdapter(adapter);
				} else if (sss.equals("i")) {
					String[] s2 = { "iphone", "ipmss", "iwsss", "ims" };
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							AutocompleteTextViewActivity.this,
							android.R.layout.simple_expandable_list_item_1, s2);
					mAutoText.setAdapter(adapter);
				}

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void afterTextChanged(Editable s) {

			}
		});
	}
}
