package com.uri.team21.culinarycommrade;
import android.content.Context;
import android.os.Parcel;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
@SmallTest

public class DataBaseTest {
    public static final String TEST_ITEM = "WildTestData";
    public static final String TEST_RECIPE = "WACKYRECIPE";
    private DataAccessor access;
    Context context;
    File myExternalFile;
    private String filename = "SQL.txt";
    private String filepath = "Downloads";

    @Before
    public void setup() {
        context = InstrumentationRegistry.getTargetContext();
        access = new DataAccessor(context);
    }

    @Test
    public void test_inventory() {
        access.deleteAllInventory();
        access.toggleInventory(TEST_ITEM);
        ArrayList<String> list = access.getInventory();
        assertEquals("Does not get one item", TEST_ITEM, list.get(0));
        access.deleteAllInventory();
    }

    @Test
    public void test_toogle_inv() {
        ArrayList<String> all = access.getAllIngredients();
        for(String i : all) {
            assertTrue(access.toggleInventory(i));
            assertFalse(access.toggleInventory(i));
        }


    }

    @Test
    public void test_shopping_list() {
        assertTrue("Item already in",access.toggleShoppingList(TEST_ITEM, TEST_ITEM));
        String [][] list = access.getShoppingList();
        boolean test = false;
        ArrayList<String> debug = new ArrayList<>();
        for(String[] i : list) {
            if (i[0].equals(TEST_ITEM)) test=true;
            debug.add(i[0]);
        }
        assertTrue(debug.toString(), test);
        access.toggleShoppingList(TEST_ITEM, TEST_ITEM);
        list = access.getShoppingList();
        test = false;
        for(String[] i : list) {
            if (i[0].equals(TEST_ITEM)) test=true;
        }
        assertFalse("Toggling again didn't work",test);
    }



    @Test
    public void test_recipes() {
        //String allRecipes = "";
        ArrayList<String> recipes = access.getRecipes();
        ArrayList<String> all = access.getAllIngredients();
        for(String i : all) {

            ArrayList<String> ingred = access.getIngredients(i);
            for(String j : ingred) {
                assertTrue(all.contains(j));
            }
            //String j = i.replaceAll("'", "''");
           // allRecipes += "INSERT INTO allIngredients (Ingredients) VALUES ('" + j + "');\n";

        }
    /* Legacy Code for testing
        try {
            myExternalFile = new File(context.getExternalFilesDir(filepath), filename);
            FileOutputStream fos = new FileOutputStream(myExternalFile);
            fos.write(allRecipes.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
}
