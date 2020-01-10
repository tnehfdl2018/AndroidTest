package com.letscombine.dbtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    // database version 설정
    static final int DATABASE_VERSION = 4;

    public DBHelper(Context context) {
        super(context, "personal", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 첫 실행 시 테이블 생성
        String sql = "create table person (name, phone)";
        Log.i("DBoncreate : ", "실행");

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // database의 버전을 확인 비교하여 다르면 테이블을
        // 삭제하고 재생성
        if (DATABASE_VERSION == newVersion){
            Log.i("DBonUpgrade : ", "실행" + DATABASE_VERSION);
            String sql = "drop table person";
            db.execSQL(sql);
            onCreate(db);
        }

    }
}
