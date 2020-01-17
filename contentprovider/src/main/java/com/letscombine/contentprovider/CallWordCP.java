package com.letscombine.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CallWordCP extends AppCompatActivity {
    static final String WORDURI = "content://com.letscombine.contentprovider.MainActivity/word";
    EditText mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_word_cp);

        mText = findViewById(R.id.edit_text);
    }

    public void mOnClick(View v) {
        ContentResolver cr = getContentResolver();
        switch (v.getId()) {
            case R.id.read_all:
                Cursor cursor = cr.query(Uri.parse(WORDURI), null, null, null, null);

                String result = "";
                while (cursor.moveToNext()) {
                    String eng = cursor.getString(0);
                    String han = cursor.getString(1);
                    result += (eng + " = " + han + "\n");
                }
                if (result.length() == 0) {
                    mText.setText("Empty set");
                }else  {
                    mText.setText(result);
                }
                cursor.close();
                break;

            case R.id.read_one:
                Cursor cursorForReadOne = cr.query(Uri.parse(WORDURI + "/boy"), null, null, null, null);
                String resultForReadOne = "";
                if (cursorForReadOne.moveToNext()) {
                    String eng = cursorForReadOne.getString(0);
                    String han = cursorForReadOne.getString(1);
                    resultForReadOne += (eng + " = " + han + "\n");
                }

                if (resultForReadOne.length() == 0) {
                    mText.setText("Empty set");
                }else {
                    mText.setText(resultForReadOne);
                }
                cursorForReadOne.close();
                break;

            case R.id.insert:
                ContentValues row = new ContentValues();
                row.put("eng", "school");
                row.put("han", "학교");

                cr.insert(Uri.parse(WORDURI), row);
                mText.setText("Insert Success");
                break;

            case R.id.delete:
                cr.delete(Uri.parse(WORDURI), null, null);
                mText.setText("Delete Success");
                break;

            case R.id.update:
                ContentValues rowForUpdate = new ContentValues();
                rowForUpdate.put("han", "핵교");
                cr.update(Uri.parse(WORDURI + "/school"), rowForUpdate, null, null);
                mText.setText("Update Success");
                break;
        }
    }
}
