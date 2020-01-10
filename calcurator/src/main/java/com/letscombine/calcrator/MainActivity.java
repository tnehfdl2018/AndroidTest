package com.letscombine.calcrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    final static int REQUEST_CODE_FIRST_NUM = 0;
    final static int REQUEST_CODE_SECOND_NUM = 1;
    final static int REQUEST_CODE_OPERATOR = 2;

    Button firstNum;
    Button secondNum;
    Button operator;
    TextView resultTextView;
    Button resultBtn;

    int firstIntNum;
    Double firstDoubleNum;
    int secondIntNum;
    Double secondDoubleNum;
    String selectOperator;
    int operationResultForInt;
    Double opertionRsultForDouble;
    int firstSaveIntNum;
    int secondSaveIntNum;
    Double firstSaveDoubleNum;
    Double secondSaveDoubleNum;
    String saveOperator;
    String firstReceiveNum;
    String secondReceiveNum;
    String resetFirst;
    String resetSecond;
    String lastResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 각 뷰 생성
        firstNum = findViewById(R.id.first_num);
        secondNum = findViewById(R.id.second_num);
        operator = findViewById(R.id.calc_operator);
        resultTextView = findViewById(R.id.result_num);
        resultBtn = findViewById(R.id.result_btn);
    }

    public void onClick(View v) {
        // 온클릭 메서드를 이용하여 클릭이 된 버튼의 아이디를 가져와
        // switchCatch문으로 구분한다.
        switch (v.getId()) {
            case R.id.first_num:
                Intent intentFirst = new Intent(MainActivity.this, NumberActivity.class);
                intentFirst.putExtra("topText", "좌변의 수 입력");
                if (firstReceiveNum != null) {
                    intentFirst.putExtra("num", firstReceiveNum);
                }
                startActivityForResult(intentFirst, REQUEST_CODE_FIRST_NUM); // 숫자를 받는 액티비티 상단에 설정한 문구와 함께 requestCode를 담아 Number클래스로 보낸다.
                break;
            case R.id.second_num:
                Intent intentSecond = new Intent(MainActivity.this, NumberActivity.class);
                intentSecond.putExtra("topText", "우변의 수 입력");
                if (secondReceiveNum != null) {
                    intentSecond.putExtra("num", secondReceiveNum);
                }
                startActivityForResult(intentSecond, REQUEST_CODE_SECOND_NUM);
                break;
            case R.id.calc_operator:
                Intent intentOperator = new Intent(MainActivity.this, OperatorActivity.class);
                startActivityForResult(intentOperator, REQUEST_CODE_OPERATOR);
                break;
            case R.id.result_btn:
                tempSave(); // 계산하기 버튼 클릭시 operation() 메소드 실행
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 각 액티비타에서 넘어온 인텐트 객체의 requestCode를 어떤 버튼의 것인지 확인한다.
        switch (requestCode) {
            // 맞는 자리에 찾아가면 text를 꺼내 각 버튼에 set한다.
            case REQUEST_CODE_FIRST_NUM:
                if (resultCode == RESULT_OK) {
                    firstIntNum = data.getIntExtra("number", 0);

                    if (firstIntNum == 0) {
                        firstDoubleNum = data.getDoubleExtra("numberDouble", 0);
                        firstNum.setText(firstDoubleNum.toString());
                        resetFirst = firstDoubleNum.toString();
                        // 임시변수에 담겨있는 숫자가 null이 아니고 방금 받아온 숫자와 임시변수 숫자가 다르면 새로 숫자가 바뀐것이기때문에 연산을 실행한다.
                        if (firstReceiveNum != null && firstDoubleNum != Double.parseDouble(firstReceiveNum)) {
                            tempSave();
                        }
                    } else {
                        firstNum.setText(Integer.toString(firstIntNum));
                        resetFirst = Integer.toString(firstIntNum);
                        // 임시변수에 담겨있는 숫자가 null이 아니고 방금 받아온 숫자와 임시변수 숫자가 다르면 새로 숫자가 바뀐것이기때문에 연산을 실행한다.
                        if (firstReceiveNum != null && firstIntNum != Integer.parseInt(firstReceiveNum)) {
                            tempSave();
                        }
                    }
                }
                break;

            case REQUEST_CODE_SECOND_NUM:
                if (resultCode == RESULT_OK) {
                    secondIntNum = data.getIntExtra("number", 0);

                    if (secondIntNum == 0) {
                        secondDoubleNum = data.getDoubleExtra("numberDouble", 0);
                        secondNum.setText(secondDoubleNum.toString());
                        resetSecond = secondDoubleNum.toString();
                        if (secondReceiveNum != null && secondDoubleNum != Double.parseDouble(secondReceiveNum)) {
                            tempSave();
                        }
                    } else {
                        secondNum.setText(Integer.toString(secondIntNum));
                        resetSecond = Integer.toString(secondIntNum);
                        if (secondReceiveNum != null && secondIntNum != Integer.parseInt(secondReceiveNum)) {
                            tempSave();
                        }
                    }
                }
                break;

            case REQUEST_CODE_OPERATOR:
                if (resultCode == RESULT_OK) {
                    selectOperator = data.getStringExtra("operator");
                    operator.setText(selectOperator);

                    if (saveOperator != null && !operator.equals(saveOperator)) {
                        tempSave();
                    }
                }
        }

        // 3가지의 버튼이 채워지면 계산하기 버튼을 활성화 시킨다.
        if (resetFirst != null && resetSecond != null && selectOperator != null) {
            resultBtn.setEnabled(true);
        }
    }

    // 임시변수에 저장하는 메소드
    public void tempSave() {
        // 연산자들이 재 변경되었을때 감지하기 위한 임시 변수에 담는다.
        if (firstIntNum == 0) {
            firstSaveDoubleNum = firstDoubleNum;
            firstReceiveNum = firstSaveDoubleNum.toString();
        } else {
            firstSaveIntNum = firstIntNum;
            firstReceiveNum = Integer.toString(firstSaveIntNum);
        }

        if (secondIntNum == 0) {
            secondSaveDoubleNum = secondDoubleNum;
            secondReceiveNum = secondSaveDoubleNum.toString();
            doubleCalc();
        } else {
            secondSaveIntNum = secondIntNum;
            secondReceiveNum = Integer.toString(secondSaveIntNum);
            if (firstIntNum == 0){
                doubleCalc();
            }else {
                intCalc();
            }
        }
        saveOperator = selectOperator;
    }

    // 계산하는 메소드
    public void intCalc() {
        // 연산자로 넘어온 값이 사측연산중 어느것인지 확인후 계산한다.
        try {
            switch (selectOperator) {
                case "+":
                    operationResultForInt = Integer.parseInt(firstReceiveNum) + Integer.parseInt(secondReceiveNum);
                    break;
                case "-":
                    operationResultForInt = Integer.parseInt(firstReceiveNum) - Integer.parseInt(secondReceiveNum);
                    break;
                case "*":
                    operationResultForInt = Integer.parseInt(firstReceiveNum) * Integer.parseInt(secondReceiveNum);
                    break;
                case "/":
                    operationResultForInt = Integer.parseInt(firstReceiveNum) / Integer.parseInt(secondReceiveNum);
                    break;
            }
            // 계산한 값을 다시 String으로 만든다.
            String lastResult = Integer.toString(operationResultForInt);
            // visible되있는 버튼을 gond으로 만든다.
            resultBtn.setVisibility(View.GONE);
            // gone되있는 버튼을 visible으로 만든다.
            resultTextView.setVisibility(View.VISIBLE);
            // visible이 된 textView에 최종 결과값을 set한다.
            resultTextView.setText(lastResult);
        }catch (ArithmeticException e) {
            Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_LONG);
        }
    }

    public void doubleCalc() {
        // 연산자로 넘어온 값이 사측연산중 어느것인지 확인후 계산한다.
        try {
            switch (selectOperator) {
                case "+":
                    opertionRsultForDouble = Double.parseDouble(firstReceiveNum) + Double.parseDouble(secondReceiveNum);
                    break;
                case "-":
                    opertionRsultForDouble = Double.parseDouble(firstReceiveNum) - Double.parseDouble(secondReceiveNum);
                    break;
                case "*":
                    opertionRsultForDouble = Double.parseDouble(firstReceiveNum) * Double.parseDouble(secondReceiveNum);
                    break;
                case "/":
                    opertionRsultForDouble = Double.parseDouble(firstReceiveNum) / Double.parseDouble(secondReceiveNum);
                    break;
            }
            // 계산한 값을 다시 String으로 만든다.
            lastResult = opertionRsultForDouble.toString();
            // visible되있는 버튼을 gone으로 만든다.
            resultBtn.setVisibility(View.GONE);
            // gone되있는 버튼을 visible으로 만든다.
            resultTextView.setVisibility(View.VISIBLE);
            // visible이 된 textView에 최종 결과값을 set한다.
            resultTextView.setText(lastResult);
        }catch (ArithmeticException e) {
            Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_LONG);
        }
    }
}