package com.letscombine.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn;
        Button btn1;

        String name = null;
        String packages = null;

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        btn = findViewById(R.id.btneey);
        btn1 = findViewById(R.id.btneeye);

        tv1.setText(R.string.five);
        tv2.setText(R.string.seven);
        tv1.setTextColor(getResources().getColor(R.color.gut));
        tv1.setTextSize(getResources().getDimension(R.dimen.biggeer));

        final Animation an = AnimationUtils.loadAnimation(this, R.anim.anim);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.startAnimation(an);

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.startAnimation(an);

            }
        });
    }
}
