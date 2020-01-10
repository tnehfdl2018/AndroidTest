package com.letscombine.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActEdit extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_edit);

        editText = findViewById(R.id.strEdit);

        Intent intent = getIntent();
        String text = intent.getStringExtra("TextIn");
        if (text != null){
            editText.setText(text);
        }

    }

    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.btnOk :
                Intent intent = new Intent();
                intent.putExtra("TextOut", editText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btnCancel :
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
