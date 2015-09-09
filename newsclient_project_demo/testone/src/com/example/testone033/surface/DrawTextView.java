package com.example.testone033.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class DrawTextView extends View {
	private Paint paint;
	private TextPaint textPain;
	private Path path;
	public DrawTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void inint() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.CYAN);
		paint.setStrokeWidth(5);

		textPain = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG
				| Paint.LINEAR_TEXT_FLAG);
		textPain.setColor(Color.DKGRAY);
		textPain.setTextSize(20);
		// 实例化路径   
		path = new Path();
		 // 添加一条弧线到Path中  
		RectF rec=  new RectF(100, 100, 300, 400);
		//CW文字在椭圆的外面；CCW文字在椭圆的里面
		path.addOval(rec, Direction.CW);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		 // 绘制路径   
		canvas.drawPath(path, paint);
		// 绘制路径上的文字   
		canvas.drawTextOnPath("adasdasdadas", path, 0, 0, paint);
	}
}
