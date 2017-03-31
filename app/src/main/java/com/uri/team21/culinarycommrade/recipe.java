package com.uri.team21.culinarycommrade;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class recipe extends Activity {

    ListView ingList;
    ListView dirList;
    TextView recName;
    private String recipeName;
    private ArrayList<ingredientView> List_file;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        List_file = new ArrayList<>();

        //TODO: recipeName should be passed to this Activity
        recipeName = "Apple Pie";

        ingList = (ListView)findViewById(R.id.list1);
        dirList = (ListView)findViewById(R.id.list_recipe);
        recName = (TextView) findViewById(R.id.text);

        recName.setText(recipeName);
        CreateIngListView();
        CreateDirListView();
    }

    private void CreateIngListView() {
        //Sample Code for displaying data using DataAccess
        DataAccessor dataAccess = new DataAccessor(this);
        //TODO: get ingredients based on recipeName
        ArrayList<String> ingredients = dataAccess.getIngredients(recipeName);

        //A lot of the following is copied over from inventory. I think this should work.
        for (int i = 0; i < ingredients.size(); i++) {
            String ingredient = ingredients.get(i);
            ingredientView ingredientViewAdd = new ingredientView(this, ingredient);
            if (!(ingredient.equals("null"))) {
                List_file.add(ingredientViewAdd);
                Log.d(TAG, "adding " + ingredient + " to list_file");
            }
        }

        ingList.setAdapter(new ingredientAdapter(recipe.this, R.layout.ingredientlayout, List_file));

    }
    private void CreateDirListView() {
        //Sample Code for displaying data using DataAccess
        DataAccessor dataAccess = new DataAccessor(this);
        //TODO: get directions based on recipeName
    }
}
