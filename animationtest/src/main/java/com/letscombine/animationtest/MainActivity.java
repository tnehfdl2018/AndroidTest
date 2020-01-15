package com.letscombine.animationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mAnimTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAnimTarget = findViewById(R.id.anim_target);
    }

    public void mOnClick(View v) {
        Animation anim = null;

        switch (v.getId()) {
            case R.id.btn_trans1 :
                anim = new TranslateAnimation(0, 0, 0, -300);
                break;

            case R.id.btn_trans2 :
                anim  = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF,1, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
                break;

            case R.id.btn_trans3 :
                anim  = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,1, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
                break;

            case R.id.btn_rotate1 :
                anim = new RotateAnimation(0, -180);
                break;

            case R.id.btn_rotate2 :
                anim = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                break;

            case R.id.btn_rotate3 :
                anim = new RotateAnimation(0, 90, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 1.0f);
                break;

            case R.id.btn_scale1 :
                anim = new ScaleAnimation(0, 1, 0, 1);
                break;

            case R.id.btn_scale2 :
                anim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                break;

            case R.id.btn_scale3 :
                anim = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                break;

            case R.id.btn_alpha1 :
                anim = new AlphaAnimation(0, 1);
                break;

            case R.id.btn_alpha2 :
                anim = new AlphaAnimation(1, 0);
                break;
        }

        anim.setDuration(1000);

        anim.setDetachWallpaper(true);
        mAnimTarget.startAnimation(anim);
    }
}
