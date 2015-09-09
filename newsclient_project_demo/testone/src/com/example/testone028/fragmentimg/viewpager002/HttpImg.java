package com.example.testone028.fragmentimg.viewpager002;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;

public class HttpImg {
	private String mUrlImg;
	private LruCache<String, Bitmap> mLruCache;
	ImageView mImageView;
	/**
	 * 主要进行实例化LruCache和地址的传递
	 * @param urlImg
	 * @param imageView
	 */
	public HttpImg(String urlImg, ImageView imageView) {
		mUrlImg = urlImg;
		mImageView = imageView;
		
		saveImgStorage();
	}

	/**
	 * 获取内存空间给下载好的图片
	 */
	public void saveImgStorage() {
		//获取当前外部可用内存的大小
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		//去当前内存大小的8分之1来缓存我们的内存
		int useSize = maxMemory / 8;
		mLruCache = new LruCache<String, Bitmap>(useSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getByteCount();
			}
		};
		startAsynctaskGetImg(mUrlImg);
	}

	/**
	 * 添加图片到缓存
	 * 
	 * @param mapKey
	 * @param bitmap
	 */
	public void addBitmapToLrucache(String mapKey, Bitmap bitmap) {
		//先根据地址判断我们的存储空间是否有这张图片，有就不会返回空，就不添加
		if (getBitmapForLruCache(mapKey) == null) {
			mLruCache.put(mapKey, bitmap);
		}
	}

	/**
	 * 从缓存去图片
	 * 
	 * @param mapurl
	 * @return
	 */
	public Bitmap getBitmapForLruCache(String mapurl) {
		return mLruCache.get(mapurl);

	}
	/**
	 * 加载我们的图片，先去内存缓存里卖弄判断是否有这张图片，没有再去开启异步任务获取图片
	 * @param urls
	 */
	public void startAsynctaskGetImg(String urls) {
	
		Bitmap bitmapss = getBitmapForLruCache(urls);
		if (bitmapss != null) {
			mImageView.setImageBitmap(bitmapss);

			return;
		} else {

			new AsyncTask<String, Void, Bitmap>() {

				protected Bitmap doInBackground(String... params) {
					Bitmap bitmap = getHttpGetImg(params[0]);
					return bitmap;
				}

				protected void onPostExecute(Bitmap result) {
					super.onPostExecute(result);
					//显示在我们传进来的imageview控件上
					mImageView.setImageBitmap(result);
					//将图片加入到我们的缓存处
					addBitmapToLrucache(mUrlImg, result);
				}
			}.execute(urls);
		}
	}
	/**
	 * 联网获取图片
	 * @param urls
	 * @return
	 */
	public Bitmap getHttpGetImg(String urls) {
		InputStream input = null;
		try {
			URL url = new URL(urls);
			input = url.openStream();
			Bitmap bitmap = BitmapFactory.decodeStream(input);
			return bitmap;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
