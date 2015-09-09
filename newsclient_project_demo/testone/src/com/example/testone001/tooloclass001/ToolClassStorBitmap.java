package com.example.testone001.tooloclass001;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

/**
 * 封装了网络取图片，缓存，上下滑动时，图片错位的问题
 * 
 * @author Administrator
 *
 */
public class ToolClassStorBitmap {
	private Executor mExcutor;
	private LruCache<String, Bitmap> mLruCache;
	public static ToolClassStorBitmap mToolClassStorBitmap = null;

	
	/** 单例模式 */
	public static ToolClassStorBitmap getIntance() {
		if (mToolClassStorBitmap == null) {
			mToolClassStorBitmap = new ToolClassStorBitmap();
		}
		return mToolClassStorBitmap;
	}
	/**
	 * 设置多线程取图片
	 */
	public void startMoreThread() {
		mExcutor = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS,
				new LinkedBlockingDeque<Runnable>());
	}

	/**
	 * LruCache:在程序内存达到设定值时会将最少最近使用的图片移除掉。 先获取存储器最大内存， 再取6分之1来存储图片
	 */
	public void getBitmapStorageSpace() {
		// 获取应用程序最大应用内存
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		// 只需要其最大内存的8分之1
		int cacheSize = maxMemory / 8;
		mLruCache = new LruCache<String, Bitmap>(cacheSize) {
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getByteCount();
			}
		};
	}

	/**
	 * 将图片存储到LruCache 采用键值对进行添加，先判断内存是否存在此地址，
	 */
	public void storeBitmapToMemory(String mapKey, Bitmap map) {
		if (getBitmapToMemory(mapKey) == null) {
			mLruCache.put(mapKey, map);
		}
	}

	/**
	 * 从存储区域去取图片
	 * 
	 */
	public Bitmap getBitmapToMemory(String mapKey) {
		return mLruCache.get(mapKey);
	}

	/**
	 * 网络获得图片
	 */
	public Bitmap getBitmapForHttp(String httpUrl) {
		InputStream input = null;
		try {
			URL url = new URL(httpUrl);
			input = url.openStream();
			Bitmap map = BitmapFactory.decodeStream(input);
			return map;
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

	public void loadBitmap(Resources rea, String imageUrl, ImageView imageView,
			int resImgID) {
		
		// 先判断内存里面是否有这张图片,有就直接放上去，然后结束掉，没有就执行下面的
		Bitmap mapToLruCache = getBitmapToMemory(imageUrl);
		if (mapToLruCache != null) {
			imageView.setImageBitmap(mapToLruCache);
			return;
		} 

			if (cancelPotentialWork(imageUrl, imageView)) {
				BitmapWorkerTask task = new BitmapWorkerTask(imageView);

				AsyncDrawable asyncDrawable = new AsyncDrawable(rea,
						BitmapFactory.decodeResource(rea, resImgID), task);
				imageView.setImageDrawable(asyncDrawable);

				if (mExcutor == null) {
					task.execute(imageUrl);
				} else {
					task.executeOnExecutor(mExcutor, imageUrl);
				}
		}
	}

	public static boolean cancelPotentialWork(String imageUrl,
			ImageView imageView) {
		final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

		if (bitmapWorkerTask != null) {
			final String bitmapData = bitmapWorkerTask.data;
			//判断2个地址是否相同，即图片是否存在
			if (!bitmapData.equals(imageUrl)) {
				// Cancel previous task
				bitmapWorkerTask.cancel(true);
			} else {
				// The same work is already in progress
				return false;
			}
		}
		// No task associated with the ImageView, or an existing task was
		// cancelled
		return true;
	}

	private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
		if (imageView != null) {
			final Drawable drawable = imageView.getDrawable();
			
			if (drawable instanceof AsyncDrawable) {
				final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
				return asyncDrawable.getBitmapWorkerTask();
			}
		}
		return null;
	}

	/**
	 * 创建一个专用的Drawable的子类来储存返回工作任务的引用。在这种情况下，当任务完成时BitmapDrawable会被使用
	 * 
	 */
	static class AsyncDrawable extends BitmapDrawable {
		private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

		public AsyncDrawable(Resources res, Bitmap bitmap,
				BitmapWorkerTask bitmapWorkerTask) {
			super(res, bitmap);
			bitmapWorkerTaskReference = new WeakReference<BitmapWorkerTask>(
					bitmapWorkerTask);
		}

		public BitmapWorkerTask getBitmapWorkerTask() {
			return (BitmapWorkerTask) bitmapWorkerTaskReference.get();
		}
	}
	//先建一个类，继承我们的异步线程任务，返回BitmapWorkerTask对象，不晓得这个对象干啥用的
	class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
		private final WeakReference<ImageView> imageViewReference;
		private String data = "";

		public BitmapWorkerTask(ImageView imageView) {
			// Use a WeakReference to ensure the ImageView can be garbage
			// collected
			imageViewReference = new WeakReference<ImageView>(imageView);
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(String... params) {
			data = params[0];
			//网络取图片，并返回
			return getBitmapForHttp(data);
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (isCancelled()) {
				bitmap = null;
			}

			if (imageViewReference != null && bitmap != null) {
				final ImageView imageView = (ImageView) imageViewReference
						.get();
				final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
				if (this == bitmapWorkerTask && imageView != null) {
					imageView.setImageBitmap(bitmap);
					// 内存没有这张图片，就添加进去
					storeBitmapToMemory(data, bitmap);
				}
			}
		}
	}
}
