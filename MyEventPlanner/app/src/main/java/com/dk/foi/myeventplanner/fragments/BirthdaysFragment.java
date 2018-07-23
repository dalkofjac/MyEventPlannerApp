package com.dk.foi.myeventplanner.fragments;

import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.fragments.base.EventFragmentBase;

public class BirthdaysFragment extends EventFragmentBase {

    public BirthdaysFragment() {
        super(EventType.BIRTHDAY);
    }

    @Override
    public String getFragmentTitle() {
        return getResources().getString(R.string.birthdays_title);
    }

    @Override
    protected void requestData(){
        super.requestData();
    }
}
