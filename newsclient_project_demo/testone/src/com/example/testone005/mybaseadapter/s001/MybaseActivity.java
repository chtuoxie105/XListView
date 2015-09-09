package com.example.testone005.mybaseadapter.s001;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.testone001.loning.MainActivity;
import com.example.testone001.loning.R;
import com.example.testone002.Serializables.AToB;
import com.example.testone005.mybaseadapter.BaseAdapterActivity;
import com.example.testone006.myGridview.MyGridViewActivity;
import com.example.testone007.SpinnerAndAutocompleterTextView.AutocompleteTextViewActivity;
import com.example.testone008.ProgressBar.MyProgressBar;
import com.example.testone008.ProgressBar.MySeekBarActivity;
import com.example.testone008.ProgressBar.MySotckProgressBar;
import com.example.testone009.Popwindow001.PopupWindowActivity;
import com.example.testone009.menu.MyMenuActivity;
import com.example.testone010.Dialog.MyDialogActivity;
import com.example.testone011.ViewPager.MyFramPagerActivity;
import com.example.testone011.ViewPager.MyViewPagerActivity;
import com.example.testone012.TabActivity.MyTabHost;
import com.example.testone013.ShapAndLayer_list.MyLayerListActivity;
import com.example.testone014.SQLiteDataBase.SqlDataBaseActivity;
import com.example.testone015.sharedpreferences_file.OutPublicFilesActivity;
import com.example.testone015.sharedpreferences_file.SharedpreferencesActivity;
import com.example.testone017.contentprovider.ContentResplvreActivity;
import com.example.testone018.service.ServiceActivity;
import com.example.testone019.broadcast.MyBroadCast;
import com.example.testone020.notification.NotificationActivity;
import com.example.testone021.mediaplayer.KuGuActivity;
import com.example.testone021.mediaplayer.MyMediaplayerActivity;
import com.example.testone022.servlet.MyAsynctaskActivity;
import com.example.testone022.servlet.TestServlet;
import com.example.testone023.httpstudy.HttpActivity;
import com.example.testone024.Http_callback.HttpToolActivity;
import com.example.testone025.json_study.JsonStudyOneActivity;
import com.example.testone025.json_study.TestArrayListJsonTwoActivity;
import com.example.testone026.json_study_bitmap.BitmapGridViewActivity;
import com.example.testone026.json_study_bitmap.ListViewJsonActivity;
import com.example.testone028.fragment.viewpager001.FragmentViewpagerTabActivity;
import com.example.testone028.fragment_one.MainFragmentActivity;
import com.example.testone028.fragment_one.MusicFragmentActivity;
import com.example.testone028.fragment_two.DialogFragmentActivity;
import com.example.testone028.fragment_two.FragmentTextTwo;
import com.example.testone028.fragmentimg.viewpager002.ImgViewpagerFragment;
import com.example.testone028.slidefragment003.TestSlidingActivity;
import com.example.testone029.actionbar.ActionBarProviderActivity;
import com.example.testone029.actionbar.MyActionBar;
import com.example.testone030.customview.ActivityCustom;
import com.example.testone031.viewstub.valueanimator.MyViewStubActivity;
import com.example.testone032.animation.XmlAnimationActivity;
import com.example.testone034.myview.SanjiaoActivity;

/**
 * 防止键盘挡住输入框 不希望遮挡设置activity属性
 *  android:windowSoftInputMode="adjustPan" 
 *  希望动态调整高度
 * android:windowSoftInputMode="adjustResize"
 */

