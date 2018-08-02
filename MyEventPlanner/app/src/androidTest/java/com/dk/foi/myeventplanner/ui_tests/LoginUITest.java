package com.dk.foi.myeventplanner.ui_tests;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.dk.foi.myeventplanner.MainActivity;
import com.dk.foi.myeventplanner.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LoginUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureLoginWorks() throws Exception {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_login));

        onView(withId(R.id.editText_username)).check(matches(withText("")));
        onView(withId(R.id.editText_password)).check(matches(withText("")));

        onView(withId(R.id.button_login)).perform(click());

        onView(withId(R.id.editText_username)).check(matches(withText("")));
        onView(withId(R.id.editText_password)).check(matches(withText("")));
    }
}
