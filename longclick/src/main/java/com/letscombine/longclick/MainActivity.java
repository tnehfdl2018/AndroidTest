package com.letscombine.longclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int mCount = 0;
    TextView textCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCount = findViewById(R.id.count);

        findViewById(R.id.decrease).setOnLongClickListener(mLongClickListener);
        findViewById(R.id.increase).setOnLongClickListener(mLongClickListener);
    }

    public void mOnClick (View v) {
        switch (v.getId()) {
            case R.id.decrease :
                mCount--;
                textCount.setText("" + mCount);
                break;
            case R.id.increase :
                mCount++;
                textCount.setText("" + mCount);
                break;
        }
    }

    View.OnLongClickListener mLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case R.id.decrease :
                    mCount = 0;
                    textCount.setText("" + mCount);
                    return true;
                case R.id.increase :
                    mCount = 100;
                    textCount.setText("" + mCount);
                    return true;
            }
            return false;
        }
    };
}
