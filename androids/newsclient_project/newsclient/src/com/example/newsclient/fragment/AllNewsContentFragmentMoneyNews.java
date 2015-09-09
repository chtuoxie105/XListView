package com.example.newsclient.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyErrorHelper;
import com.android.volley.toolbox.StringRequest;
import com.example.newsclient.R;
import com.example.newsclient.adapter.ListContentHeadAdapter;
import com.example.newsclient.bean.HttpGetDataBeanOne;
import com.example.newsclient.httpclient.HttpGetDataTool;
import com.example.newsclient.httpclient.HttpGetDataTool.BackMsgByAsynctask;
import com.example.newsclient.slidingmenu.activity.RequestManager;
import com.scxh.slider.library.SliderLayout;
import com.scxh.slider.library.SliderTypes.BaseSliderView;
import com.scxh.slider.library.SliderTypes.TextSliderView;

/**
 * 此Fragment用于财经页面的数据显示，以及左右滑动的TAB也跟着挥动的处理
 * 
 * @author Administrator
 *
 */
@SuppressLint({ "SimpleDateFormat", "InflateParams", "ShowToast" })
public class AllNewsContentFragmentMoneyNews extends Fragment implements
		IXListViewListener {

	private XListView xListView;
	private SliderLayout sliderLayout;
	private ListContentHeadAdapter adapter;

	int pageNo = 0; // 页号 ，表示第几页,第一页从0开始
	int pageSize = 20; // 页大小，显示每页多少条数据
	String news_type_id = "T1348648756099"; // 新闻类型标识, 此处表示头条新闻
	String urlGet = "http://c.m.163.com/nc/article/list/" + news_type_id + "/"
			+ pageNo * pageSize + "-" + pageSize + ".html";

	private HttpGetDataTool mHttpGetDataToolMoney;
	private List<HttpGetDataBeanOne> mListJsonOne = new ArrayList<HttpGetDataBeanOne>();
	// 异步回调的接口刷新放到handler里。进行区别判断
	private int REFRESH_MSG = 1;
	private int REFRESH_CACHE = 2;

	public static String CHANGE_DATA_KEY = "key_change_data";

	private static AllNewsContentFragmentMoneyNews mAllNewsContentFragment;

	public static AllNewsContentFragmentMoneyNews newFragment() {

		mAllNewsContentFragment = new AllNewsContentFragmentMoneyNews();

		return mAllNewsContentFragment;
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mHttpGetDataToolMoney = HttpGetDataTool.setIntanceCaseHttp();
		View v = inflater.inflate(R.layout.news_table_main_layout, container,
				false);

		caseView(v, inflater);
		return v;
	}

	/** 实例化控件，联网取JSON */
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// startHttp(urlGet);
		startHttpVolley(urlGet);
	}

	/** volley框架取数据 */
	public void startHttpVolley(String httpUrl) {

		StringRequest string = new StringRequest(httpUrl,
				new Response.Listener<String>() {
					public void onResponse(String response) {
						if (!response.equals("") && response != null) {
							JSONData(response);
						}
					}
				}, new Response.ErrorListener() {
					public void onErrorResponse(VolleyError error) {
						onLoad();
						Toast.makeText(
								getActivity(),
								VolleyErrorHelper.getMessage(error,
										getActivity()), 0).show();
					}
				});
		RequestManager.getRequestQueue().add(string);
	}

	/** 联网取数据 **/
	public void startHttp(String httpUrl) {
		mHttpGetDataToolMoney.createThreadGetData(getActivity(), httpUrl,
				new BackMsgByAsynctask() {
					public void normalMsg(String normalMsg) {
						if (!normalMsg.equals("")) {
							Message msg = Message.obtain();
							msg.what = REFRESH_MSG;
							msg.obj = normalMsg;
//							handler.sendMessage(msg);
						}
					}

					public void exceptionMsg(String normalMsg) {
						if (!normalMsg.equals("")) {
							Message msg = Message.obtain();
							msg.what = REFRESH_CACHE;
							msg.obj = normalMsg;
//							handler.sendMessage(msg);
						}
					}
				});
	}

	/** 解析JSON数据,在handler里面进行网络数据的解析，控件的刷新 **/
	public void JSONData(String responce) {
		try {
			JSONObject obj = new JSONObject(responce);
			JSONArray array = obj.getJSONArray("T1348648756099");
			int intOBJ = array.length();
			for (int i = 0; i < intOBJ; i++) {
				JSONObject conObj = array.getJSONObject(i);
				HttpGetDataBeanOne beanOne = new HttpGetDataBeanOne();
				beanOne.setTitle(conObj.getString("title"));
				beanOne.setImgsrc(conObj.getString("imgsrc"));
				beanOne.setPtime(conObj.getString("ptime"));
				beanOne.setDocid(conObj.getString("docid"));
				if (conObj.has("replyCount")) {
					beanOne.setReplyCount(conObj.getString("replyCount"));
				}
				if (conObj.has("digest")) {
					beanOne.setDigest(conObj.getString("digest"));
				}
				if (conObj.has("url")) {
					beanOne.setUrl(conObj.getString("url"));
				}
				if (conObj.has("source")) {
					beanOne.setSource(conObj.getString("source"));
				}
				String[] imgStringList = new String[2];
				if (conObj.has("imgextra")) {
					JSONArray imgList = conObj.getJSONArray("imgextra");
					int k = imgList.length();
					for (int g = 0; g < k; g++) {
						imgStringList[g] = imgList.getJSONObject(g).getString(
								"imgsrc");
					}
					beanOne.setImgStringList(imgStringList);
				}

				mListJsonOne.add(beanOne);
			}
			HashMap<String, String> imgMap = new HashMap<String, String>();
			for (int i = 0; i < 12; i++) {
				if (mListJsonOne.size() > 0) {
					HttpGetDataBeanOne dataBean = mListJsonOne.get(i);
					imgMap.put(dataBean.getTitle(), dataBean.getImgsrc());
				}
			}

			// 自动播放图片
			setSliderLayoutData(imgMap);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// 停止刷新
		onLoad();
		// 第一页刷新只加载第一页的数据
		if (pageNo == 0) {
			adapter.setData(mListJsonOne);
		} else {
			// 不是第一页就叠加数据
			adapter.addData(mListJsonOne);
		}
		// 最后一页加载完，就设置上拉不可用
		if (pageNo == 20) {
			xListView.setPullLoadEnable(false);
		}
	}

	/** 设置自动滚动播放的图片 ***/
	public void setSliderLayoutData(HashMap<String, String> map) {
		sliderLayout.removeAllSliders();
		if (map.size() > 0) {
			for (String mapName : map.keySet()) {
				TextSliderView textSlidingView = new TextSliderView(
						getActivity());
				textSlidingView.description(mapName).image(map.get(mapName))
						.setScaleType(BaseSliderView.ScaleType.CenterCrop);
				sliderLayout.addSlider(textSlidingView);
			}
		}

		sliderLayout
				.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
	}

	/** 实例化控件 **/
	public void caseView(View v, LayoutInflater inflater) {

		xListView = (XListView) v
				.findViewById(R.id.news_fragment_xlistview_one);

		View listHead = v.findViewById(R.id.listview_refresh_layout);
		View sliderView = inflater.inflate(R.layout.image_sliding_layout, null);

		sliderLayout = (SliderLayout) sliderView
				.findViewById(R.id.news_listview_slider);
		adapter = new ListContentHeadAdapter(getActivity());
		xListView.setAdapter(adapter);

		xListView.addHeaderView(sliderView);
		xListView.setEmptyView(listHead);
		xListView.setPullLoadEnable(true);
		xListView.setPullRefreshEnable(true);
		xListView.setXListViewListener(this);

		// xListView.setOnItemClickListener(this);

	}

	public void onLoad() {

		xListView.stopRefresh();
		xListView.stopLoadMore();
		xListView.setRefreshTime(currentTime());// 时间

	}

	public String currentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日 HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	public void onRefresh() {
		pageNo = 0;
		urlGet = "http://c.m.163.com/nc/article/list/" + news_type_id + "/"
				+ pageNo * pageSize + "-" + pageSize + ".html";
		startHttpVolley(urlGet);
		xListView.setPullLoadEnable(true);
	}

	public void onLoadMore() {
		if (pageNo + 20 <= 20) {
			pageNo = pageNo + 20;
			urlGet = "http://c.m.163.com/nc/article/list/" + news_type_id + "/"
					+ pageNo * pageSize + "-" + pageSize + ".html";
			startHttpVolley(urlGet);
		}
	}
	@Override
	public void onStop() {
		super.onStop();
		if (sliderLayout != null) {
			sliderLayout.stopAutoCycle();
		}
	}
}
