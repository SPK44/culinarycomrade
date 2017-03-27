package com.uri.team21.culinarycommrade;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class recipe extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        //TODO: recipeName should be passed to this Activity
        String  recipeName = "Apple Pie";

        ListView ingList = (ListView)findViewById(R.id.list1);
        ListView dirList = (ListView)findViewById(R.id.list_recipe);
        TextView recName = (TextView) findViewById(R.id.text);

        recName.setText(recipeName);
        CreateIngListView();
        CreateDirListView();
    }

    private void CreateIngListView() {
        //Sample Code for displaying data using DataAccess
        DataAccessor dataAccess = new DataAccessor(this);
        //TODO: get ingredients based on recipeName

    }
    private void CreateDirListView() {
        //Sample Code for displaying data using DataAccess
        DataAccessor dataAccess = new DataAccessor(this);
        //TODO: get directions based on recipeName
    }
}
