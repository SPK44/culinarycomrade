package com.uri.team21.culinarycommrade;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.*;

public class DataAccessor {

    private DataBaseHelper rDbHelper;
    private DataBaseWriter lDbHelper;
    private DataBaseWriter iDbHelper;
    private static ArrayList<String> unique;


    DataAccessor(Context context) {
        rDbHelper = new DataBaseHelper(context);
        iDbHelper = new DataBaseWriter(context, "Inventory");
        lDbHelper = new DataBaseWriter(context, "List");
    }


    // gets list of all recipes
    public ArrayList<String> getRecipes() {


        // SQL select statement
        ArrayList<String> data = new ArrayList<>();
        Cursor cursor = rDbHelper.query("SELECT _description FROM esha");
        // Adds to an array list to return
        while (!cursor.isAfterLast()) {
            data.add(cursor.getString(cursor.getColumnIndex("_description")));
            cursor.moveToNext();
        }
        cursor.close();

        return data;

    }

    // Grabs unique ingredients
    public ArrayList<String> getAllIngredients() {

        if (unique == null) { // caches the list until app is killed
            unique = new ArrayList<>();
            Cursor cursor = rDbHelper.query("SELECT Ingredients FROM ingred"); // grabs from SQL
            while (!cursor.isAfterLast()) {
                unique.add(cursor.getString(cursor.getColumnIndex("Ingredients")));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return unique;

    }

    // Queries the inventory
    public ArrayList<String> getInventory() {

        ArrayList<String> data = new ArrayList<>();
        Cursor cursor = iDbHelper.query("SELECT Ingredient FROM Inventory");
        while (!cursor.isAfterLast()) { // same as insert
            data.add(cursor.getString(cursor.getColumnIndex("Ingredient")));
            cursor.moveToNext();
        }
        cursor.close();

        return data;

    }

    // Same as inventory, but a 2D array instead
    public String[][] getShoppingList()
    {
        Cursor cursor = lDbHelper.query("SELECT COUNT(Ingredient) AS Count FROM List");
        int length = cursor.getInt(cursor.getColumnIndex("Count"));
        String[][] data = new String[length][2];
        cursor.close();
        Cursor cursor2 = lDbHelper.query("SELECT Ingredient,Recipe FROM List");
        int i = 0;
        while (!cursor2.isAfterLast()) {
            data[i][0] = cursor2.getString(cursor2.getColumnIndex("Ingredient"));
            data[i][1] = cursor2.getString(cursor2.getColumnIndex("Recipe"));
            cursor2.moveToNext();
            i++;
        }
        cursor2.close();
        return data;

    }

    //Get Ingredients for a specific Recipe
    public ArrayList<String> getIngredients(String Name)
    {
        String query = "SELECT RecipeItem_0_ItemName";
        for (int i = 1; i < 25; i++) {
            query += ", RecipeItem_" + i + "_ItemName";
        }
        query += " FROM esha ";
        Name = Name.replaceAll("'", "''");
        query += "WHERE _description = '" + Name + "';";
        ArrayList<String> data = new ArrayList<>();
        Cursor cursor = rDbHelper.query(query);
        if (cursor.getCount() > 0) {
            for (int i = 0; i < 25; i++) {
                String someData = cursor.getString(cursor.getColumnIndex("RecipeItem_" + i + "_ItemName"));
                if (someData == null || someData.length() == 0) break;
                data.add(someData);
                //Log.d("DATA", "getIngredients: " + someData.toCharArray()[0]);
            }
        }
        cursor.close();
        return data;
    }

    //Get all ingredients available in the Database
    public ArrayList<String> getAllIngredients_dynamic()
    {
        if (unique == null) {
            ArrayList<String> allIngredients = new ArrayList<String>();
            ArrayList<String> recipes = getRecipes();
            int size = recipes.size();
            String[] aRecipes = recipes.toArray(new String[size]);
            String[] aAllIngredients;
            for (int i = 0; i < size; i++) {
                ArrayList<String> someIngredients = getIngredients(aRecipes[i]);
                String[] aSomeIngredients = someIngredients.toArray(new String[someIngredients.size()]);
                aAllIngredients = allIngredients.toArray(new String[allIngredients.size()]);
                allIngredients = concat(aAllIngredients, aSomeIngredients);
            }
            aAllIngredients = allIngredients.toArray(new String[allIngredients.size()]);
            allIngredients = uniqueIngredients(aAllIngredients);
            unique = new ArrayList<String>(allIngredients);
            return allIngredients;
        }
        return unique;
    }

    //Delete repeated ingredients in the Array
    public ArrayList<String> uniqueIngredients(String[] arr)
    {
        ArrayList<String> arrList = new ArrayList<String>();
        int count= 0;
        //List<String> arrList = Arrays.asList(arr);
        ArrayList<String> lenList = new ArrayList<String>();
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i].equals(arr[j])){
                    count+=1;
                }
            }
            if(count<1){
                arrList.add(arr[i]);
            }
            count=0;
        }
        return arrList;
    }

    //Concatenate two arrays into one
    public ArrayList<String> concat(String[] a, String[] b) {
        int aLen = a.length;
        int bLen = b.length;
        String[] c = new String[aLen + bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        ArrayList<String> d = new ArrayList<String>(Arrays.asList(c));
        return d;
    }

    //Get the Yield/ How many the recipe feeds
    public int getYield(String Name) {
        Name = Name.replaceAll("'", "''");
        String query = "SELECT Yield FROM esha WHERE _description='" + Name + "';";
        Cursor cursor = rDbHelper.query(query);
        String someData = cursor.getString(0);
        double yield = Double.parseDouble(someData);
        return (int)yield;
    }

    //Get directions on how to prepare the a specific recipe
    public String getDirections(String Name) {
        Name = Name.replaceAll("'", "''");
        String query = "SELECT memo FROM esha WHERE _description='" + Name + "';";
        Cursor cursor = rDbHelper.query(query);
        String Directions = cursor.getString(0);
        return Directions;
    }

    // Checks if an item is in the Database or not, then adds or removes. HIGH overhead
    public boolean toggleInventory(String Ingredient) {
        Ingredient = Ingredient.replaceAll("'", "''");
        Cursor cursor = iDbHelper.query("SELECT Ingredient FROM Inventory WHERE Ingredient='" + Ingredient+ "';"); // SQL select
        if(cursor.getCount() > 0) { //Counts items picked up
            removeFromInventory(Ingredient);
            cursor.close();
            return false;
        }
        addToInventory(Ingredient);
        cursor.close();
        return true;
    }

    // Converse of toggleInventory
    public boolean toggleShoppingList(String Ingredient, String Recipe) {
        Ingredient = Ingredient.replaceAll("'", "''");
        Recipe = Recipe.replaceAll("'", "''");
        Cursor cursor = lDbHelper.query("SELECT Ingredient FROM List WHERE Ingredient='" + Ingredient + "' AND Recipe='" + Recipe + "';");
        if(cursor.getCount() > 0) {
            removeFromShoppingList(Ingredient, Recipe);
            cursor.close();
            return false;
        }
        addToShoppingList(Ingredient, Recipe);
        cursor.close();
        return true;
    }


    private void addToInventory(String Ingredient) {
        iDbHelper.query("INSERT INTO Inventory (Ingredient) VALUES ('" + Ingredient + "');");
    }

    private void addToShoppingList(String Ingredient, String Recipe) {
        lDbHelper.query("INSERT INTO List (Ingredient, Recipe) VALUES ('" + Ingredient + "','" + Recipe + "');");
    }

    private void removeFromInventory(String Ingredient) {
        iDbHelper.query("DELETE FROM Inventory WHERE Ingredient='" + Ingredient + "';");
    }

    private void removeFromShoppingList(String Ingredient, String Recipe) {
        lDbHelper.query("DELETE FROM List WHERE Ingredient='" + Ingredient + "' AND Recipe='" + Recipe + "';");
    }

    public void deleteAllInventory() {
        iDbHelper.query("DELETE FROM Inventory");
    }

    public void deleteAllList(String Recipe) {
        Recipe = Recipe.replaceAll("'", "''");
        lDbHelper.query("DELETE FROM List WHERE Recipe='" + Recipe + "';");
    }

}
