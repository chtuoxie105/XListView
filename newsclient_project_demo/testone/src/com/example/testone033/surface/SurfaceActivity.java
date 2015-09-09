package com.example.testone033.surface;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.testone001.loning.R;


public class SurfaceActivity extends Activity {
	private SurfaceView surface;
	private SurfaceHolder holder;
	private MediaPlayer mediaPlayer;
	private int point;
	private String path = "http://flv2.bn.netease.com/videolib3/1508/31/oTRqr7797/SD/oTRqr7797-mobile.mp4";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_surface_layout);

		mediaPlayer = new MediaPlayer();
		surface = (SurfaceView) findViewById(R.id.test_surface);
		holder = surface.getHolder();
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder.setKeepScreenOn(true);
		holder.addCallback(new Callback() {
			public void surfaceDestroyed(SurfaceHolder holder) {

			}

			public void surfaceCreated(SurfaceHolder holder) {
				if (point > 0 && path != null) {
					// 閲嶆柊鍒涘缓鎾斁
					play(point);
				}
			}

			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// 绐佸彂浜嬫晠璁板綍褰撳墠鎾斁鐨勪綅缃紝骞跺仠姝㈡挱鏀�
				if (mediaPlayer.isPlaying()) {
					point = mediaPlayer.getCurrentPosition();
					mediaPlayer.stop();
				}
			}
		});
	}

	public void panduan(String path) {
		if(path.startsWith("http")){
			play(0);
		}else{
			File file =new File(Environment.getExternalStorageDirectory(),path);
			if(file.exists()){
				path = file.getAbsolutePath();
				play(0);
			}else{
				Toast.makeText(this,"瑙嗛鍦板潃涓嶆纭�",0).show();
			}
		}
	}

	/** 鎾斁 **/
	public void play(int points) {
		try {

			mediaPlayer.reset();
			mediaPlayer.setDataSource(path);
			mediaPlayer.setDisplay(holder);
			mediaPlayer.prepare();// 缂撳瓨
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
				public void onPrepared(MediaPlayer mp) {
					mediaPlayer.start();
					if (point > 0) {
						mediaPlayer.seekTo(point);
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void onDestroy() {
		super.onDestroy();
		if (mediaPlayer != null) {
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}
}