public class MybaseActivity extends Activity implements OnItemClickListener {
	private ListView mlist;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_linear_layout);

		mlist = (ListView) findViewById(R.id.simple_linear_text_view);

		MyBaseAdapter adapter = new MyBaseAdapter(this, getdata());
		mlist.setAdapter(adapter);

		mlist.setOnItemClickListener(this);
	}

	public List<Resource> getdata() {
		List<Resource> list = new ArrayList<Resource>();

		Resource sour = new Resource();

		zuheCreat(list, "ListView学习1", MainActivity.class);
		zuheCreat(list, "自定义适配器", BaseAdapterActivity.class);
		zuheCreat(list, "参数传递2", AToB.class);
		zuheCreat(list, "GridView学习3", MyGridViewActivity.class);
		zuheCreat(list, "AutocompleteTextView学习5",
				AutocompleteTextViewActivity.class);
		zuheCreat(list, "MyProgressBar学习6", MyProgressBar.class);
		zuheCreat(list, "MySotckProgressBar学习7", MySotckProgressBar.class);
		zuheCreat(list, "MySeekBarActivity学习8", MySeekBarActivity.class);
		zuheCreat(list, "MyMenuActivity学习9", MyMenuActivity.class);
		zuheCreat(list, "PopupWindowActivity学习10", PopupWindowActivity.class);
		zuheCreat(list, "MyDialogActivity学习11", MyDialogActivity.class);
		zuheCreat(list, "MyViewPagerActivity学习12", MyViewPagerActivity.class);
		zuheCreat(list, "MyFramPagerActivity学习13", MyFramPagerActivity.class);
		zuheCreat(list, "MyTabHost学习14", MyTabHost.class);
		zuheCreat(list, "LayerList_seekbar学习15", MyLayerListActivity.class);
		zuheCreat(list, "SqlDataBaseActivity数据库的>>学习16",
				SqlDataBaseActivity.class);
		zuheCreat(list, "SharedpreferencesActivity保存数据学习17",
				SharedpreferencesActivity.class);
		zuheCreat(list, "外部存储区域>>保存数据学习18", OutPublicFilesActivity.class);

		sour = new Resource();
		sour.setmTitleText("文件管理器>>学习19");
		// sour.setToMap("intent", new Intent(this,
		// MyExternalPublicActivity.class));
		list.add(sour);

		zuheCreat(list, "ContentProvider>>学习20", ContentResplvreActivity.class);
		zuheCreat(list, "ContentProvider>>学习20", ContentResplvreActivity.class);
		zuheCreat(list, "ServiceActivity服务>Bind启动式>学习", ServiceActivity.class);
		zuheCreat(list, "MyBroadCast广播>自定义、短信>学习", MyBroadCast.class);
		zuheCreat(list, "NotificationActivity通知学习", NotificationActivity.class);
		zuheCreat(list, "MyMediaplayerActivity播放器学习",
				MyMediaplayerActivity.class);
		zuheCreat(list, "KuGuo播放器学习", KuGuActivity.class);
		zuheCreat(list, "TestServlet连接html网页学习", TestServlet.class);
		zuheCreat(list, "MyAsynctaskActivity异步处理机制", MyAsynctaskActivity.class);
		zuheCreat(list, "HttpActivity连接服务器", HttpActivity.class);
		zuheCreat(list, "HttpToolActivity登陆确认", HttpToolActivity.class);
		zuheCreat(list, "JsonStudyActivity", JsonStudyOneActivity.class);
		zuheCreat(list, "TestArrayListJsonActivity",
				TestArrayListJsonTwoActivity.class);
		zuheCreat(list, "BitmapActivity网络取图片", BitmapGridViewActivity.class);
		zuheCreat(list, "ListViewJsonActivity网络取数据", ListViewJsonActivity.class);
		zuheCreat(list, "MainFragmentActivity静态添加", MainFragmentActivity.class);
		zuheCreat(list, "MusicFragmentActivity动态添加",
				MusicFragmentActivity.class);
		zuheCreat(list, "FragmentTextTwo两个fragment通过activity传递参数",
				FragmentTextTwo.class);
		zuheCreat(list, "MyActionBar学习", MyActionBar.class);
		zuheCreat(list, "ActionBarProviderActivity学习",
				ActionBarProviderActivity.class);
		zuheCreat(list, "自定义布局的学习", ActivityCustom.class);
		zuheCreat(list, "ViewStub内存优化之：隐藏一些开始不必要的的控件", MyViewStubActivity.class);
		zuheCreat(list, "自定义DialogFragment，并实现回调机制",
				DialogFragmentActivity.class);
		zuheCreat(list, "fragmentViewpager和tab混合使用",
				FragmentViewpagerTabActivity.class);
		zuheCreat(list, "ImgViewpagerFragment网络取图片", ImgViewpagerFragment.class);
		zuheCreat(list, "SlidingMenuActivity", TestSlidingActivity.class);
		zuheCreat(list, "XmlAnimationActivity", XmlAnimationActivity.class);
		zuheCreat(list, "自定义三角形控件", SanjiaoActivity.class);

		return list;
	}

	public void zuheCreat(List<Resource> lists, String s, Class<?> mmm) {
		Resource sour = new Resource();
		sour.setmTitleText(s);
		sour.setToMap("intent", new Intent(this, mmm));
		lists.add(sour);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		MyBaseAdapter asd = (MyBaseAdapter) parent.getAdapter();
		System.out.println("111111111" + asd);
		// List<Resource> s = (List<Resource>) asd.getItem(position);

		Resource s = (Resource) asd.getItem(position);

		System.out.println("111111111" + s);
		// Resource sou = s.get(position);

		// System.out.println("111111111"+sou);
		// Intent n = (Intent) sou.getToMap("intent");
		Intent n = (Intent) s.getToMap("intent");

		System.out.println("111111111555>>" + n);
		startActivity(n);
	}
}
