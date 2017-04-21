package com.uri.team21.culinarycommrade;
import android.content.Context;
import android.database.Cursor;
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

public class DataBaseQuickTest {
    public static final String TEST_ITEM = "WildTestData";
    public static final String TEST_RECIPE = "WACKYRECIPE";
    private DataAccessor access;
    Context context;
    private ContainsChecker ch;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getTargetContext();
        access = new DataAccessor(context);
        ch = new ContainsChecker(context);
    }

    @Test
    public void test_yeild_directions() {
        /*DataBaseHelper db = new DataBaseHelper(context);
        Cursor c = db.query("SELECT * FROM esha");
        String[] strArr = c.getColumnNames();
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            strBuilder.append(strArr[i]);
        }
        String newString = strBuilder.toString();
        assertTrue(newString, false);*/
        ArrayList<String> recipes = access.getRecipes();
        access.getYield(recipes.get(0));
        access.getDirections(recipes.get(0));
    }

    @Test
    public void test_contains() {
        access.deleteAllInventory();
        assertTrue("Didn't add",access.toggleInventory(TEST_ITEM));
        ch.update();
        assertTrue("Not there",ch.inInv(TEST_ITEM));
    }

}
