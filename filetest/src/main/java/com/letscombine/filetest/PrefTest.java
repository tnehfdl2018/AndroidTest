package com.letscombine.filetest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrefTest extends AppCompatActivity {

    TextView textName;
    TextView textStNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref_test);

        textName = findViewById(R.id.name);
        textStNum = findViewById(R.id.st_num);

        SharedPreferences pref = getSharedPreferences("PrefTest", 0);
        String name = pref.getString("name", "이름 없음");
        textName.setText(name);

        int stNum = pref.getInt("StNum", 20200117);
        textStNum.setText(String.valueOf(stNum));
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences pref = getSharedPreferences("PrefTest", 0);
        SharedPreferences.Editor edit = pref.edit();

        String name = textName.getText().toString();
        int StNum = 0;

        StNum = Integer.parseInt(textStNum.getText().toString());

        edit.putString("name", name);
        edit.putInt("stNum", StNum);

        edit.commit();
    }
}
