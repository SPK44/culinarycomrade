package com.uri.team21.culinarycommrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.graphics.Color;

public class search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_search);



        //Sample Code for displaying data using DataAccess
        DataAccessor dataAccess = new DataAccessor(this);


        // Create a TextView programmatically.
        TextView tv = new TextView(getApplicationContext());

        // Create a LayoutParams for TextView
        LayoutParams lp = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, // Width of TextView
                LayoutParams.WRAP_CONTENT); // Height of TextView

        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);

        // Set text to display in TextView
        tv.setText(dataAccess.getRecipes().toString());

        // Set a text color for TextView text
        tv.setTextColor(Color.parseColor("#ff0000"));

        // Add newly created TextView to parent view group (RelativeLayout)
        rl.addView(tv);
    }
}
