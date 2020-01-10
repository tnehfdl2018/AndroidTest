package com.letscombine.eventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View vw = new View(this);
//        vw.setOnTouchListener(TouchLitener);

        //익명 내부클래스의 임시객체 사용
        vw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Toast.makeText(MainActivity.this, "익명 내부 클래스의 임시 객체 사용", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });
        setContentView(vw);
    }


        // 익명 내부 클래스
//    View.OnTouchListener TouchLitener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_DOWN){
//                Toast.makeText(MainActivity.this, "익명 내부 클래스", Toast.LENGTH_LONG).show();
//                return true;
//            }
//            return false;
//        }
//    };

//    class TouchLitenerClass implements View.OnTouchListener {
//        public boolean onTouch(View v, MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                Toast.makeText(MainActivity.this, "리스너 인터페이스", Toast.LENGTH_LONG).show();
//                return true;
//            }
//            return false;
//        }
//    }
//    TouchLitenerClass touchLitener = new TouchLitenerClass();

//    class MyView extends View{
//        public MyView(Context context){
//            super(context);
//        }

//        public boolean onTouchEvent(MotionEvent event) {
//            super.onTouchEvent(event);
//            if (event.getAction() == MotionEvent.ACTION_DOWN){
//                Toast.makeText(MainActivity.this, "콜백 메서드 재정의", Toast.LENGTH_LONG).show();
//                return true;
//            }
//            return false;
//        }
//    }


}