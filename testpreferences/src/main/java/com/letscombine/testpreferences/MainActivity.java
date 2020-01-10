package com.letscombine.testpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textName;
    TextView textStNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = findViewById(R.id.name);
        textStNum = findViewById(R.id.stnum);

        SharedPreferences sharedPreferences = getSharedPreferences("PrefTest", 0);
        String name = sharedPreferences.getString("Name", "이름없음");
        textName.setText(name);

        int stNum = sharedPreferences.getInt("StNum", 20101234);
        textStNum.setText("" + stNum);
    }

    public void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("PrefTest", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String name = textName.getText().toString();
        int stNum = 0;
        stNum = Integer.parseInt(textStNum.getText().toString());

        editor.putString("Name", name);
        editor.putInt("stNum", stNum);
        editor.commit();
    }
}
