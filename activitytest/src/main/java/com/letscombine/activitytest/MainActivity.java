package com.letscombine.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    final static int ACT_EDIT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnEdit:
                Intent intent = new Intent(this, ActEdit.class);
                intent.putExtra("TextIn", textView.getText().toString());
                startActivityForResult(intent, ACT_EDIT);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACT_EDIT:
                if (resultCode == RESULT_OK) {
                    textView.setText(data.getStringExtra("TextOut"));
                }
                break;
        }
    }
}
