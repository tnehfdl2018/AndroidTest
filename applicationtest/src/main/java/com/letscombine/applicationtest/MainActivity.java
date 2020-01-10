package com.letscombine.applicationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpdateNoMode();
    }

    void UpdateNoMode() {
        TextView txtMode = findViewById(R.id.mode);
        AndExam_Application app = (AndExam_Application) getApplication();
        if (app.getMode() == AndExam_Application.BEGINNER) {
            txtMode.setText("현재 모드 : 초보자 모드");
        }else {
            txtMode.setText("현재 모드 : 숙련자 모드");
        }
    }

    public void mOnClick(View v) {
        AndExam_Application app = (AndExam_Application) getApplication();
        switch (v.getId()) {
            case R.id.beginner :
                app.setMode(AndExam_Application.BEGINNER);
                break;
            case R.id.professional :
                app.setMode(AndExam_Application.PROFESSIONAL);
                break;
        }
        UpdateNoMode();
    }
}
