package com.letscombine.animationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class AnimSet extends AppCompatActivity {
    ImageView mAnimTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_set);

        mAnimTarget = findViewById(R.id.anim_target2);
    }

    public void mOnClick(View v) {
        AnimationSet ani = null;
        switch (v.getId()) {
            case R.id.btn_start :
                ani = new AnimationSet(true);
                ani.setInterpolator(new LinearInterpolator());

                TranslateAnimation trans = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
                trans.setDuration(1000);
                ani.addAnimation(trans);

                AlphaAnimation alpha = new AlphaAnimation(1, 0);
                alpha.setDuration(300);
                alpha.setStartOffset(500);
                alpha.setRepeatCount(4);
                alpha.setRepeatMode(Animation.REVERSE);
                ani.addAnimation(alpha);
                break;
        }
        mAnimTarget.startAnimation(ani);
    }
}
