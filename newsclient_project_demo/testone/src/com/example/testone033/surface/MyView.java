package com.example.testone033.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View implements Runnable {
	private Paint mPaint;
	private Context mContext;
	private int pint;

	public MyView(Context context) {
		super(context);
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		// 实例化画笔
		inint();
	}

	/** 实例化画笔，并设置抗锯齿 ***/
	public void inint() {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// 画一个圆
		// 画笔样式分三种：
		// 1.Paint.Style.STROKE：描边
		// 2.Paint.Style.FILL_AND_STROKE：描边并填充
		// 3.Paint.Style.FILL：填充
		mPaint.setStyle(Style.FILL_AND_STROKE);
		// 设置蓝色
//		mPaint.setColor(Color.BLUE);
		//整体淡红色
		mPaint.setColor(Color.argb(255,255,128,103));
		mPaint.setStrokeWidth(10);
		ColorMatrix colorFil = new ColorMatrix(new float[]{
				1,0,0,0,0,
				0,1,0,0,0,
				0,0,1,0,0,
				0,0,0,1,0
		});
		//R(红的)G（绿色）B（蓝色）A（透明度）、其范围在0.0F至2.0F之间
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawCircle(50, 50, 100, mPaint);

	}

	/** 提供一个setter方法对外设置半径值，并在设置了该值后调用invalidate()方法重绘View */
	public synchronized void setPint(int point) {
		pint = point;
		invalidate();
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (pint < 200) {
					pint = pint + 10;
					//刷新
					postInvalidate();
				} else {
					pint = 0;
				}
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
