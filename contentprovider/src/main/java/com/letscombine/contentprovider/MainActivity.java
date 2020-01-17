package com.letscombine.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    WordDBHelper mHelper;
    EditText mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new WordDBHelper(this);
        mText = findViewById(R.id.edit_text);
    }

    public void mOnClick(View v) {
        SQLiteDatabase db;
        ContentValues row;

        switch (v.getId()) {
            case R.id.insert :
                db = mHelper.getWritableDatabase();
                row = new ContentValues();
                row.put("eng", "boy");
                row.put("han", "머스마");
                db.insert("dic", null, row);

                db.execSQL("Insert into dic values (null, 'girl', '가시나');");
                mHelper.close();
                mText.setText("insert Success");
                break;
            case R.id.delete :
                db = mHelper.getWritableDatabase();
                db.delete("dic", null, null);
                mHelper.close();
                mText.setText("Delete Success");
                break;
            case R.id.update :
                db = mHelper.getWritableDatabase();
                row = new ContentValues();
                row.put("han", "소년");
                db.update("dic", row, "eng = 'boy'", null);
                mHelper.close();
                mText.setText("Update Success");
                break;
            case R.id.select :
                db = mHelper.getReadableDatabase();
                Cursor cursor;
                cursor = db.rawQuery("SELECT eng, han FROM dic", null);

                String result = "";
                while (cursor.moveToNext()) {
                    String eng = cursor.getString(0);
                    String han = cursor.getString(1);
                    result += (eng + " = " + han + "\n");
                }

                if (result.length() == 0) {
                    mText.setText("Empty Set");
                }else {
                    mText.setText(result);
                }
                cursor.close();
                mHelper.close();
                break;
        }
    }
}

class WordDBHelper extends SQLiteOpenHelper {
    public WordDBHelper(Context context) {
        super(context, "EngWord.db", null, 1);
        Log.i("우~와~우ffffffffffffff와~" , "ddddddd");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE dic ( _id INTEGER PRIMARY KEY AUTOINCREMENT, eng TEXT, han TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS dic");
        onCreate(db);
    }
}
