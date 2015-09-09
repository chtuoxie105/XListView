package com.example.testone015.sharedpreferences_file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testone001.loning.R;

/**
 * Environment.getExternalStorageDirectory();//得到根目录mnt/sdcard
 * 往SD卡的外部区域:公共区域:里面添加图片 遍历根目录
 */
public class OutPublicFilesActivity extends Activity {
	private ImageView mImageFile;
	private TextView mTextFile;
	private Button mAddBtnFile, mFindBtnFile;
	private String PIC_NAME = "pic.png";// 图片的名字
	private String path;// 文件的地址
	private Bitmap bit;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file1_out_txt_layout);
		mImageFile = (ImageView) findViewById(R.id.file_out_image);
		mTextFile = (TextView) findViewById(R.id.file_out_text);
		mAddBtnFile = (Button) findViewById(R.id.file_out_btn);
		mFindBtnFile = (Button) findViewById(R.id.file_out_find_btn);

		// BitmapFactory 工厂把图片转换成bitmap型才能被添加到建的文件中
		bit = BitmapFactory.decodeResource(getResources(), R.drawable.m3);
		mImageFile.setImageBitmap(bit);// 传Bitmap工厂转换的图片
		// getExternalStoragePublicDirectory（）次方法是说明存储到外部存储区域的公共区域，括号里面是存储的类型

		mAddBtnFile.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (!isUseable()) {// 判断SD卡是否能用
					Toast.makeText(OutPublicFilesActivity.this, "外部存储不可用",
							Toast.LENGTH_SHORT).show();
					return;
				}
				Log.e("11", "111");

				// File fil = new File(path, PIC_NAME);// (地址,文件的名字)
				File file = foundExternalStoragePublicDirectory(
						Environment.DIRECTORY_PICTURES, PIC_NAME);
				path = file.getPath();// 存储的路径
				mTextFile.setText(path);// 打印文件的地址

				FileOutputStream os;
				try {
					os = new FileOutputStream(file);
					// compress();压缩图片到文件夹
					bit.compress(Bitmap.CompressFormat.PNG, 90, os);

					os.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		mFindBtnFile.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (!isUseable()) {
					Toast.makeText(OutPublicFilesActivity.this, "外部存储不可用", 0)
							.show();
				}

				File file = Environment.getExternalStorageDirectory();// 得到根目录mnt/sdcard
//				 File file = new File("storage");

				Log.e("11", "" + file);
				followFile(file);

			}
		});

	}

	/**
	 * 遍历文件夹
	 * 
	 * @param file
	 */
	public void followFile(File file) {
		File[] fileList = file.listFiles();
		if (fileList != null) {

			for (File filesList : fileList) {
				if (filesList.isDirectory()) {// 判断是文件还是目录，目录包含文件，目录就继续执行
					File fileNew = filesList.getAbsoluteFile();//获得新的路径

					followFile(fileNew);

					String s = filesList.getName();
					Log.e("查找出来的目录名1", s);
				} else {// 是文件就打印出文件的位置
					String s = filesList.getName();
					
					if(filesList.getPath().endsWith(".jpg")){
						Log.e("查找出来的文件2",s+"  " + filesList.getPath().endsWith(".jpg"));// 打印出以.png后缀名的图片
					}
				

				}
			}
		}
	}

	/**
	 * 判断SD卡是否存在 返回的是true:存在
	 * 
	 * @return
	 */
	public boolean isUseable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 返回一个外部存储区域的公共区域地址 (你要传入的数据类型图片、音乐、电影、下载内容、闹钟、铃声,次数据的名字)(Environment.,)
	 * \Pictures、Music、Movies、Download、Alarms、Ringtones;
	 * 
	 * @param parameter
	 * @param PICName
	 * @return
	 */
	public File foundExternalStoragePublicDirectory(String parameter,
			String PICName) {
		File fileAddress = Environment
				.getExternalStoragePublicDirectory(parameter);
		if (fileAddress.exists()) {
			fileAddress.mkdir();
		}
		File filePicAddress = new File(fileAddress, PICName);
		if (filePicAddress.exists()) {
			try {
				fileAddress.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return filePicAddress;
	}
}
