package com.uri.team21.culinarycommrade;

import android.widget.LinearLayout;
import android.content.Context;
import android.widget.TextView;
import android.widget.ImageButton;

/**
 * Created by Shaps on 2/26/2017.
 */

public class ingredientView extends LinearLayout {

    private TextView ingredientName;
    private TextView recipeName;
    private ImageButton inventoryButton;
    private ImageButton shoppingButton;
    private String id;
    private String recipe;


    public ingredientView(Context context){
        super(context);
        ingredientName = new TextView(context);
        inventoryButton = new ImageButton(context);
        shoppingButton = new ImageButton(context);

        addView(ingredientName);
        addView(inventoryButton);
        addView(shoppingButton);

        id = "unset";
        setName(id);

        recipe = "";
    }

    public ingredientView(Context context, String name){
        super(context);
        ingredientName = new TextView(context);
        recipeName = new TextView(context);
        inventoryButton = new ImageButton(context);
        shoppingButton = new ImageButton(context);

        addView(ingredientName);
        addView(recipeName);
        addView(inventoryButton);
        addView(shoppingButton);

        setName(name);
        setRecipe("");
    }

    public ingredientView(Context context, String name, String rec){
        super(context);
        ingredientName = new TextView(context);
        recipeName = new TextView(context);
        inventoryButton = new ImageButton(context);
        shoppingButton = new ImageButton(context);

        addView(ingredientName);
        addView(recipeName);
        addView(inventoryButton);
        addView(shoppingButton);

        setName(name);
        setRecipe(rec);
    }

    public void setName(String name){
        id = name;
        ingredientName.setText(id);
    }

    public void setRecipe(String rec){
        recipe = rec;
        recipeName.setText(rec);
    }

    public String getName(){
        return id;
    }

    public String getRecipe(){ return recipe; }

}
