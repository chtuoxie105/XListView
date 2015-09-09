package com.example.testone034.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
	private String text ="sdbhkjsdasda";
	private Paint paint;
	private Rect rect;
	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inint();
	}
	public void inint(){
		paint = new Paint();
		paint.setColor(Color.DKGRAY);
		paint.setStyle(Style.FILL);
		//矩形
		rect = new Rect(10, 10, 100, 100);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//矩形
		canvas.drawRect(rect, paint);
		
		//文本
		paint.setColor(Color.RED);
		paint.setTextSize(15.0f);
		canvas.drawText(text,10, 60, paint);
	}
}
