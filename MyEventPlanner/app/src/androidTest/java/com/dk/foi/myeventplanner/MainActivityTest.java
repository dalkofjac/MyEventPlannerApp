package com.dk.foi.myeventplanner;

import android.app.Fragment;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dk.foi.myeventplanner.MainActivity;
import com.dk.foi.myeventplanner.R;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureMainFragmentIsPresent() throws Exception {
        MainActivity activity = rule.getActivity();

        Fragment fragment = activity.getFragmentManager().findFragmentById(R.id.fragment_container);
        assertThat("Main fragment was not initialised.", fragment, notNullValue());

        View holidaysButton = activity.findViewById(R.id.button_ms_holiday);
        assertThat("Holidays button was not initialised.", holidaysButton, notNullValue());
    }
}
