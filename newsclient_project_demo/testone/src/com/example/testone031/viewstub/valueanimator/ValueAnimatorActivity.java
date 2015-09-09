package com.example.testone031.viewstub.valueanimator;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * 动画的学习 实现组合动画功能主要需要借助AnimatorSet这个类，这个类提供了一个play()方法
 * ，如果我们向这个方法中传入一个Animator对象(ValueAnimator或ObjectAnimator)
 * 将会返回一个AnimatorSet.Builder的实例，AnimatorSet.Builder中包括以下四个方法：
 * 
 * •after(Animator anim) 将现有动画插入到传入的动画之后执行 •after(long delay) 将现有动画延迟指定毫秒后执行
 * •before(Animator anim) 将现有动画插入到传入的动画之前执行 •with(Animator anim) 将现有动画和传入的动画同时执行
 *
 */
public class ValueAnimatorActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public void valueAnimatorOne() {
		ValueAnimator ani = ValueAnimator.ofFloat(0f, 1f);
		// ValueAnimator ani = ValueAnimator.ofInt(0,100);

		ani.setDuration(300);
		// 设置延时播放的时间
		ani.setStartDelay(500);
		// 设置动画重复播放的次数
		ani.setRepeatCount(2);
		// 设置循环模式:循环模式包括RESTART和REVERSE两种，分别表示重新播放和倒序播放的意思
		ani.setRepeatMode(ani.RESTART);

		ani.addUpdateListener(new AnimatorUpdateListener() {
			public void onAnimationUpdate(ValueAnimator animation) {

				Float f = animation.getAnimatedFraction();
				Log.i("11", "ffff>>>:" + f);
			}
		});

		ani.start();
	}

	/**
	 * 旋转:"rotation">>透明:alpha>>>将TextView在垂直方向上放大3倍再还原：scaleY
	 * 将一个TextView在5秒中内从常规变换成全透明，再从全透明变换成常规
	 */
	public void objectAnimatorTwo() {
		// ObjectAnimator obj = ObjectAnimator.ofFloat(new TextView, "alpha",
		// 1f,5f);
		//
		// obj.setDuration(500);
		// obj.start();
	}

	/**
	 * 让TextView先从屏幕外移动进屏幕，然后开始旋转360度，旋转的同时进行淡入淡出操作
	 * 
	 * •after(Animator anim) 将现有动画插入到传入的动画之后执行 
	 * •after(long delay) 将现有动画延迟指定毫秒后执行
	 * •before(Animator anim) 将现有动画插入到传入的动画之前执行 
	 * •with(Animator anim)将现有动画和传入的动画同时执行
	 */
/*	public void zuhevalueAnimator() {
		ObjectAnimator moveIn = ObjectAnimator.ofFloat(textview,
				"translationX", -500f, 0f);
		ObjectAnimator rotate = ObjectAnimator.ofFloat(textview, "rotation",
			0f, 360f);
		ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(textview, "alpha",
				1f, 0f, 1f);
		AnimatorSet animSet = new AnimatorSet();
		animSet.play(rotate).with(fadeInOut).after(moveIn);
		animSet.setDuration(5000);
		animSet.start();
		
		
		animSet.addListener(new AnimatorListener() {
			public void onAnimationStart(Animator animation) {
				//onAnimationStart()方法会在动画开始的时候调用
			}
			
			public void onAnimationRepeat(Animator animation) {
				//onAnimationRepeat()方法会在动画重复执行的时候调用
			}
			
			public void onAnimationEnd(Animator animation) {
				//onAnimationEnd()方法会在动画结束的时候调用
			}
			
			public void onAnimationCancel(Animator animation) {
				//onAnimationCancel()方法会在动画被取消的时候调用。
			}
		});
		
		//Android提供了一个适配器类，叫作AnimatorListenerAdapter，
		//使用这个类就可以解决掉实现接口繁琐的问题,之重写我们想要操作的方法
		
		animSet.addListener(new AnimatorListenerAdapter() {
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
			}
			
		});
	}*/
}
