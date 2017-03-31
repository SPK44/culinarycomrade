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
    private ListView list;
    private ArrayList<ingredientView> List_file;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppinglist);
        List_file = new ArrayList<>();
        list = (ListView) findViewById(android.R.id.list);
        CreateListView();
    }
    private void CreateListView() {
        //Sample Code for displaying data using DataAccess
        DataAccessor dataAccess = new DataAccessor(this);

        //TODO: these are only here for testing, remove once we can add items to the shopping list from other places
        dataAccess.addToShoppingList("apples", "apple pie");
        dataAccess.addToShoppingList("bananas", "banana pie");
        dataAccess.addToShoppingList("oranges", "orange pie");

        String[][] shoppingList = dataAccess.getShoppingList();

        for (int i = 0; i < shoppingList.length; i++) {
            String ingredient = shoppingList[i][0];
            String recipe = shoppingList[i][1];
            String concat = ingredient + " - (" + recipe + ")";
            ingredientView ingredientViewAdd = new ingredientView(this, ingredient);
            if(!(recipe.equals("null"))) {
                List_file.add(ingredientViewAdd);
                Log.d(TAG, "adding " + ingredient + " to list_file");
            }
        }

        //Create an adapter for the listView and add the ArrayList to the adapter.
        list.setAdapter(new ingredientAdapter(shoppinglist.this, R.layout.ingredientlayout, List_file));
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
