package com.letscombine.threadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ARN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arn);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnincrease :
                TextView textCounter = findViewById(R.id.txtcounter);
                int count = Integer.parseInt(textCounter.getText().toString());
                textCounter.setText(Integer.toString(count + 1));
                break;
            case R.id.btnload :
                doUpload();
                Toast.makeText(getApplicationContext(), "업로드를 완료했습니다.", Toast.LENGTH_LONG).show();
                break;
        }
    }

    void doUpload() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
