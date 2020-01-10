package com.letscombine.calcrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OperatorActivity extends AppCompatActivity {

    RadioGroup operator_radio;
    int radioId;
    RadioButton choiceRadioButton;
    Button operationOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);

        operationOk = findViewById(R.id.operator_ok);
        operator_radio = findViewById(R.id.radioGroup);

        // 연산자가 선택되면 확인버튼을 활성화 시킨다.
        operator_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != 0){  // checkId가 0이 아니라는 것은 어떤 라디오 버튼이든 선택이 되었다는 의미
                    operationOk.setEnabled(true);
                }
            }
        });
    }

    public void returnOperator(View v) {
        switch (v.getId()) {
            case R.id.operator_ok :
                // 선택된 라디오 버튼의 id를 얻는다.
                radioId = operator_radio.getCheckedRadioButtonId();
                // 얻은 id를 가지고 라디오 버튼을 생성한다.
                choiceRadioButton = findViewById(radioId);
                // 라디오버튼 값을 가져온다.
                String selectOperator = choiceRadioButton.getText().toString();
                // 인텐트를 생성하고 연산자를 담은 인텐트와 RESULT_OK를 전송
                Intent intent = new Intent(OperatorActivity.this, MainActivity.class);
                intent.putExtra("operator", selectOperator);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.operator_cancel :
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
