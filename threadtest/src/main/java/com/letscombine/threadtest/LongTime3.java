package com.letscombine.threadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class LongTime3 extends AppCompatActivity {

    int mValue;
    TextView mText;
    ProgressDialog mProgressDialog;
    boolean mQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_time3);

        mText = findViewById(R.id.text);
    }

    @SuppressWarnings("deprecation")
    public void mOnClick(View v) {
        mValue = 0;
        showDialog(0);
        mQuit = false;
        mHandler.sendEmptyMessage(0);
    }


    @SuppressWarnings("deprecation")
    protected Dialog onCraeteDialog(int id) {
        switch (id) {
            case 0 :
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setTitle("Updating");
                mProgressDialog.setMessage("Wait...");
                mProgressDialog.setCancelable(false);
                mProgressDialog.setButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mQuit = true;
                        dismissDialog(0);
                    }
                });
                return mProgressDialog;
        }
        return null;
    }

    Handler mHandler = new Handler() {
        @SuppressWarnings("deprecation")
        public void handleMessage(Message msg) {
            mValue++;
            mText.setText(Integer.toString(mValue));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (mValue < 100 && mQuit == false) {
                mProgressDialog.setProgress(mValue);
                mHandler.sendEmptyMessage(0);
            }else {
                dismissDialog(0);
            }
        }
    };
}
