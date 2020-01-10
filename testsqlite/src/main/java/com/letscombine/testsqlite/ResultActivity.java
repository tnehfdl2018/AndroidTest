package com.letscombine.testsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView textViewTitle, textViewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewTitle = findViewById(R.id.textTitle);
        textViewContent = findViewById(R.id.textContent);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select title, content from testDB", null);
        while (cursor.moveToNext()){
            textViewTitle.setText(cursor.getString(0));
            textViewContent.setText(cursor.getString(1));
        }

    }
}
