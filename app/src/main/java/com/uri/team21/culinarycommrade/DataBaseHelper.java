package com.uri.team21.culinarycommrade;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

// This is a database helper for the recipe database
public class DataBaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "recipes.db3";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public Cursor query(String sql) {

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql, null);

        c.moveToFirst();
        return c;

    }

}
