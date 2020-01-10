package com.letscombine.showdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    int a = 3;
    int b = 4;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mOnClick(View v) {
        new AlertDialog.Builder(this)
                .setTitle("질문")
                .setMessage("어떤 연산을 하시겠습니까?")
                .setPositiveButton("덧셈", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result = a + b;
                    }
                })
                .setNegativeButton("곰셈", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result = 3 * 4;
                    }
                })
                .show();

        TextView text = findViewById(R.id.text);
        text.setText("연산결과 : " + result);
        Toast.makeText(getApplicationContext(), "연산을 완료했습니다.", Toast.LENGTH_LONG).show();
    }
}

