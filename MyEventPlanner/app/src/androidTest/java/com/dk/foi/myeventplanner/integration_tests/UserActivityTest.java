package com.dk.foi.myeventplanner.integration_tests;

import android.app.Fragment;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;

import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.activities.UserActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class UserActivityTest {
    @Rule
    public ActivityTestRule<UserActivity> rule  = new  ActivityTestRule<>(UserActivity.class);

    @Test
    public void ensureUserActivityAndNavigationArePresent() throws Exception {
        UserActivity activity = rule.getActivity();
        assertThat("User activity was not initialised", activity, notNullValue());

        DrawerLayout drawer = activity.findViewById(R.id.drawer_layout_user);
        assertThat("Navigation drawer was not initialised", drawer, notNullValue());
    }

    @Test
    public void ensureUserMainFragmentIsPresent() throws Exception {
        UserActivity activity = rule.getActivity();
        assertThat("Main activity was not initialised", activity, notNullValue());

        Fragment fragment = activity.getFragmentManager().findFragmentById(R.id.fragment_container);
        assertThat("Main fragment was not initialised", fragment, notNullValue());

        Button holidaysButton = activity.findViewById(R.id.button_ms_holiday);
        assertThat("Holidays button was not initialised", holidaysButton, notNullValue());

        Button birthdaysButton = activity.findViewById(R.id.button_ms_birthday);
        assertThat("Birthdays button was not initialised", birthdaysButton, notNullValue());

        Button otherEventsButton = activity.findViewById(R.id.button_ms_other);
        assertThat("Other events button was not initialised", otherEventsButton, notNullValue());

        Button upcomingEventsButton = activity.findViewById(R.id.button_ms_upcoming);
        assertThat("Upcoming events button was not initialised", upcomingEventsButton, notNullValue());

        Button personalEventsButton = activity.findViewById(R.id.button_ms_personal);
        assertThat("Personal events button was not initialised", personalEventsButton, notNullValue());
    }
}
