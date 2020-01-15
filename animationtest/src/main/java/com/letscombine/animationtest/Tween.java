package com.letscombine.animationtest;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Tween extends AppCompatActivity {
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);

        linearLayout = findViewById(R.id.linear);
    }

    public void mOnClick(View v) {
        Animation ani = null;

        switch (v.getId()) {
            case R.id.translate :
                ani = AnimationUtils.loadAnimation(this, R.anim.translate);
                break;
            case R.id.rotate :
                ani = AnimationUtils.loadAnimation(this, R.anim.rotate);
                break;
            case R.id.scale :
                ani = AnimationUtils.loadAnimation(this, R.anim.scale);
                break;
            case R.id.alpha :
                ani = AnimationUtils.loadAnimation(this, R.anim.alpha);
                break;
            case R.id.set :
                ani = AnimationUtils.loadAnimation(this, R.anim.set);
                break;
        }
        linearLayout.startAnimation(ani);
    }
}
