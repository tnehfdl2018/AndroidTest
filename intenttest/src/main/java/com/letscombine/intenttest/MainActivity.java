package com.letscombine.intenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mOnClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.web :
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(intent);
                break;

            case R.id.dial :
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-2042-1992"));
                startActivity(intent);
                break;

            case R.id.picture :
                intent = new Intent(Intent.ACTION_VIEW);
                String sd = Environment.getExternalStorageDirectory().getAbsolutePath();
                Uri uri = Uri.fromFile(new File(sd + "/test.jpg"));
                intent.setDataAndType(uri, "image/jpeg");
                startActivity(intent);
                break;

            case R.id.other :
                intent = new Intent(Intent.ACTION_MAIN);
                intent.setComponent(new ComponentName("com.letscombine.activitytest", "com.letscombine.activitytest.MainActivity"));
                startActivity(intent);
                break;
        }
    }
}
