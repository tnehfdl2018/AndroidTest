package com.letscombine.lists;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "dataSQL", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dataSQL = "create table test_table (name, content)";

        db.execSQL(dataSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == db.getVersion()){
            db.execSQL("drop table test_table");
            onCreate(db);
        }

    }
}
