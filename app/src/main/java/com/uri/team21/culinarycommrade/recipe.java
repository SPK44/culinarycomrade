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
import android.text.method.ScrollingMovementMethod;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class recipe extends Activity {

    ListView ingList;
    TextView dirList;
    TextView recName;
    private String recipeName;
    private ArrayList<ingredientView> List_file;
    private String directions;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        List_file = new ArrayList<>();

        Bundle b = getIntent().getExtras();
        recipeName = "null";
        if(b != null)
            recipeName = b.getString("recipeName");

        Log.d(TAG, "Displaying " + recipeName);
        ingList = (ListView)findViewById(R.id.list1);
        dirList = (TextView)findViewById(R.id.textDirections);
        recName = (TextView)findViewById(R.id.textView);

        recName.setText(recipeName);
        dirList.setMovementMethod(new ScrollingMovementMethod());
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
            //ingredientView ingredientViewAdd = new ingredientView(this, ingredient, recipeName);
            ingredientView ingredientViewAdd = new ingredientView(this, ingredient, "");
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
        directions = dataAccess.getDirections(recipeName);
        dirList.setText(directions);
    }
}
