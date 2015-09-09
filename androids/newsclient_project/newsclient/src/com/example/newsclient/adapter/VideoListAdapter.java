package com.example.newsclient.adapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.NetworkImageView;
import com.example.newsclient.R;
import com.example.newsclient.bean.VideoBean;
import com.example.newsclient.slidingmenu.activity.RequestManager;
import com.example.newsclient.slidingmenu.activity.VideoActivity.DestroyMediaplayer;

public class VideoListAdapter extends BaseAdapter implements DestroyMediaplayer {
	List<VideoBean> mList = new ArrayList<VideoBean>();
	LayoutInflater infalter;
	private Context mContxnt;
	private MediaPlayer mMediaplayer = new MediaPlayer();
	private int mPoint;
	private String pathMPF;
	private int allTime;
	private SurfaceHolder holder;
	private ImageView mImgBtn;
	private ImageView mImgBg;
	private Bitmap mBitmap;

	public VideoListAdapter(Context context) {
		infalter = LayoutInflater.from(context);
		mContxnt = context;
	}

	public int getCount() {
		return mList.size();
	}

	public void setData(List<VideoBean> list) {
		mList = list;
		notifyDataSetChanged();
	}

	public void addData(List<VideoBean> list) {
		mList.addAll(list);
		notifyDataSetChanged();
	}

