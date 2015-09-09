package com.example.testone006.myGridview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import com.example.testone001.loning.R;
/**
 * GridView 九宫格的练习
 */
public class MyGridViewActivity extends Activity {
	private GridView mGridView;
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.grid_view_one_layout);
			
			mGridView = (GridView) findViewById(R.id.gridview_one);				
			MyGridViewBase adapter = new MyGridViewBase(this,getData());		
			mGridView.setAdapter(adapter);
			
			mGridView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Log.e("11111","1111222"+position);
					MyGridViewBase gridVie = (MyGridViewBase) parent.getAdapter();
//					List<MyGridView> listOne = (List<MyGridView>) gridVie.getItem(position);
					MyGridView myGrid = (MyGridView) gridVie.getItem(position);
//					MyGridView  myGrid= listOne.get(position);
					
					Intent intent = new Intent(MyGridViewActivity.this,ZoomActivity.class);
					Bundle bundle  = new Bundle();
					bundle.getInt("IMAGE",myGrid.getmImage());
					intent.putExtra("BUNDLR", bundle);
					startActivity(intent);
				}
			});
		}
		public List<MyGridView> getData(){
			
			List<MyGridView> list = new ArrayList<MyGridView>();
			
			MyGridView grid = new MyGridView();
			
			grid.setmImage(R.drawable.simple1);	
			grid.setmText("第一个图片");
			list.add(grid);
	
			grid = new MyGridView();
			
			grid.setmImage(R.drawable.simple2);	
			grid.setmText("第二个图片");
			list.add(grid);
			
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple1);			
			grid.setmText("第三个图片");
			list.add(grid);
			
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple2);			
			grid.setmText("第四个图片");
			list.add(grid);
			
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple3);			
			grid.setmText("第五个图片");
			list.add(grid);
			
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple3);			
			grid.setmText("第六个图片");
			list.add(grid);
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple3);			
			grid.setmText("第七个图片");
			list.add(grid);
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple3);			
			grid.setmText("第八个图片");
			list.add(grid);
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple1);			
			grid.setmText("第九个图片");
			list.add(grid);
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple2);			
			grid.setmText("第十个图片");
			list.add(grid);
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple3);			
			grid.setmText("第11个图片");
			list.add(grid);
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple4);			
			grid.setmText("第12个图片");
			list.add(grid);
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple1);			
			grid.setmText("第13个图片");
			list.add(grid);
			
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple2);			
			grid.setmText("第14个图片");
			list.add(grid);
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple3);			
			grid.setmText("第15个图片");
			list.add(grid);
			grid = new MyGridView();
			grid.setmImage(R.drawable.simple4);			
			grid.setmText("第16个图片");
			list.add(grid);
			return list;
		}

}
