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
    private ImageButton inventoryButton;
    private ImageButton shoppingButton;
    private String id;


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

        DataAccessor dataAccess = new DataAccessor(this.getContext());
    }

    public ingredientView(Context context, String name){
        super(context);
        ingredientName = new TextView(context);
        inventoryButton = new ImageButton(context);
        shoppingButton = new ImageButton(context);

        addView(ingredientName);
        addView(inventoryButton);
        addView(shoppingButton);

        id = name;
        setName(id);
    }

    public void setName(String name){
        id = name;
        ingredientName.setText(id);
    }

    public String getName(){
        return id;
    }

}
