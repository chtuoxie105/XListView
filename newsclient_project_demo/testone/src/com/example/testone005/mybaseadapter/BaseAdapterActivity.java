package com.example.testone005.mybaseadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.testone001.loning.R;

public class BaseAdapterActivity extends Activity {
	private ListView mListV;	
	
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			setContentView(R.layout.activity_list_view);
			mListV = (ListView)findViewById(R.id.list_view);
			
			MyBaseadapter  base = new MyBaseadapter(this,getData());
			
			mListV.setAdapter(base);
		}
		
		
		public List<ResourceBean> getData(){
			List<ResourceBean> list = new ArrayList<ResourceBean>();
			
			ResourceBean bean = new ResourceBean();
			bean.setmImage(R.drawable.simple1);
			bean.setmContent("11 【成都】太平洋电影城");
			bean.setmTitle("仅售39元！价值58元的昭化古城门票，休闲时刻，共享欢乐.");
			list.add(bean);
			
			bean = new ResourceBean();
			bean.setmImage(R.drawable.wmgj);
			bean.setmContent("2 西北大葱");
			bean.setmTitle("不干了，跳楼大减价,完美国际大片");
			list.add(bean);

			bean = new ResourceBean();
			bean.setmImage(R.drawable.simple1);
			bean.setmContent("3 【成都】太平洋电影城");
			bean.setmTitle("2D观影票1张，3D影片需加10元");
			list.add(bean);
						
			bean = new ResourceBean();
			bean.setmImage(R.drawable.simple1);
			bean.setmContent("4 【成都】太平洋电影城");
			bean.setmTitle("2D观影票1张，3D影片需加10元");
			list.add(bean);
			
			bean = new ResourceBean();
			bean.setmImage(R.drawable.simple1);
			bean.setmContent("5 【成都】太平洋电影城");
			bean.setmTitle("2D观影票1张，3D影片需加10元");
			list.add(bean);
			
			bean = new ResourceBean();
			bean.setmImage(R.drawable.simple1);
			bean.setmContent("6 【成都】太平洋电影城");
			bean.setmTitle("2D观影票1张，3D影片需加10元");
			list.add(bean);
			
			bean = new ResourceBean();
			bean.setmImage(R.drawable.simple1);
			bean.setmContent("7 【成都】太平洋电影城");
			bean.setmTitle("2D观影票1张，3D影片需加10元");
			list.add(bean);
			
			bean = new ResourceBean();
			bean.setmImage(R.drawable.simple1);
			bean.setmContent("8 【成都】太平洋电影城");
			bean.setmTitle("2D观影票1张，3D影片需加10元");
			list.add(bean);
			
			bean = new ResourceBean();
			bean.setmImage(R.drawable.simple1);
			bean.setmContent("9 【成都】太平洋电影城");
			bean.setmTitle("2D观影票1张，3D影片需加10元");
			list.add(bean);
			return list;
		}
}
