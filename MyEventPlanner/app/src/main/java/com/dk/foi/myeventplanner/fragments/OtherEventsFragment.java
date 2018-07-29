package com.dk.foi.myeventplanner.fragments;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.fragments.base.EventFragmentBase;

import java.util.List;

public class OtherEventsFragment extends EventFragmentBase {

    public OtherEventsFragment() {
        super(EventType.OTHER);
    }

    @Override
    protected String getFragmentTitle() {
        return getResources().getString(R.string.other_events_title);
    }

    @Override
    protected List<Event> requestData(){
        return super.requestData();
    }
}
