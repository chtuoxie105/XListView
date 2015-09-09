package com.example.testone015.sharedpreferences_file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class FilesActivity extends Activity {
	private String Filr_Name = "file_out.txt";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			File files = new File(getFilesDir(), Filr_Name);
			
			FileOutputStream file = openFileOutput(Filr_Name, Context.MODE_APPEND);
			String s = "穿着拖鞋去上学";
			file.write(s.getBytes());
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
