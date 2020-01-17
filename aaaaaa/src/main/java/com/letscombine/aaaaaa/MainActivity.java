package com.letscombine.aaaaaa;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long now = System.currentTimeMillis();
        // 현재시간을 date 변수에 저장한다.
        Date date = new Date(now);
        // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        // nowDate 변수에 값을 저장한다.
        String formatDate = simpleDateFormat.format(date);


        TextView tx = findViewById(R.id.zzz);

        Log.i("dddddddd", formatDate);

        tx.setText(formatDate);
    }
}
