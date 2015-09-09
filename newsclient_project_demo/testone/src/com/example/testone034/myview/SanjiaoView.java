package com.example.testone034.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SanjiaoView extends View {
	private Paint paint;
	private Path path;

	public SanjiaoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inint();
	}
	public void inres(){
		paint = new Paint();
	}
	
	public void inint() {

		paint = new Paint();
		paint.setStyle(Style.STROKE);// 设置为空心
		paint.setColor(Color.GREEN);
		paint.setAntiAlias(true);
		//设置空心线宽
		paint.setStrokeWidth(3);
		//标签为alpha透明度节点
		//paint.setAlpha(a);
		https://github.com/chtuoxie105/news.git
		
		// 三角形
		path = new Path();
		path.moveTo(10, 100);
		path.lineTo(90, 100);
		path.lineTo(50, 60);
		path.close();
//		 path.moveTo(10, 330); 
//		 path.lineTo(70, 330);
//		 path.lineTo(40, 270);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.i("11","path>>>"+path+"<<paint>>"+paint);
		
		canvas.drawPath(path, paint);
		
		paint.setTextSize(24);
		canvas.drawText("三角形", 240, 320, paint);

	}
}
