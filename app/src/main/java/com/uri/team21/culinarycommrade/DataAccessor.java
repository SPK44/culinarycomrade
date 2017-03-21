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
        while(!cursor2.isAfterLast()) {
            data[i][0] = cursor2.getString(cursor2.getColumnIndex("Ingredient"));
            data[i][1] = cursor2.getString(cursor2.getColumnIndex("Recipe"));
            cursor2.moveToNext();
            i++;
        }
        cursor2.close();
        return data;

    }

     public ArrayList<String> getIngredients(String Name) { 
        String query = "SELECT RecipeItem/0/_ItemName";
             for( int i=1; i<25; i++)     {         query+=", RecipeItem/"+i+"/_ItemName";
             }     query+= "FROM esha";
             query+= "WHERE _description ="+ Name;
             ArrayList<String> data = new ArrayList<>();
             Cursor cursor = rDbHelper.query(query);
              for( int i=0; i<25; i++)     {
               String someData=  cursor.getString(cursor.getColumnIndex("RecipeItem/"+i+"/_ItemName"));
                 if (someData == null) {
                     break; 
            } 
                else  { 
                data.add(someData); 
            } 
        }     cursor.close();
              return data;  
    }

      public ArrayList<String> getAllIngredients() { 
        ArrayList<String> allIngredients =new ArrayList<String>();
             ArrayList<String> recipes = getRecipes();
             int size = recipes.size(); 
        for (int i = 0; i<size; i++)     {
                 ArrayList<String> someIngredients = getIngredients(recipes[i]); 
            allIngredients = concat(allIngredients,someIngredients);
                 } 
        return allIngredients;
         }

          public String[] concat(String[] a, String[] b) { 
        int aLen = a.length;
             int bLen = b.length;
             String[] c= new String[aLen+bLen];
             System.arraycopy(a, 0, c, 0, aLen); 
            System.arraycopy(b, 0, c, aLen, bLen); 
        return c; 
    }  

    public int getYield(String Name){
          String query ="SELECT Yeild/measure/_quantity From esha WHERE _description="+Name; 
        Cursor cursor = rDbHelper.query(query);
             String someData=  cursor.getString(cursor.getColumnIndex("Yeild/measure/_quantity")); 
        int yield = Integer.parseInt(someData);
             return yield;
         }

          public String getDescription(String Name){  
        String query ="SELECT memo/__cdata From esha WHERE _description="+Name; 
        Cursor cursor = rDbHelper.query(query);
             String Directions=  cursor.getString(cursor.getColumnIndex("memo/__cdata"));
             return Directions;
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
