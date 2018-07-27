package com.dk.foi.myeventplanner.fragments;

import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.fragments.base.EventFragmentBase;

public class OtherEventsFragment extends EventFragmentBase {

    public OtherEventsFragment() {
        super(EventType.OTHER);
    }

    @Override
    public String getFragmentTitle() {
        return getResources().getString(R.string.other_events_title);
    }

    @Override
    protected void requestData(){
        super.requestData();
    }
}
