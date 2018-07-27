package com.dk.foi.myeventplanner.ui_tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.dk.foi.myeventplanner.MainActivity;
import com.dk.foi.myeventplanner.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class AddNewEventUITest {

    private String testEventName = "Example event with wrong date";
    private String testEventDate = "20/30";
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureButtonAddNewEventWorks() throws Exception {
        onView(withId(R.id.button_ms_holiday)).perform(click());
        onView(withId(R.id.fab_event)).perform(click());

        onView(withId(R.id.editText_add_event_name)).check(matches(withText("")));
        onView(withId(R.id.editText_add_event_date)).check(matches(withText("")));

        onView(withId(R.id.editText_add_event_name)).perform(typeText(testEventName));
        onView(withId(R.id.editText_add_event_date)).perform(typeText(testEventDate));

        onView(withId(R.id.button_add_event)).perform(click());

        onView(withId(R.id.editText_add_event_name)).check(matches(withText(testEventName)));
        onView(withId(R.id.editText_add_event_date)).check(matches(withText(testEventDate)));
    }
}
