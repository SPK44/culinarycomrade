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
        for(String i : recipes) {
            ArrayList<String> ingredients = db.getIngredients(i);
            int count = 0;
            for(String j: ingredients) {
                for(String l : inventory)
                    if (j.equals(l)) {
                        count++;
                    }
            }
            weightedList.append(i, ((double)count/ingredients.size()));
        }

        weightedList.sort();

        return weightedList;
    }
}

