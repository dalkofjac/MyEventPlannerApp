package com.dk.foi.myeventplanner.fragments;

import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.fragments.base.EventDetailsFragmentBase;

public class OtherEventDetailsFragment extends EventDetailsFragmentBase {

    public OtherEventDetailsFragment() {
        super(EventType.OTHER);
    }

    @Override
    public String getFragmentTitle() {
        return getResources().getString(R.string.other_event_details_title);
    }
}
