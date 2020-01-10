package com.letscombine.threadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class StrictModeTest extends AppCompatActivity {

    final static boolean DEBUG_MODE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (DEBUG_MODE) {
            StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyDialog()
                    .build();
            StrictMode.setThreadPolicy(threadPolicy);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strict_mode_test);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnfile:
                try {
                    FileOutputStream fos = openFileOutput("test.txt", Context.MODE_WORLD_READABLE);
                    String str = "Android File IO Test";
                    fos.write(str.getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }break;

        }
    }
}
