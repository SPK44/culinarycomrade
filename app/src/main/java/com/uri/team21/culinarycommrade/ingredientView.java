package com.uri.team21.culinarycommrade;

import android.widget.LinearLayout;
import android.content.Context;
import android.widget.TextView;
import android.widget.Button;

/**
 * Created by Shaps on 2/26/2017.
 */

public class ingredientView extends LinearLayout {

    private TextView ingredientName;
    private Button inventoryButton;
    private Button shoppingButton;

    public ingredientView(Context context){
        super(context);
        ingredientName = new TextView(context);
        inventoryButton = new Button(context);
        shoppingButton = new Button(context);

        addView(ingredientName);
    }

    public void setName(String name){
        ingredientName.setText(name);
    }
    public void setIngredientName(String name){
        ingredientName.setText(name);
    }
}
