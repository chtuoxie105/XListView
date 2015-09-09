package com.example.testone004.Listview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testone001.loning.R;

public class ListsActivity extends Activity {
		String[]  s = {"11111","222222","33333","44444","555555","66666","777777","888888","99999","00000"};
		private ListView mListview;
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_list_view);
		
			mListview = (ListView) findViewById(R.id.list_view);			
			ArrayAdapter<String> adapter = new  ArrayAdapter<String>(this,R.layout.lsit_view,s);			
			mListview.setAdapter(adapter);
			
			
			//监听ListView
			mListview.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
				
					switch(position){
					case 0: Log.e("第1项","00000000");break;	
					case 1: Log.e("第2项","111111111");break;	
					case 2: Log.e("第3项","222222222");break;	
					case 3: Log.e("第4项","3333333333");break;	
					case 4: Log.e("第5项","444444444");break;	
					case 5: Log.e("第6项","555555555");break;	
					case 6: Log.e("第7项","666666666");break;	
					case 7: Log.e("第8项","777777777");break;	
					case 8: Log.e("第9项","8888888888");break;	
					case 9: Log.e("第10项","999999999");break;	
					case 10: Log.e("第11项","aaaaaaaa");break;	
			
					}
				}
			});
		}

}
