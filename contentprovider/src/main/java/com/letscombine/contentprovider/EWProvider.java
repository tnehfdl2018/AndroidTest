package com.letscombine.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EWProvider extends ContentProvider {
    static final Uri CONTENT_URI = Uri.parse("content://com.letscombine.contentprovider.MainActivity/word");
    static final int ALLWORD = 1;
    static final int ONEWORD = 2;

    static final UriMatcher Matcher;

    static {
        Matcher = new UriMatcher(UriMatcher.NO_MATCH);
        Matcher.addURI("com.letscombine.contentprovider", "word", ALLWORD);
        Matcher.addURI("com.letscombine.contentprovider", "word/*", ONEWORD);
    }

    SQLiteDatabase mDB;

    @Override
    public boolean onCreate() {
        WordDBHelper helper = new WordDBHelper(getContext());
        mDB = helper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String sql;

        sql = "SELECT eng, han FROM dic";

        if (Matcher.match(uri) == ONEWORD) {
            sql += " where eng = '" + uri.getPathSegments().get(1) + "'";
        }
        Cursor cursor = mDB.rawQuery(sql, null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        if (Matcher.match(uri) == ALLWORD) {
            return "com.letscombine.contentprovider.cursor.item/word";
        }
        if (Matcher.match(uri) == ONEWORD) {
            return "com.letscombine.contentprovider.cursor.dir/word";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long row = mDB.insert("dic", null, values);
        if (row > 0) {
            Uri notiuri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(notiuri, null);
            return notiuri;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;

        switch (Matcher.match(uri)) {
            case ALLWORD:
                count = mDB.delete("dic", selection, selectionArgs);
                break;
            case ONEWORD:
                String where;
                where = "eng = '" + uri.getPathSegments().get(1) + "'";
                if (TextUtils.isEmpty(selection) == false) {
                    where += "AND" + selection;
                }
                count = mDB.delete("dic", where, selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;

        /*
        String sql;
        sql = "DELETE FROM dic";

        if (Matcher.match(uri) == ONEWORD) {
            sql += "where eng = '" + uri.getPathSegments().get(1) + "'";
        }
        mDB.execSQL(sql);
        return 1;
        //*/

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        int count = 0;

        switch (Matcher.match(uri)) {
            case ALLWORD:
                count = mDB.update("dic", values, selection, selectionArgs);
                break;
            case ONEWORD:
                String where;
                where = "eng = '" + uri.getPathSegments().get(1) + "'";
                if (TextUtils.isEmpty(selection) == false) {
                    where += "AND" + selection;
                }
                count = mDB.update("dic", values, where, selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}

