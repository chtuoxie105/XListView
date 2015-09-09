package com.example.testone033.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class RoundView extends View {
	private static final float STROKE_WIDTH = 1F / 256F, // 描边宽度占比
			LINE_LENGTH = 3F / 32F, // 线段长度占比
			CRICLE_LARGER_RADIU = 3F / 32F,// 大圆半径
			CRICLE_SMALL_RADIU = 5F / 64F,// 小圆半径
			ARC_RADIU = 1F / 8F,// 弧半径
			ARC_TEXT_RADIU = 5F / 32F;// 弧围绕文字半径

	private Paint paint;
	private int size;// 控制边长,描边的宽度
	private float strokeWidth;// 控制边长,描边的宽度

	private float x, y;// 圆心坐标
	private float largeCricleRadiu;// 圆心半径

	public RoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	// 初始化画笔
	private void inint() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.STROKE);
		paint.setStrokeCap(Cap.ROUND);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
		// 强制长宽都一样
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		size = w;
		calculation();
	}

	// 设计参数
	private void calculation() {
		// 计算描边宽度
		strokeWidth = STROKE_WIDTH * size;
		// 圆的半径
		largeCricleRadiu = size * CRICLE_LARGER_RADIU;
		// 圆心坐标
		x = size / 2;
		y = size / 2 + size * CRICLE_LARGER_RADIU;
		setPara();
	}

	// 设置参数
	private void setPara() {
		paint.setStrokeWidth(strokeWidth);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 绘制背景
		canvas.drawColor(0xFFF29B76);
		canvas.drawCircle(x, y, largeCricleRadiu, paint);
	}
}
