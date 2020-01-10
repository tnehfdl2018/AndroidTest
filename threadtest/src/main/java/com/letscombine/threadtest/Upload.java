package com.letscombine.threadtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

public class Upload extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
    }

    public void mOnClick(View v) {
        new AlertDialog.Builder(this)
                .setTitle("질문")
                .setMessage("업로드 하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.sendEmptyMessageDelayed(0, 10);
                    }
                })
                .setNegativeButton("아니요", null)
                .show();
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                doUpload();
            }
        }
    };

    void doUpload() {
        for (int i = 0; i < 20; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(getApplicationContext(), "업로드를 완료했습니다.", Toast.LENGTH_LONG).show();
    }
}