	public Object getItem(int position) {
		return mList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {
		final VideoResource mVideoResource;
		if (convertView == null) {
			convertView = infalter.inflate(R.layout.sliding_video_item_layout,
					null);
			mVideoResource = new VideoResource();

			mVideoResource.surfaceView = (SurfaceView) convertView
					.findViewById(R.id.surfaceView_video);
			mVideoResource.imgViewItem = (NetworkImageView) convertView
					.findViewById(R.id.video_img_view);
			mVideoResource.imgViewItemBtn = (ImageView) convertView
					.findViewById(R.id.video_img_btn);

			mVideoResource.titleTxt = (TextView) convertView
					.findViewById(R.id.video_title_txt);
			mVideoResource.contentTxt = (TextView) convertView
					.findViewById(R.id.video_content_txt);
			mVideoResource.timeTxt = (TextView) convertView
					.findViewById(R.id.video_time_txt);
			mVideoResource.playcontentTxt = (TextView) convertView
					.findViewById(R.id.video_playcontent_txt);
			mVideoResource.followTxt = (TextView) convertView
					.findViewById(R.id.video_follow_txt);
			convertView.setTag(mVideoResource);
		} else {
			mVideoResource = (VideoResource) convertView.getTag();
		}
		mImgBtn = mVideoResource.imgViewItemBtn;
		mImgBg = mVideoResource.imgViewItem;

		VideoBean bean = (VideoBean) getItem(position);
		final String videoUrl = bean.getMp4_url();
		String imgUrl = bean.getCover();

		// imgVolley(imgUrl, mVideoResource.imgViewItem);
		netWorkImageView(imgUrl, mVideoResource.imgViewItem);
		mVideoResource.titleTxt.setText(bean.getTitle());
		mVideoResource.contentTxt.setText(bean.getDescription());
		mVideoResource.timeTxt.setText("时间:"
				+ timeIntToString(bean.getLength()));
		mVideoResource.playcontentTxt.setText(bean.getPlayCount() + "次");
		mVideoResource.followTxt.setText(bean.getReplyCount() + "评论");
		surfaceToMpfour(mVideoResource.surfaceView);
		mVideoResource.imgViewItemBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mVideoResource.imgViewItem.setVisibility(View.INVISIBLE);
				mVideoResource.imgViewItemBtn.setVisibility(View.INVISIBLE);
				refrashPath(videoUrl);
			}
		});

		return convertView;
	}

	/** 实例化播放器的相关属性 */
	public void surfaceToMpfour(SurfaceView surfaceView) {
		holder = surfaceView.getHolder();
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder.setKeepScreenOn(true);

		holder.addCallback(new Callback() {
			public void surfaceDestroyed(SurfaceHolder holder) {

			}

			public void surfaceCreated(SurfaceHolder holder) {
				if (mPoint > 0 && mMediaplayer != null) {
					playMPFour(mPoint);
				}
			}

			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				if (mMediaplayer.isPlaying()) {
					mPoint = mMediaplayer.getCurrentPosition();
					mMediaplayer.stop();
				}
			}
		});
	}

	/** 判断视频的来源 */
	public void refrashPath(String path) {
		if (path.startsWith("http")) {
			pathMPF = path;
			playMPFour(0);
		} else {
			File file = new File(Environment.getExternalStorageDirectory(),
					path);
			if (file.exists()) {
				pathMPF = file.getAbsolutePath();
				playMPFour(0);
			} else {
				Toast.makeText(mContxnt, "视频地址不正确!", Toast.LENGTH_SHORT).show();
			}
		}
	}

	/** 播放 */
	public void playMPFour(int point) {

		new Thread(new Runnable() {
			public void run() {
				mMediaplayer.reset();
				try {
					mMediaplayer.setDataSource(pathMPF);
					mMediaplayer.setDisplay(holder);
					mMediaplayer.prepare();// 缓存
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		threadMediaplayerOver();

		mMediaplayer.setOnPreparedListener(new OnPreparedListener() {
			public void onPrepared(MediaPlayer mp) {
				mMediaplayer.start();
				if (mPoint > 0) {
					mMediaplayer.seekTo(mPoint);
				}
			}
		});

	}

	/** 开启线程去监听结束 */
	public void threadMediaplayerOver() {
		boolean temp = true;
		while (temp) {
			new Thread(new Runnable() {
				public void run() {
					if (mPoint == allTime && mImgBtn != null && mImgBg != null
							&& mBitmap != null) {
						mImgBg.setVisibility(View.VISIBLE);
						mImgBtn.setVisibility(View.VISIBLE);
						mImgBtn.setImageResource(R.drawable.biz_news_list_icon_video);
						mImgBg.setImageBitmap(mBitmap);
					}
				}
			}).start();
		}
	}

	/** Volley联网取图片 */
	public void imgVolley(String imgUrl, ImageView imgView) {
		ImageLoader imageLoader = RequestManager.getImageLoader();

		ImageListener listener = ImageLoader.getImageListener(imgView,
				R.drawable.moren_bitmap, android.R.drawable.ic_input_delete);
		ImageContainer container = imageLoader.get(imgUrl, listener);
		mBitmap = container.getBitmap();
	}

	/** 处理整形的时间 **/
	public String timeIntToString(int timeLength) {
		allTime = timeLength;
		int minute = timeLength / 60;
		int secTime = timeLength & 60;
		String minuteString;
		String secString;
		if (minute > 0 && minute < 10) {
			minuteString = "0" + minute;
		} else if (minute < 0) {
			minuteString = "00";
		} else {
			minuteString = "" + minute;
		}
		if (secTime < 10) {
			secString = "0" + secTime;
		} else {
			secString = "" + secTime;
		}
		return minuteString + ":" + secString;
	}

	public void netWorkImageView(String url, NetworkImageView imageView) {

		imageView.setDefaultImageResId(R.drawable.moren_bitmap);
		imageView.setErrorImageResId(android.R.drawable.ic_input_delete);
		imageView.setImageUrl(url, RequestManager.getImageLoader());

	}

	public class VideoResource {
		private SurfaceView surfaceView;
		private NetworkImageView imgViewItem;
		private ImageView imgViewItemBtn;
		private TextView titleTxt;
		private TextView contentTxt;
		private TextView timeTxt;
		private TextView playcontentTxt;
		private TextView followTxt;
	}

	public void destroyMediaplayer() {
		if (mMediaplayer != null) {
			mMediaplayer.release();
			mMediaplayer = null;
		}
	}
}
