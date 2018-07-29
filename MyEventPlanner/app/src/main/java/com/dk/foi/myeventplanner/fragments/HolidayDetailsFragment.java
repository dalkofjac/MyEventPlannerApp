package com.dk.foi.myeventplanner.fragments;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.fragments.base.EventDetailsFragmentBase;

public class HolidayDetailsFragment extends EventDetailsFragmentBase {

    public HolidayDetailsFragment() {
        super(EventType.HOLIDAY);
    }

    @Override
    protected String getFragmentTitle() {
        return getResources().getString(R.string.holiday_details_title);
    }

    @Override
    protected Event loadCurrentEvent() {
        return super.loadCurrentEvent();
    }

    @Override
    protected void deleteCurrentEvent() {
        super.deleteCurrentEvent();
    }

}
