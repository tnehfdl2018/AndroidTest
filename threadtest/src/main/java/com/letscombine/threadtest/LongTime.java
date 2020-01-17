package com.letscombine.threadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class LongTime extends AppCompatActivity {

    int mValue;
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_time);

        mText = findViewById(R.id.text);
    }

    public void mOnClick(View v) {
        mValue = 0;
        mHandler.sendEmptyMessage(0);
    }


    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            mValue++;
            mText.setText(Integer.toString(mValue));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (mValue < 100) {
                mHandler.sendEmptyMessage(0);
            }
        }
    };
}
