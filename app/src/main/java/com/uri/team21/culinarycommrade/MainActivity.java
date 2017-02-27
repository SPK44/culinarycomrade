package com.uri.team21.culinarycommrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    // Test for github
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}
