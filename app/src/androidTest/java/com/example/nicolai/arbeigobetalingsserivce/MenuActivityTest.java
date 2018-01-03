package com.example.nicolai.arbeigobetalingsserivce;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nicolai on 03-01-2018.
 */
public class MenuActivityTest {
    @Rule
    public ActivityTestRule<MenuActivity> menuActivityActivityTestRule = new ActivityTestRule<MenuActivity>(MenuActivity.class);
    private MenuActivity mActivity = null;

    @Before
    public void setUp() throws Exception {

        mActivity = menuActivityActivityTestRule.getActivity();

    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.ArbeigoUser);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}