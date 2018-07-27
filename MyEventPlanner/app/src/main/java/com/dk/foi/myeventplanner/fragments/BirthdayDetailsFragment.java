package com.dk.foi.myeventplanner.fragments;

import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.fragments.base.EventDetailsFragmentBase;

public class BirthdayDetailsFragment extends EventDetailsFragmentBase {

    public BirthdayDetailsFragment() {
        super(EventType.BIRTHDAY);
    }

    @Override
    public String getFragmentTitle() {
        return getResources().getString(R.string.birthday_details_title);
    }
}
