package com.example.testone032.animation;

import com.example.testone001.loning.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class XmlAnimationActivity extends Activity implements OnClickListener {
	private Button alphaBtn, scaleBtn, translateBtn, rotateBtn;
	private ImageView imgAnimo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animo_image_test_layout);

		alphaBtn = (Button) findViewById(R.id.animo_alpha_btn);
		scaleBtn = (Button) findViewById(R.id.animo_scale_btn);
		translateBtn = (Button) findViewById(R.id.animo_translate_btn);
		rotateBtn = (Button) findViewById(R.id.animo_rotate_btn);
		imgAnimo = (ImageView) findViewById(R.id.animo_test_img);

		alphaBtn.setOnClickListener(this);
		scaleBtn.setOnClickListener(this);
		translateBtn.setOnClickListener(this);
		rotateBtn.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.animo_alpha_btn:
			Animation ani = AnimationUtils.loadAnimation(this,
					R.anim.test_one_alpha_anim);
			imgAnimo.startAnimation(ani);

			break;
		case R.id.animo_scale_btn:
			ani = AnimationUtils
					.loadAnimation(this, R.anim.test_two_scale_anim);
			imgAnimo.startAnimation(ani);
			break;
		case R.id.animo_translate_btn:
			ani = AnimationUtils.loadAnimation(this,
					R.anim.test_three_translate_anim);
			imgAnimo.startAnimation(ani);
			break;
		case R.id.animo_rotate_btn:
			ani = AnimationUtils.loadAnimation(this,
					R.anim.test_four_rotate_anim);
			imgAnimo.startAnimation(ani);
			break;

		}

	}
}
