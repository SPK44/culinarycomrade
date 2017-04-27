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

    // checks if an item is in the cached inventory
    public boolean inInv(String item) {
        return inv.contains(item);
    }

    // checks if an items is in the cached shoppping list
    public boolean inList(String item, String recipe) {

        // brute force contains
        for(String[] s : list) {
            if (s[0].equals(item) && s[1].equals(recipe)){
                return true;
            }
        }
        return false;
    }

    // updates cached lists
    public void update() {
        inv = db.getInventory();
        list = db.getShoppingList();
    }
}
