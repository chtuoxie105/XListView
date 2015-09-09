package com.example.testone021.mediaplayer;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.testone001.loning.R;

public class KuGuActivity extends Activity implements OnClickListener {
	private ImageView mUpMusicBtn,mStartAndSuspendMusicBtn, mNextMusicBtn;

	private SeekBar mSeekBarMusic;
	private TextView mAllTimeText, mCurrentTimeText;

	private Handler mHandler = new Handler();
	private MediaPlayer media = new MediaPlayer();

	String mMusicAddress = Environment.getExternalStoragePublicDirectory(
			Environment.DIRECTORY_MUSIC).getPath();
	String resetAddress = "file://" + mMusicAddress + "/qianlvyouhun.mp3";
	private boolean timeRefrsh = true;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kugu_mediaplayer_one_layout);

		mUpMusicBtn = (ImageView) findViewById(R.id.kuguo_up_music_btn);
		mStartAndSuspendMusicBtn = (ImageView) findViewById(R.id.kuguo_start_and_suspend_music_btn);
		mNextMusicBtn = (ImageView) findViewById(R.id.kuguo_next_music_btn);
		mSeekBarMusic = (SeekBar) findViewById(R.id.kuguo_seekbar);
		mCurrentTimeText = (TextView) findViewById(R.id.kuguo_current_time_text);
		mAllTimeText = (TextView) findViewById(R.id.kuguo_all_time_text);

		Log.i("11", resetAddress);
		new Thread(new SeekBarThread()).start();

		uploadResrouce(resetAddress);// 设置资源，准备

		mStartAndSuspendMusicBtn.setOnClickListener(this);
		mUpMusicBtn.setOnClickListener(this);
		mNextMusicBtn.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.kuguo_up_music_btn:// 上一曲
			media.reset();
			uploadResrouce(resetAddress);
			mStartAndSuspendMusicBtn.setImageResource(R.drawable.kugou1);
			mediaPlayerMusic();
			break;
		case R.id.kuguo_start_and_suspend_music_btn:// 暂停/开始
			mediaPlayerMusic();
			mStartAndSuspendMusicBtn.setImageResource(R.drawable.kugou2);
			break;
		case R.id.kuguo_next_music_btn:// 下一曲
			media.reset();
			uploadResrouce(resetAddress);
			mStartAndSuspendMusicBtn.setImageResource(R.drawable.kugou1);
			mediaPlayerMusic();
			break;
		}

	}

	/**
	 * 加载资源，并启动
	 */
	public void uploadResrouce(String s) {
		try {
			mStartAndSuspendMusicBtn.setImageResource(R.drawable.kugou2);
			media.setDataSource(s);
			media.prepare();
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 启动播放，要么运行，要么暂停
	 */
	public void mediaPlayerMusic() {
		if (media.isPlaying()) {// 如果是正在运行，点击就暂停
			mStartAndSuspendMusicBtn.setImageResource(R.drawable.kugou1);
			media.pause();// 暂停状态
			Log.i("11", "暂停");
		}
		// 进来时:没有运行状态，-运行
		else {
			Log.i("11", "重新开始播放");
			mStartAndSuspendMusicBtn.setImageResource(R.drawable.kugou2);
			media.start();
		}

	}

	protected void onDestroy() {
		super.onDestroy();
		timeRefrsh = false;
	}

	public class SeekBarThread implements Runnable {
		public void run() {
			while (timeRefrsh) {
				if (media.isPlaying() && media != null) {
					mSeekBarMusic.setMax(media.getDuration());
					mSeekBarMusic.setProgress(media.getCurrentPosition());
				}
				mHandler.post(new Runnable() {
					public void run() {
						// 设置总的时间
						int allPoint = (media.getDuration() / 1000) / 60;
						int allSec = (media.getDuration() / 1000) % 60;
						if (allSec < 10 && allPoint < 10) {
							mAllTimeText
									.setText("0" + allPoint + ":0" + allSec);
						} else if (allSec >= 10 && allPoint < 10) {
							mAllTimeText.setText("0" + allPoint + ":" + allSec);
						} else if (allSec < 10 && allPoint >= 10) {
							mAllTimeText.setText(allPoint + ":0" + allSec);
						}

						// 设置正在进行时的时间
						int currentPoint = (media.getCurrentPosition() / 1000) / 60;
						int currentSec = (media.getCurrentPosition() / 1000) % 60;
						if (currentSec < 10) {
							mCurrentTimeText.setText("0" + currentPoint + ":0"
									+ currentSec);
						} else {
							mCurrentTimeText.setText("0" + currentPoint + ":"
									+ currentSec);
						}
					}

				});
				try {
					// 设置刷新的时间
					Thread.sleep(1000 - (media.getCurrentPosition() % 1000));
					int n = 1000 - (media.getCurrentPosition() % 1000);
					Log.i("11", "刷新的时间......" + media.getCurrentPosition());
					Log.i("11", "刷新的时间" + n);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
