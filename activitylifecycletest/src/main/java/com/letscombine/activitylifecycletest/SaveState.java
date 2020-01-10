package com.letscombine.activitylifecycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class SaveState extends AppCompatActivity {
    private MyView vw;
    int x;
    int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null){
            x = 50;

        }else {
            x = savedInstanceState.getInt("x");
        }
        SharedPreferences sharedPreferences = getSharedPreferences("SaveState", 0);
        y = sharedPreferences.getInt("y", 50);
        vw = new MyView(this);
        vw.setFocusable(true);
        vw.setFocusableInTouchMode(true);
        setContentView(vw);
    }

    protected void onPause(){
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("SaveState", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("y", y);
        editor.commit();
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("x", x);
    }

    protected class MyView extends View {
        public MyView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            Paint p = new Paint();
            p.setColor(Color.GREEN);
            canvas.drawCircle(x, y, 16, p);
        }

        public boolean onKeyDown(int keyCode, KeyEvent event) {
            super.onKeyDown(keyCode, event);
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_DPAD_LEFT:
                        x -= 15;
                        invalidate();
                        return true;

                    case KeyEvent.KEYCODE_DPAD_RIGHT:
                        x += 15;
                        invalidate();
                        return true;

                    case KeyEvent.KEYCODE_DPAD_UP:
                        y -= 15;
                        invalidate();
                        return true;

                    case KeyEvent.KEYCODE_DPAD_DOWN:
                        y += 15;
                        invalidate();
                        return true;
                }
            }
            return false;
        }
        public boolean onTouchEvent(MotionEvent event){
            super.onTouchEvent(event);
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                x += 15;
                y += 15;
                invalidate();
                return true;
            }
            return false;
        }
    }
}
