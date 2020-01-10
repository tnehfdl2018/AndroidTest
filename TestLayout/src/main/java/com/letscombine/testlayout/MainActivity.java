package com.letscombine.testlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView img;
        Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;

        img = findViewById(R.id.img);
        btn1 = findViewById(R.id.numLin1);
        btn2 = findViewById(R.id.numLin2);
        btn3 = findViewById(R.id.numLin3);
        btn4 = findViewById(R.id.numLin4);
        btn5 = findViewById(R.id.numLin5);
        btn6 = findViewById(R.id.numLin6);
        btn7 = findViewById(R.id.numLin7);
        btn8 = findViewById(R.id.numLin8);


        img.setImageResource(R.drawable.aaa);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setScaleType(ImageView.ScaleType.MATRIX);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setScaleType(ImageView.ScaleType.CENTER);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setScaleType(ImageView.ScaleType.CENTER_CROP);

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setScaleType(ImageView.ScaleType.FIT_XY);

            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setScaleType(ImageView.ScaleType.FIT_CENTER);

            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setScaleType(ImageView.ScaleType.FIT_START);

            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setScaleType(ImageView.ScaleType.FIT_END);

            }
        });
    }
}
