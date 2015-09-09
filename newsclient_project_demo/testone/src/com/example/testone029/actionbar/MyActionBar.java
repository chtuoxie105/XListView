package com.example.testone029.actionbar;

import java.lang.reflect.Field;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.ViewConfiguration;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.testone001.loning.R;

/***
 * actionBar的初步学习 http://blog.csdn.net/guolin_blog/article/details/18234477
 * ***/
public class MyActionBar extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar action = getActionBar();
		action.show();
		// 设置导航,就是返回键
		action.setDisplayHomeAsUpEnabled(true);
		// 让overflow按钮一直显示，不管是不是有menu键
		setOverflowShowingAlways();
	}

	/**
	 * 如果手机没有物理Menu键的话，overflow按钮就可以显示，如果有物理Menu键的话，
	 * overflow按钮就不会显示出来,次方法可以让overflow按钮一直显示,
	 * ViewConfiguration这个类中有一个叫做sHasPermanentMenuKey的静态变量，
	 * 系统就是根据这个变量的值来判断手机有没有物理Menu键的. 当然这是一个内部变量，我们无法直接访问它，但是可以通过反射的方式修改它的值，
	 * 让它永远为false就可以了
	 */
	private void setOverflowShowingAlways() {
		try {
			ViewConfiguration figtion = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(figtion, false);

		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/** 把定义menu的action按钮布局加入到title去 **/
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_actionbar_activity, menu);

		// ActionView是一种可以在ActionBar中替换Action按钮的控件，
		// 它可以允许用户在不切换界面的情况下通过ActionBar完成一些较为丰富的操作。
		// 比如说，你需要完成一个搜索功能
		MenuItem searchItem = menu.findItem(R.id.menu_actionbar_serach);
		SearchView searchView = (SearchView) searchItem.getActionView();
		// 监听搜索actionView的是否展开状态,这两个方法可以可以进行UI操作
		searchItem.setOnActionExpandListener(new OnActionExpandListener() {
			public boolean onMenuItemActionExpand(MenuItem item) {

				Log.i("11", "展开");
				return true;
			}

			public boolean onMenuItemActionCollapse(MenuItem item) {
				Log.i("11", "合并");

				return true;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

	/** actionbar中action的点击事件的监听 **/
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_actionbar_add:
			Toast.makeText(this, "添加", 0).show();
			break;

		case R.id.menu_actionbar_deletel:
			Toast.makeText(this, "删除", 0).show();
			break;
		case R.id.menu_actionbar_updata:
			Toast.makeText(this, "查询", 0).show();
			break;
		case R.id.menu_actionbar_change:
			Toast.makeText(this, "更改", 0).show();
			break;
		case android.R.id.home:
			Intent intent = NavUtils.getParentActivityIntent(this);
			if (NavUtils.shouldUpRecreateTask(this, intent)) {
				TaskStackBuilder.create(this)
						.addNextIntentWithParentStack(intent).startActivities();
			} else {
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				NavUtils.navigateUpTo(this, intent);
			}

			Toast.makeText(this, "导航的返回键的监听", 0).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
