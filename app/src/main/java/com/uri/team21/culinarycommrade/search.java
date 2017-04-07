package com.uri.team21.culinarycommrade;

import android.app.ListActivity;
import android.content.Intent;
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

public class search extends ListActivity {
    /** Called when the activity is first created. */
    ListView list;
    private List<String> List_file;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        List_file = new ArrayList<String>();

        list = (ListView) findViewById(android.R.id.list);
        CreateListView();
    }

    private void CreateListView() {
        WeightedSearch weightedList = new WeightedSearch(this);
        WeightedMessenger sortedList = weightedList.getSortedList();
        String[] sortedRecipes = sortedList.recipes;
        double[] sortedWeights = sortedList.weights;

        for (int i = 0; i < sortedList.index; i++) {
            String recipeName = sortedRecipes[i];
            double recipeWeight = sortedWeights[i] * 100;
            String concat = recipeName + " - " + recipeWeight + " % match";
            if(!(recipeName.equals("null"))) {
                List_file.add(concat);
                Log.d(TAG, "adding " + recipeName + " to list_file");
            }
        }

        //Create an adapter for the listView and add the ArrayList to the adapter.
        list.setAdapter(new ArrayAdapter<String>(search.this, android.R.layout.simple_list_item_1,List_file));
        //list.setOnItemClickListener(new OnItemClickListener()
        //{
            //@Override
            //public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            //{
                //args2 is the listViews Selected index
            //}
        //});
    }

    public void onListItemClick(ListView list, View v, int position, long id) {
        //TODO: make this not hacked together code
        String[] split = ((list.getItemAtPosition(position)).toString()).split(" - ");
        String recipeName = split[0];
        //End of TODO
        Log.d(TAG, "clicked " + recipeName);
        Intent intent = new Intent(search.this, recipe.class);
        Bundle b = new Bundle();
        b.putString("recipeName", recipeName);
        intent.putExtras(b);
        startActivity(intent);
    }
}
