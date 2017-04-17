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

    @Before
    public void setup() {
        Context context = InstrumentationRegistry.getTargetContext();
        access = new DataAccessor(context);
    }

    @Test
    public void test_inventory() {
        access.deleteAllInventory();
        access.toggleInventory(TEST_ITEM);
        ArrayList<String> list = access.getInventory();
        assertEquals("Does not get one item", TEST_ITEM, list.get(0));
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
        String allRecipes = "";
        ArrayList<String> recipes = access.getRecipes();
        ArrayList<String> all = access.getAllIngredients();
        for(String i : recipes) {
            ArrayList<String> ingred = access.getIngredients(i);
            for(String j : ingred) {
                assertTrue(all.contains(j));
            }
            allRecipes = "INSERT INTO allIngredients (Ingredients) VALUES ('" + i + "');";
        }
        Log.d("YOLO", allRecipes);
    }
}
