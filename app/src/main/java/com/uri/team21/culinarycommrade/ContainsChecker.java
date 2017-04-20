package com.uri.team21.culinarycommrade;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by douglas on 4/19/17.
 */

public class ContainsChecker {

    DataAccessor db;
    ArrayList<String> inv;
    String[][] list;

    ContainsChecker(Context context) {
        db = new DataAccessor(context);
        inv = db.getInventory();
        list = db.getShoppingList();
    }

    public boolean inInv(String item) {
        return inv.contains(item);
    }

    public boolean inList(String item, String recipe) {
        for(String[] s : list) {
            if (s[0].equals(item) && s[1].equals(recipe)){
                return true;
            }
        }
        return false;
    }
}
