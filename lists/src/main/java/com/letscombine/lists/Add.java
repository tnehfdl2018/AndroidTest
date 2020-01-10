package com.letscombine.lists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Add extends AppCompatActivity {
    Button addBtn;
    AutoCompleteTextView addName;
    AutoCompleteTextView addContent;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addBtn = findViewById(R.id.addBtn);
        addName = findViewById(R.id.addName);
        addContent = findViewById(R.id.addContent);

        // 실질적 추가 메소드
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adddName = addName.getText().toString();
                String adddContent = addContent.getText().toString();

                DBHelper dbHelper = new DBHelper(Add.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                db.execSQL("insert into test_table (name, content) values (?, ?)", new String[]{adddName, adddContent});
                db.close();

                Intent intent = new Intent(Add.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onPause() {
        super.onPause();
        Log.i("Add Page : ", "onPause()");

        sharedPreferences = getSharedPreferences("PrefTest", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String name = addName.getText().toString();
        String content = addContent.getText().toString();

        editor.putString("Name", name);
        editor.putString("content", content);
        editor.commit();
    }
}
