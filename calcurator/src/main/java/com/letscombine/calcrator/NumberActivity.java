package com.letscombine.calcrator;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NumberActivity extends AppCompatActivity {

    TextView topText;
    EditText insertNum;
    Button insertOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        topText = findViewById(R.id.topText);
        insertNum = findViewById(R.id.insert_num);
        insertOk = findViewById(R.id.insert_ok);

        // mainActivity에서 넘어온 인텐트에 들어있는 String값을 꺼내 상위 TextView에 설정한다.
        Intent intent = getIntent();
        String text = intent.getStringExtra("topText");
        if (text != null) {
            topText.setText(text);
        }

        // 피연산자를 결정후 다시 재선택하면 선택했던 숫자가 다시 기록된다.
        String number = intent.getStringExtra("num");
        insertNum.setText(number);

       insertNum.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               // 데이터 입력전
               insertOk.setEnabled(true);
           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               // 데이터 입력중
               insertOk.setEnabled(true);
           }

           @Override
           public void afterTextChanged(Editable s) {
               // 데이터 입력후
               insertOk.setEnabled(true);
           }
       });
    }

    public void returnNum(View v) {
        switch (v.getId()) {
            case R.id.insert_ok :
                // editView에 있는 값을 가져온다.
                String number = insertNum.getText().toString();
                isInt(number);
                break;
            case R.id.insert_cancel :
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

    public void isInt(String number){
        try {
            int chkInt = Integer.parseInt(number);
            // string값과 RESULT_OK를 담아 mainActivity로 돌려준다.
            Intent intent = new Intent(NumberActivity.this, MainActivity.class);
            intent.putExtra("number", chkInt);
            setResult(RESULT_OK, intent);
            finish();
        }catch (NumberFormatException e) {
            Double numberDouble = Double.parseDouble(number);
            // string값과 RESULT_OK를 담아 mainActivity로 돌려준다.
            Intent intent = new Intent(NumberActivity.this, MainActivity.class);
            intent.putExtra("numberDouble", numberDouble);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
