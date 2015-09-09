package com.example.testone009.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

import com.example.testone001.loning.R;

public class MyMenuActivity extends Activity {
	private Button mPopBtn;
	private ListView mList;
	private PopupMenu mPop;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_popup);

		mPopBtn = (Button) findViewById(R.id.main_pop_btn);
		mList = (ListView) findViewById(R.id.main_list);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData());
		mList.setAdapter(adapter);

		// 注册上下文菜单
		registerForContextMenu(mList);

		mList.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				ArrayAdapter adpater = (ArrayAdapter) parent.getAdapter();
				Toast.makeText(MyMenuActivity.this,
						"长按事件 :  " + adpater.getItem(position), 1).show();
				return false;
			}
		});

		// -=============弹出菜单==============
		mPop = new PopupMenu(this, mPopBtn);//显示的位置在mPopBtn的下面
		mPop.inflate(R.menu.menu_pop_activity);//加载内容

		mPopBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mPop.show();//显示内容

			}
		});
		mPop.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.main_pop_newcons:
					Toast.makeText(MyMenuActivity.this, "正在扫描!", 1).show();
					break;
				case R.id.main_pop_addcons:
					Toast.makeText(MyMenuActivity.this, "选择添加的人", 1).show();
					break;
				case R.id.main_pop_delecons:
					Toast.makeText(MyMenuActivity.this, "请左右晃动", 1).show();
					break;
				case R.id.main_pop_findcons:
					Toast.makeText(MyMenuActivity.this, "正在查找附近的人", 1).show();
					break;
				}

				return false;
			}
		});

	}

	public ArrayList<String> getData() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("选项菜单");
		list.add("上下文菜单");
		list.add("弹出菜单");
		return list;
	}

	// ==================选项菜单===================
	/**
	 * 选项菜单的两个方法:(点击事件) 数据源方法:onCreateOptionsMenu; 监听事件方法:onOptionsItemSelected
	 */

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 2, "设置");
		menu.add(1, 0, 1, "退出");
		return true;
	}

	// 监听>>>>响应>>>>事件
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case 0:
			Toast.makeText(this, "退出成功", 1).show();
			break;
		case 1:
			Toast.makeText(this, "设置成功", 1).show();
			break;

		}
		return super.onOptionsItemSelected(item);
	}

	// ==================选项菜单===================

	// =================上下文菜单===================
	/**
	 * 上下文菜单的两个方法:(长按事件) 数据源方法:onCreateContextMenu; 监听事件方法:onContextItemSelected
	 */
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("文件操作");
		menu.add(1, 1, Menu.NONE, "发送");
		menu.add(1, 2, Menu.NONE, "标记为重要");
		menu.add(1, 3, Menu.NONE, "重命名");
		menu.add(1, 4, Menu.NONE, "删除");
	}

	// 监听>>>响应>>>>事件
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(this, "发送", 1).show();
			break;
		case 2:
			Toast.makeText(this, "标记为重要", 1).show();
			break;
		case 3:
			Toast.makeText(this, "重命名", 1).show();
			break;
		case 4:
			Toast.makeText(this, "删除", 1).show();
			break;
		}
		return super.onContextItemSelected(item);
	}
}
