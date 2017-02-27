package com.uri.team21.culinarycommrade;

import android.database.Cursor;
import android.content.Context;
import java.util.*;


/**
 * Created by douglas on 2/23/17.
 */

public class DataAccessor {

    private final Context mContext;
    private DataBaseHelper mDbHelper;


    public DataAccessor(Context context) {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(context);
    }

    public ArrayList<String> getRecipies() {

        ArrayList<String> data = new ArrayList<>();
        Cursor cursor = mDbHelper.query("SELECT _description FROM vegi");
        while(!cursor.isAfterLast()) {
            data.add(cursor.getString(cursor.getColumnIndex("_description")));
            cursor.moveToNext();
        }
        cursor.close();

        return data;

    }

}
