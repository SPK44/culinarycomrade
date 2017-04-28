package com.uri.team21.culinarycommrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    private AutoCompleteTextView text;
    DataAccessor dataAccess;
    ArrayList<String> recipes;

    // Test for github
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataAccess = new DataAccessor(this);
        recipes = dataAccess.getRecipes();

        text = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,recipes);
        text.setAdapter(adapter);
        text.setThreshold(1);

    }

    public void navigateInv(View view) {
        Intent startNewActivity = new Intent(this, inventory.class);
        startActivity(startNewActivity);
    }

    public void navigateSearch(View view) {
        Intent startNewActivity = new Intent(this, search.class);
        startActivity(startNewActivity);
    }

    public void navigateShop(View view) {
        Intent startNewActivity = new Intent(this, shoppinglist.class);
        startActivity(startNewActivity);
    }

    //public void navigateSnake(View view) {
        //Intent startNewActivity = new Intent(this, SnakeView.class);
        //startActivity(startNewActivity);
    //}

    public void View(View view) {
        String selected = text.getText().toString();
        int valid = 0;

        //check if selected item is a valid ing
        for (int i = 0; i < recipes.size(); i++) {
            String recipe = recipes.get(i);
            if(recipe.equals(selected) && selected != " ") {
                valid = 1;
            }
        }
        if(valid == 1){
            Log.d(TAG, "viewing " + text.getText().toString());
            String recipeName = selected;
            Intent intent = new Intent(MainActivity.this, recipe.class);
            Bundle b = new Bundle();
            b.putString("recipeName", recipeName);
            intent.putExtras(b);
            startActivity(intent);
        }
        text.setText("");
    }
}
