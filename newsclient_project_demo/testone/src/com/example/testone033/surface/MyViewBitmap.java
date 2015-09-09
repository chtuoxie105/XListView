package com.example.testone033.surface;

import com.example.testone001.loning.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class MyViewBitmap extends View {
	private Paint mPaint;// 画笔
	private Context mContext;// 上下文环境的引用
	private Bitmap bitmap;// 位图
	private int x, y;// 位图绘制时左上角的距离

	public MyViewBitmap(Context context) {
		super(context);
	}

	public MyViewBitmap(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		// 实例化画笔
		inint();
		// 初始化资源
		intRes();
	}

	/** 实例化画笔，并设置抗锯齿 ***/
	public void inint() {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		ColorMatrix colorFil = new ColorMatrix(new float[]{
				 1,0,0,0,0,
				 0,1,0,0,0,
				 0,0,1,0,0,
				 0,0,0,1,0
				 });
		mPaint.setColorFilter(new ColorMatrixColorFilter(colorFil));
		// 画一个圆
		// 画笔样式分三种：
		// 1.Paint.Style.STROKE：描边
		// 2.Paint.Style.FILL_AND_STROKE：描边并填充
		// 3.Paint.Style.FILL：填充
		// mPaint.setStyle(Style.FILL_AND_STROKE);
		// 设置蓝色
		// mPaint.setColor(Color.BLUE);
		// 整体淡红色
		// mPaint.setColor(Color.argb(255,255,128,103));
		// mPaint.setStrokeWidth(10);
		// ColorMatrix colorFil = new ColorMatrix(new float[]{
		// 1,0,0,0,0,
		// 0,1,0,0,0,
		// 0,0,1,0,0,
		// 0,0,0,1,0
		// });
		// R(红的)G（绿色）B（蓝色）A（透明度）、其范围在0.0F至2.0F之间
	}

	/** 初始化资源 **/
	public void intRes() {
		bitmap = BitmapFactory.decodeResource(mContext.getResources(),
				R.drawable.btn_style_one_pressed);
		x = 50;
		y = 50;
		
		/*
		 * 计算位图绘制时左上角的坐标使其位于屏幕中心 屏幕坐标x轴向左偏移位图一半的宽度 屏幕坐标y轴向上偏移位图一半的高度
		 * 
		 * x = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 -
		 * bitmap.getWidth() / 2; y = MeasureUtil.getScreenSize((Activity)
		 * mContext)[1] / 2 - bitmap.getHeight() / 2;
		 */
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
//		canvas.drawCircle(50, 50, 100, mPaint);
		canvas.drawBitmap(bitmap,x,y,mPaint);
	}

}
