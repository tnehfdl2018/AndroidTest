package com.letscombine.activitylifecycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "ActParent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mOnClick(View view) {
        Log.i(TAG, "startActivity");
        Intent intent = new Intent(this, ChildActivity.class);
        startActivity(intent);
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

    public void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart");
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
