package com.dk.foi.myeventplanner.fragments;

import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.fragments.base.EventFragmentBase;

public class HolidaysFragment extends EventFragmentBase {

    public HolidaysFragment() {
        super(EventType.HOLIDAY);
    }

    @Override
    public String getFragmentTitle() {
        return getResources().getString(R.string.holidays_title);
    }

    @Override
    protected void requestData(){
        super.requestData();
    }
}
