package com.uri.team21.culinarycommrade;


import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// This is a class for writing to writable databases such as the Ingredient and Shopping List
public class DataBaseWriter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private String DATABASE_NAME;


    // Be sure that you keep a consistent DBName
    DataBaseWriter(Context context, String DATABASE_NAME) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.DATABASE_NAME = DATABASE_NAME;
    }

    // Just in case the Accessor forgets. Never used though
    public String getDataBaseName() {
        return DATABASE_NAME;
    }

    public Cursor query(String sql) {

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(sql, null);

        c.moveToFirst();
        db.close();
        return c;

    }

    // For the init of the app
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DATABASE_NAME + " (" +
                "Ingredient TEXT";
        if (DATABASE_NAME == "List") {
            query += ", Recipe TEXT";
        }
        query += ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldNum, int newNum) {
        //Will not upgrade
    }
}
