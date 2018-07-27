package com.dk.foi.myeventplanner.helpers;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.enums.FragmentLevel;

public class FragmentStarter {
    /**
     * Class for simple start of fragments.
     *
     * @param fragment targeted fragment
     * @param activity current activity
     * @param level fragment level for backstack priority
     */
    public static void StartNewFragment (Fragment fragment, Activity activity, FragmentLevel level) {
        if(level == FragmentLevel.INDEX) {
            FragmentTransaction fm = activity.getFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_container, fragment);
            fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fm.addToBackStack("1");
            fm.commit();
        }
        else if (level == FragmentLevel.LEVEL_ONE) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            if (fragmentManager.getBackStackEntryCount() <= 1) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.addToBackStack("2");
                ft.replace(R.id.fragment_container, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            } else {
                fragmentManager.popBackStack("1", 0);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack("2")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        }
        else{
            FragmentTransaction fm = activity.getFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_container, fragment);
            fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fm.addToBackStack(null);
            fm.commit();
        }
    }
}
