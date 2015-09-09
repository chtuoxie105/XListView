package com.example.testone026.json_study_bitmap;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testone001.loning.R;
import com.example.testone001.tooloclass001.ToolClassStorBitmap;

public class ListViewJsonActivity extends Activity {
	
	String urlAddress = "http://192.168.1.203/json/around";
	
	private ListView mListViewJsonData;
	//开启多线程的那个类
	private Executor mExcutor;
	//这个集合装我们的Bean数据，就是解析出来的那个数组里面的内容
	private ArrayList<ListViewBeanJson> listJSONObject = new ArrayList<ListViewBeanJson>();
	//自己定义的一个适配器
	private ListViewAdapterGotHttp mAdapter;
	//声明工具类
	private ToolClassStorBitmap mToolClassStorBitmap;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_listview_json_layout);
		// ThreadPoolExecutor这个是开启多线程的一个类，构造方法解释:
		//(一次只能开启10个线程运行，一共可以容纳100个线程在里面，每个线程运行的时间为10秒钟,后面2个不晓得啥意思，照着敲就行)；
		mExcutor = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS,
				new LinkedBlockingDeque<Runnable>());
		//封装的一个工具类，就是他们说的框架啥的，意思就是第一次接触框架了
		mToolClassStorBitmap = ToolClassStorBitmap.getIntance();
		//开启工具类里面的多线程运行取图片和数据；
		mToolClassStorBitmap.startMoreThread();
		//为了防止上下滑动时。图片会变和错位，采用了缓存android提供的LruCache
		mToolClassStorBitmap.getBitmapStorageSpace();

		mAdapter = new ListViewAdapterGotHttp(this);
		mListViewJsonData = (ListView) findViewById(R.id.json_listview_for_http);
		getHttpData(urlAddress);
	}

	/**
	 * 启动多个线程取取JSON对象
	 */
	public void getHttpData(String url) {
		new AsyncTask<String, Void, String>() {
			protected String doInBackground(String... params) {
				String str = httpGetMesg(params[0]);
				return str;
			}

			protected void onPostExecute(String result) {
				try {
					// 先获得整体的JSON对象
					JSONObject jsonObkect = new JSONObject(result);
					// 从整体的JSON对象中此处获得info的JSON对象
					JSONObject jsonInfo = jsonObkect.getJSONObject("info");
					// 再从info对象中获得我们的JSON数组对象merchantKey，这就是我们最终需要的数据来源数组
					JSONArray jsonMerchantKey = jsonInfo
							.getJSONArray("merchantKey");
					// 这儿就可以用我们的循环遍历方法去操作我们的数组了
					int n = jsonMerchantKey.length();
					for (int i = 0; i < n; i++) {
						//获得数组里面的具体对象，然后从他们里面抽取我们需要的数据
						JSONObject jsonArrayDat = jsonMerchantKey
								.getJSONObject(i);
						ListViewBeanJson beanJson = new ListViewBeanJson();

						beanJson.setShopImg(jsonArrayDat.getString("picUrl"));
						beanJson.setCardShopImg(jsonArrayDat
								.getString("cardType"));
						beanJson.setGroupShopImg(jsonArrayDat
								.getString("groupType"));
						beanJson.setCouponShopImg(jsonArrayDat
								.getString("couponType"));

						beanJson.setShopNameText(jsonArrayDat.getString("name"));
						beanJson.setShopMessageText(jsonArrayDat
								.getString("coupon"));
						beanJson.setShopAddressText(jsonArrayDat
								.getString("location"));
						beanJson.setShopMapText(jsonArrayDat
								.getString("distance"));
						
						listJSONObject.add(beanJson);
					}
					mAdapter.setData(listJSONObject);
					mListViewJsonData.setAdapter(mAdapter);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}.executeOnExecutor(mExcutor, url);
	}

	/**
	 * 网络获取JSON对象
	 */
	public String httpGetMesg(String urls) {
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(urls);
			HttpResponse response = httpClient.execute(httpGet);
			int n = response.getStatusLine().getStatusCode();
			if (n == HttpStatus.SC_OK) {
				String line = EntityUtils.toString(response.getEntity(),
						"UTF-8");
				return line;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 自定义适配器，并添加数据到布局
	 */
	public class ListViewAdapterGotHttp extends BaseAdapter {
		private LayoutInflater infalter;
		private ArrayList<ListViewBeanJson> listJSONAdapter = new ArrayList<ListViewBeanJson>();

		public ListViewAdapterGotHttp(Context context) {
			infalter = LayoutInflater.from(context);
		}

		public void setData(ArrayList<ListViewBeanJson> listJSONObjects) {
			listJSONAdapter = listJSONObjects;
			notifyDataSetChanged();
		}

		public int getCount() {
			return listJSONAdapter.size();
		}

		public Object getItem(int position) {
			return listJSONAdapter.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			final ListViewBeanJsonReasou mListViewBeanJson;
			if (convertView == null) {
				convertView = infalter.inflate(
						R.layout.listview_item_jsondata_layout, null);
				mListViewBeanJson = new ListViewBeanJsonReasou();

				mListViewBeanJson.shopImg = (ImageView) convertView
						.findViewById(R.id.listview_json__shopimg);
				mListViewBeanJson.groupShopImg = (ImageView) convertView
						.findViewById(R.id.listview_json__groupshopping);
				mListViewBeanJson.couponShopImg = (ImageView) convertView
						.findViewById(R.id.listview_json__coupon);
				mListViewBeanJson.cardShopImg = (ImageView) convertView
						.findViewById(R.id.listview_json__shoppingcard);

				mListViewBeanJson.ShopNameText = (TextView) convertView
						.findViewById(R.id.listview_json_shopname);
				mListViewBeanJson.ShopMessageText = (TextView) convertView
						.findViewById(R.id.listview_json_shopmessage);
				mListViewBeanJson.ShopAddressText = (TextView) convertView
						.findViewById(R.id.listview_json_shopaddress);
				mListViewBeanJson.ShopMapText = (TextView) convertView
						.findViewById(R.id.listview_json_shopmap);
				convertView.setTag(mListViewBeanJson);
			} else {
				mListViewBeanJson = (ListViewBeanJsonReasou) convertView
						.getTag();
			}

			ListViewBeanJson jsonDataBean = (ListViewBeanJson) getItem(position);

			mListViewBeanJson.ShopNameText.setText(jsonDataBean
					.getShopNameText());
			mListViewBeanJson.ShopMessageText.setText(jsonDataBean
					.getShopMessageText());
			mListViewBeanJson.ShopAddressText.setText(jsonDataBean
					.getShopAddressText());
			mListViewBeanJson.ShopMapText
					.setText(jsonDataBean.getShopMapText());

			if (jsonDataBean.getGroupShopImg().equals("YES")) {
				mListViewBeanJson.groupShopImg
						.setImageResource(R.drawable.near_group);
			}
			if (jsonDataBean.getCouponShopImg().equals("YES")) {
				mListViewBeanJson.couponShopImg
						.setImageResource(R.drawable.near_ticket);
			}
			if (jsonDataBean.getCardShopImg().equals("YES")) {
				mListViewBeanJson.cardShopImg
						.setImageResource(R.drawable.near_card);
			}
			//从Bean类里面获取图片的地址
			String ms = jsonDataBean.getShopImg();
			mToolClassStorBitmap.loadBitmap(getResources(), ms,
					mListViewBeanJson.shopImg, R.drawable.m3);

			return convertView;
		}
	}

	class ListViewBeanJsonReasou {
		private ImageView shopImg;
		private ImageView groupShopImg;
		private ImageView couponShopImg;
		private ImageView cardShopImg;

		private TextView ShopNameText;
		private TextView ShopMessageText;
		private TextView ShopAddressText;
		private TextView ShopMapText;
	}
}
