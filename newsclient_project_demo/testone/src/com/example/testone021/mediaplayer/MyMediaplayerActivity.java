package com.example.testone021.mediaplayer;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testone001.loning.R;
/**
 *播放器的练习
 */
public class MyMediaplayerActivity extends Activity implements OnClickListener {
	private Button mMediaOneBtn,mSystemBtn;
	File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
	String s = file.getPath();
	String musicFile = "file://"+s;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mediaplayer_one_layout);

		mMediaOneBtn = (Button) findViewById(R.id.mediaplayer_main_one);
		mSystemBtn = (Button) findViewById(R.id.mediaplayer_main_system);
		
	
		mMediaOneBtn.setOnClickListener(this);	
		mSystemBtn.setOnClickListener(this);	
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mediaplayer_main_one:
			mediaPlayerAddress();
			break;
		case R.id.mediaplayer_main_system:
			mediaPlayerSyatem();
			break;
		}
	}
	/**
	 * 调用系统播放器,要加具体的文件名
	 */
	public void mediaPlayerSyatem(){
		String a = musicFile+"/qianlvyouhun.mp3";
		Uri uri = Uri.parse(a);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri,"audio/mp3");
		startActivity(intent);
	}
	/**
	 * 自己去写播放器的一些方法，播放自己给的途径文件，要加具体的文件名
	 */
	public void mediaPlayerAddress(){
		
		String as = musicFile+"/qianlvyouhun.mp3";
		MediaPlayer media = new MediaPlayer();
		try {
			media.setDataSource(as);
			media.prepare();
			media.start();
			media.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					// 监听播放结束
					
				}
			});
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		
	}
}
