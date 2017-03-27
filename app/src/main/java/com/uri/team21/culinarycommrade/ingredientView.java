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
    private String id;


    public ingredientView(Context context){
        super(context);
        ingredientName = new TextView(context);
        inventoryButton = new Button(context);
        shoppingButton = new Button(context);

        addView(ingredientName);
        addView(inventoryButton);
        addView(shoppingButton);

        id = "unset";
        setIngredientName(id);

        DataAccessor dataAccess = new DataAccessor(this.getContext());
    }

    public ingredientView(Context context, String name){
        super(context);
        ingredientName = new TextView(context);
        inventoryButton = new Button(context);
        shoppingButton = new Button(context);

        addView(ingredientName);
        addView(inventoryButton);
        addView(shoppingButton);

        id = name;
        setIngredientName(id);

        DataAccessor dataAccess = new DataAccessor(this.getContext());
    }


    public void setName(String name){
        id = name;
        ingredientName.setText(id);
    }

    public String getName(){
        return id;
    }

    public void setClickListener(OnClickListener listener){
        inventoryButton.setOnClickListener(listener);
        shoppingButton.setOnClickListener(listener);
    }

    public void inventoryClick(){
        setIngredientName("butt");
    }

    public void setIngredientName(String name){
        id = name;
        ingredientName.setText(id);
    }
}
