package com.dk.foi.myeventplanner.integration_tests;

import android.app.Fragment;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.dk.foi.myeventplanner.MainActivity;
import com.dk.foi.myeventplanner.R;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

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

        View birthdaysButton = activity.findViewById(R.id.button_ms_birthday);
        assertThat("Birthdays button was not initialised.", birthdaysButton, notNullValue());

        View otherEventsButton = activity.findViewById(R.id.button_ms_other);
        assertThat("Other events button was not initialised.", otherEventsButton, notNullValue());

        View upcomingEventsButton = activity.findViewById(R.id.button_ms_upcoming);
        assertThat("Upcoming events button was not initialised.", upcomingEventsButton, notNullValue());
    }
}
