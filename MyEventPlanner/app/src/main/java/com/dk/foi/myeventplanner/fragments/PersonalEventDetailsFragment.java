package com.dk.foi.myeventplanner.fragments;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.fragments.base.EventDetailsFragmentBase;
import com.dk.foi.myeventplanner.webservices.PersonalEventService;

public class PersonalEventDetailsFragment extends EventDetailsFragmentBase {

    public PersonalEventDetailsFragment() {
        super(EventType.PERSONAL);
    }

    @Override
    protected String getFragmentTitle() {
        return getResources().getString(R.string.personal_event_details_title);
    }

    @Override
    protected void deleteCurrentEvent() {
        PersonalEventService eventService = new PersonalEventService();
        eventService.delete(getActivity().getIntent().getStringExtra("USER_ID"),
                String.valueOf(currentEvent.getId()));
    }

}
