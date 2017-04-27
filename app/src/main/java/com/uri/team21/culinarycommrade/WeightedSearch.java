package com.uri.team21.culinarycommrade;

import android.content.Context;

import java.util.ArrayList;

public class WeightedSearch {

    private DataAccessor db;
    private ArrayList<String> recipes;
    private ArrayList<String> inventory;

    public WeightedSearch(Context context) {
        db = new DataAccessor(context);
    }

    public WeightedMessenger getSortedList() {
        recipes = db.getRecipes();
        inventory = db.getInventory();

        WeightedMessenger weightedList = new WeightedMessenger(recipes.size());

        int k = 0;
        for(String i : recipes) { // for each recipe
            ArrayList<String> ingredients = db.getIngredients(i); // get its ingredients
            int count = 0;
            for(String j: ingredients) { // for each ingredient
                if (inventory.contains(j)) { // if the ingredient is in the inventory
                        count++; // add one to the count
                }
            }
            weightedList.append(i, ((double)count/ingredients.size())); // append the wieght to the messanger
        }

        weightedList.sort(); // sort the messenger

        return weightedList; // return the messenger
    }
}

