package com.dk.foi.myeventplanner.integration_tests;

import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;

import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.activities.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> rule  = new  ActivityTestRule<>(LoginActivity.class);

    @Test
    public void ensureLoginScreenIsCorrectlyInitialised() throws Exception {
        LoginActivity activity = rule.getActivity();
        assertThat("Login activity was not initialised", activity, notNullValue());

        DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);
        assertThat("Navigation drawer was initialised when it should not", drawer, nullValue());

        Button loginButton = activity.findViewById(R.id.button_login);
        assertThat("Login button was not initialised", loginButton, notNullValue());

    }
}
