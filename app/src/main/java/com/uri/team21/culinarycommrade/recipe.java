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
        //Accesses the database to retrieve the recipe's ingredients
        DataAccessor dataAccess = new DataAccessor(this);
        ArrayList<String> ingredients = dataAccess.getIngredients(recipeName);

        //Loops through every ingredient, creating an ingredientView for each of them
        for (int i = 0; i < ingredients.size(); i++) {
            String ingredient = ingredients.get(i);
            //Creates a new ingredientView for the current ingredient
            ingredientView ingredientViewAdd = new ingredientView(this, ingredient, "");
            if (!(ingredient.equals("null"))) {
                //Adds the ingredient view to the screen's list of ingredientViews
                List_file.add(ingredientViewAdd);
                //Prints the added ingredient to the console for debug purposes
                Log.d(TAG, "adding " + ingredient + " to list_file");
            }
        }
        //Passes the list of ingredientViews to ingList, the UI element responsible for displaying them
        ingList.setAdapter(new ingredientAdapter(recipe.this, R.layout.ingredientlayout, List_file));

    }
    private void CreateDirListView() {
        //Retrieves the recipe's directions from the database
        DataAccessor dataAccess = new DataAccessor(this);
        directions = dataAccess.getDirections(recipeName);
        //Passes the directions string to the UI element responsible for displaying it
        dirList.setText(directions);
    }
}
