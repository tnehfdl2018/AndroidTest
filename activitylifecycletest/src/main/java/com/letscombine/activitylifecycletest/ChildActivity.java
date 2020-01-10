package com.letscombine.activitylifecycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ChildActivity extends AppCompatActivity {

    static final String TAG = "ActChild";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
    }

    public void onStart(){
        super.onStart();
        Log.i(TAG, "onStart");
    }

    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume");
    }

    public void onPause(){
        super.onPause();
        Log.i(TAG, "onPause");
    }

    public void onStop(){
        super.onStop();
        Log.i(TAG, "onStop");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
