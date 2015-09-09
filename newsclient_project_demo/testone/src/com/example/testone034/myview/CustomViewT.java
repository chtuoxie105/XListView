package com.example.testone034.myview;

import com.example.testone001.loning.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class CustomViewT extends View {
	private Paint paint;
	private String text = "asdasdasdas";

	public CustomViewT(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setStyle(Style.FILL);
		
		// TypedArray是存放资源的array,1.通过上下文得到这个数组,attrs是构造函数传进来的,对应attrs.xml
		TypedArray typed = context.obtainStyledAttributes(attrs,
				R.styleable.CustomViewT);
		// 获得xml里定义的属性,格式为 名称_属性名 后面是默认值
		int textColor = typed.getColor(R.styleable.CustomViewT_textColor,
				0xFFFFFFFF);
		float textSize = typed.getDimension(R.styleable.CustomViewT_textSize,
				15.0f);

		paint.setColor(textColor);
		paint.setTextSize(textSize);
		// 为了保持以后使用该属性一致性,返回一个绑定资源结束的信号给资源
		typed.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawText(text,10,60, paint);
	}
}
