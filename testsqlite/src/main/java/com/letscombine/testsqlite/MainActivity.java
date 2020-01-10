package com.letscombine.testsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText title, content;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.editTitle);
        content = findViewById(R.id.editContent);
        add = findViewById(R.id.btnSave);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String takeTitle = title.getText().toString();
                String takeContent = content.getText().toString();

                DBHelper helper = new DBHelper(MainActivity.this);
                SQLiteDatabase db = helper.getWritableDatabase();
                db.execSQL("insert into testDB (title, content) values(?,?)", new String[]{takeTitle, takeContent});
                db.close();
//                ContentValues values = new ContentValues();
//                values.put("title", takeTitle);
//                values.put("content", takeContent);
//
//                db.insert("testDB", null, values);

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);

            }
        });
    }
}
