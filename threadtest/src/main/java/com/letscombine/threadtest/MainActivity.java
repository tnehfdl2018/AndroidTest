package com.letscombine.threadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int mMainValue = 0;
    int mBackValue = 0;
    TextView mMainText;
    TextView mBackText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainText = findViewById(R.id.mainValue);
        mBackText = findViewById(R.id.backValue);


        BackThread thread = new BackThread();
        thread.setDaemon(true);
        thread.start();

//        BackRunnable runnable = new BackRunnable();
//        Thread thread = new Thread(runnable);
//        thread.setDaemon(true);
//        thread.start();
    }

    public void mOnClick(View v) {
        mMainValue++;
        mMainText.setText("MainValue : " + mMainValue);
    }

//    public void mOnClick(View v) {
//        mMainValue++;
//        mMainText.setText("MainValue : " + mMainValue);
//        mBackText.setText("BackValue : " + mBackValue);
//    }

    class BackThread extends Thread {
        public void run() {
            while (true) {
                mBackValue++;
                mHandler.sendEmptyMessage(0);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    ;
                }
            }
        }
    }

    Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                mBackText.setText("BackValue : " + mBackValue);
            }
        }
    };

//    class BackRunnable implements Runnable {
//
//        @Override
//        public void run() {
//            while(true){
//                mBackValue++;
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
