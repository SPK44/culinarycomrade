package com.uri.team21.culinarycommrade;

/**
 * Created by Shaps on 3/26/2017.
 */


import android.app.Activity;
import android.content.Context;
import java.util.List;

import android.content.Intent;
import android.widget.AdapterView.OnItemClickListener;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ImageButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class ingredientAdapter extends ArrayAdapter<ingredientView> {

    private ArrayList<ingredientView> ingredients = new ArrayList<>();
    private Context mContext;
    private ArrayList<String> ingredNames;


    public ingredientAdapter(Context context, int textViewResourceId, ArrayList<ingredientView> objects) {
        super(context, textViewResourceId, objects);
        mContext = context;
        System.out.println(objects);
        ingredients = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ingredientView view;
        View v = convertView;
        ContainsChecker checker = new ContainsChecker(mContext);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ingredientlayout, parent, false);
        }

        final TextView ingName = (TextView) convertView.findViewById(R.id.ingName);
        final TextView recName = (TextView) convertView.findViewById(R.id.recName);

        ImageButton inventBut = (ImageButton) convertView.findViewById(R.id.inventBut);
        ImageButton shopBut = (ImageButton) convertView.findViewById(R.id.shopBut);

        ingName.setText(ingredients.get(position).getName());
        recName.setText(ingredients.get(position).getRecipe());

        Boolean inInv = checker.inInv(ingName.getText().toString());
        Boolean inList = checker.inList(ingName.getText().toString(), recName.getText().toString());

        if(mContext instanceof inventory){
            inventBut.setImageResource(R.drawable.removeinventory);
            shopBut.setImageResource(R.drawable.addshopping);
        } else if(mContext instanceof shoppinglist){
            inventBut.setImageResource(R.drawable.addinventory);
            shopBut.setImageResource(R.drawable.removeshopping);
        } else {
            if(inInv)
                inventBut.setImageResource(R.drawable.removeinventory);
            else
                inventBut.setImageResource(R.drawable.addinventory);

            if(inList)
                shopBut.setImageResource(R.drawable.removeshopping);
            else
                shopBut.setImageResource(R.drawable.addshopping);
        }



        inventBut.setOnClickListener(new View.OnClickListener() {
            DataAccessor dataAccess = new DataAccessor(mContext);
            String name = ingName.getText().toString();
            String recipe = recName.getText().toString();
            @Override
            public void onClick(View v) {
                if(mContext instanceof shoppinglist){
                    dataAccess.toggleInventory(name);
                    dataAccess.toggleShoppingList(name, recipe);

                } else {
                    dataAccess.toggleInventory(name);
                }
                notifyDataSetChanged();

            }


        });

        shopBut.setOnClickListener(new View.OnClickListener() {
            DataAccessor dataAccess = new DataAccessor(mContext);
            String name = ingName.getText().toString();
            String recipe = recName.getText().toString();
            @Override
            public void onClick(View v) {
                if(mContext instanceof inventory){
                    dataAccess.toggleInventory(name);
                    dataAccess.toggleShoppingList(name, recipe);
                } else {
                    dataAccess.toggleShoppingList(name, recipe);
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
