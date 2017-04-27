package com.uri.team21.culinarycommrade;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

// This is a database helper for the recipe database
public class DataBaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "recipes.db3";
    private static final int DATABASE_VERSION = 2;
    SQLiteDatabase db;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.setForcedUpgrade(); // Always uses the lastest DB, regardless of updating
        db = getReadableDatabase();


    }

    public Cursor query(String sql) {


        Cursor c = db.rawQuery(sql, null);

        c.moveToFirst(); // Goes to first item
        return c;

    }

    // Closes database before heap deallocation
    protected void finalize() {

        db.close();

    }

}
