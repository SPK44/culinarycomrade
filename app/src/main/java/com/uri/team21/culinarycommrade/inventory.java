package com.uri.team21.culinarycommrade;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import static android.content.ContentValues.TAG;

public class inventory extends ListActivity {
    /** Called when the activity is first created. */
    private AutoCompleteTextView text;
    ListView list;
    private ArrayList<ingredientView> List_file;
    private List<String> list_file_names;
    ArrayList<String> ingredients;
    DataAccessor dataAccess;
    ArrayList<String> inventory;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);


        dataAccess = new DataAccessor(this);
        ingredients = dataAccess.getAllIngredients();
        inventory = dataAccess.getInventory();

        //setup for list view
        List_file = new ArrayList<>();
        list = (ListView) findViewById(android.R.id.list);
        CreateListView();

        //setup for autocomplete search
        text = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,ingredients);
        text.setAdapter(adapter);
        text.setThreshold(1);
    }

    private void CreateListView() {
        //Sample Code for displaying data using DataAccess

        for (int i = 0; i < inventory.size(); i++) {
            String ingredient = inventory.get(i);
            if(!(ingredient.equals("null"))) {
                ingredientView ingredientViewAdd = new ingredientView(this, ingredient);
                List_file.add(ingredientViewAdd);
                Log.d(TAG, "adding " + ingredient + " to list_file");
            }
        }

        //Create an adapter for the listView and add the ArrayList to the adapter.
        list.setAdapter(new ingredientAdapter(inventory.this, R.layout.ingredientlayout, List_file));
        list.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                //args2 is the listViews Selected index
            }
        });
    }
    public void Search(View view) {


        int duplicate = 0;
        int valid = 0;
        String selected = text.getText().toString();

        //check if selected item is a valid ing
        for (int i = 0; i < ingredients.size(); i++) {
            String ingredient = ingredients.get(i);
            if(ingredient.equals(selected) && !selected.equals("")) {
                valid = 1;
            }
        }
        if(valid == 1){
            //check if item already exists in inventory
            for (int i = 0; i < inventory.size(); i++) {
                String ingredient = inventory.get(i);
                if(ingredient.equals(selected) ) {
                    duplicate = 1;
                }
            }
            if(duplicate == 0) {
                Log.d(TAG, "adding " + text.getText().toString() + " to inventory");
                dataAccess.toggleInventory(text.getText().toString());
                ingredientView ingredientViewAdd = new ingredientView(this, selected);
                List_file.add(ingredientViewAdd);
                //reload
                finish();
                startActivity(getIntent());
            }
        }
        text.setText("");
    }
}
