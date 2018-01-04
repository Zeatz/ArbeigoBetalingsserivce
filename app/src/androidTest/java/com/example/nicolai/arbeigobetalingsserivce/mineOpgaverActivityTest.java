package com.example.nicolai.arbeigobetalingsserivce;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by Nicolai on 03-01-2018.
 */
public class mineOpgaverActivityTest {

    @Rule
    public ActivityTestRule<mineOpgaverActivity> mineOpgaverActivityActivityTestRule =
            new ActivityTestRule<mineOpgaverActivity>(mineOpgaverActivity.class);

    private mineOpgaverActivity mOpgaverActivity = null;

    private Instrumentation.ActivityMonitor monitor
            = getInstrumentation().addMonitor(mineOpgaverActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {

        mOpgaverActivity = mineOpgaverActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaucnhButton(){
       assertNotNull(mOpgaverActivity.findViewById(R.id.button3));

       onView(withId(R.id.button3)).perform(click());

       Activity mOpgaveActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

       assertNotNull(mOpgaveActivity);

       mOpgaveActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mOpgaverActivity = null;
    }

}