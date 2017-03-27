package com.uri.team21.culinarycommrade;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import static android.content.ContentValues.TAG;

public class inventory extends ListActivity {
    /** Called when the activity is first created. */
    ListView list;
    private ArrayList<ingredientView> List_file;
    private List<String> list_file_names;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        List_file = new ArrayList<>();

        list = (ListView) findViewById(android.R.id.list);
        CreateListView();
    }
    private void CreateListView() {
        //Sample Code for displaying data using DataAccess
        DataAccessor dataAccess = new DataAccessor(this);

        //TODO: these are only here for testing, remove once we can add items to inventory from other places
        dataAccess.addToInventory("apples");
        dataAccess.addToInventory("bananas");
        dataAccess.addToInventory("oranges");

        ArrayList<String> inventory = dataAccess.getInventory();

        for (int i = 0; i < inventory.size(); i++) {
            String ingredient = inventory.get(i);
            //TODO: make ingredientViews to add to list
            ingredientView ingredientViewAdd = new ingredientView(this, ingredient);
            if(!(ingredient.equals("null"))) {
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
}
