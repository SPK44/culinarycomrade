package com.uri.team21.culinarycommrade;

import android.database.Cursor;
import android.content.Context;
import java.util.*;



public class DataAccessor {

    private DataBaseHelper rDbHelper;
    private DataBaseWriter lDbHelper;
    private DataBaseWriter iDbHelper;


    DataAccessor(Context context) {
        rDbHelper = new DataBaseHelper(context);
        iDbHelper = new DataBaseWriter(context, "Inventory");
        lDbHelper = new DataBaseWriter(context, "List");
    }

    public ArrayList<String> getRecipes() {

        ArrayList<String> data = new ArrayList<>();
        Cursor cursor = rDbHelper.query("SELECT _description FROM esha");
        while(!cursor.isAfterLast()) {
            data.add(cursor.getString(cursor.getColumnIndex("_description")));
            cursor.moveToNext();
        }
        cursor.close();

        return data;

    }

    public ArrayList<String> getInventory() {

        ArrayList<String> data = new ArrayList<>();
        Cursor cursor = iDbHelper.query("SELECT Ingredient FROM Inventory");
        while(!cursor.isAfterLast()) {
            data.add(cursor.getString(cursor.getColumnIndex("Ingredient")));
            cursor.moveToNext();
        }
        cursor.close();

        return data;

    }

    public String[][] getShoppingList() {

        Cursor cursor = lDbHelper.query("SELECT COUNT(Ingredient) AS Count FROM List");
        int length = cursor.getInt(cursor.getColumnIndex("Count"));
        String[][] data = new String[length][2];
        cursor.close();
        Cursor cursor2 = lDbHelper.query("SELECT Ingredient,Recipe FROM List");
        int i = 0;
        while(!cursor.isAfterLast()) {
            data[i][0] = cursor2.getString(cursor.getColumnIndex("Ingredient"));
            data[i][1] = cursor2.getString(cursor.getColumnIndex("Recipe"));
            cursor.moveToNext();
            i++;
        }
        cursor2.close();
        return data;

    }

    public void addToInventory(String Ingredient) {
        iDbHelper.query("INSERT INTO Inventory (Ingredient) VALUES ('" + Ingredient + "');");
    }

    public void addToShoppingList(String Ingredient, String Recipe) {
        lDbHelper.query("INSERT INTO List (Ingredient, Recipe) VALUES ('" + Ingredient + "','" + Recipe +"');");
    }

    public void removeFromInventory(String Ingredient) {
        iDbHelper.query("DELETE FROM Inventory WHERE Ingredient='" + Ingredient + "';");
    }

    public void removeFromShoppingList(String Ingredient, String Recipe) {
        lDbHelper.query("DELETE FROM List WHERE Ingredient='" + Ingredient + "' AND Recipe='" + Recipe +"';");
    }
    public void deleteAllInventory() {
        iDbHelper.query("DELETE FROM Inventory");
    }
    public void deleteAllList(String Recipe) {
        lDbHelper.query("DELETE FROM List WHERE Recipe='" + Recipe + "';");
    }

}
