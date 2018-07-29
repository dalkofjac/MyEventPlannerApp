package com.dk.foi.myeventplanner.fragments;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.fragments.base.EventFragmentBase;

import java.util.List;

public class HolidaysFragment extends EventFragmentBase {

    public HolidaysFragment() {
        super(EventType.HOLIDAY);
    }

    @Override
    protected String getFragmentTitle() {
        return getResources().getString(R.string.holidays_title);
    }

    @Override
    protected List<Event> requestData(){
        return super.requestData();
    }
}
