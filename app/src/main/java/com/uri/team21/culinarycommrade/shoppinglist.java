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

public class shoppinglist extends ListActivity {
    /** Called when the activity is first created. */
    ListView list;
    private List<String> List_file;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppinglist);
        List_file = new ArrayList<String>();
        list = (ListView) findViewById(android.R.id.list);

        CreateListView();
    }

    private void CreateListView() {
        //Sample Code for displaying data using DataAccess
        DataAccessor dataAccess = new DataAccessor(this);

        //TODO: these are only here for testing, remove once we can add items to inventory from other places
        dataAccess.addToShoppingList("apples", "apple pie");
        dataAccess.addToShoppingList("bananas", "banana split");
        dataAccess.addToShoppingList("oranges", "orange creamsicle");

        String[][] shoppingList = dataAccess.getShoppingList();

        for (int i = 0; i < shoppingList.length; i++) {
            String ingredient = shoppingList[i][0];
            String recipe = shoppingList[i][1];
            String concat = ingredient + " - (" + recipe + ")";
            //TODO: make ingredientViews to add to list
            //ingredientView ingredientView = new ingredientView(concat);
            if(!(concat.equals("null - (null)"))) {
                List_file.add(concat);
                Log.d(TAG, "adding " + concat + " to list_file");
            }
        }

        //Create an adapter for the listView and add the ArrayList to the adapter.
        list.setAdapter(new ArrayAdapter<String>(shoppinglist.this, android.R.layout.simple_list_item_1,List_file));
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

