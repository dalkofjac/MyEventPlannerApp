package com.dk.foi.myeventplanner.fragments;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.fragments.base.EventFragmentBase;
import com.dk.foi.myeventplanner.webservices.PersonalEventService;

import java.util.ArrayList;
import java.util.List;

public class PersonalEventsFragment extends EventFragmentBase {

    public PersonalEventsFragment() {
        super(EventType.PERSONAL);
    }

    @Override
    protected String getFragmentTitle() {
        return getResources().getString(R.string.personal_events_title);
    }

    @Override
    protected List<Event> requestData(){
        PersonalEventService service = new PersonalEventService();
        List<Event> events = new ArrayList<>();

        events = service.getAll(getActivity().getIntent().getStringExtra("USER_ID"));

        events = super.sorterService.attachYears(events);
        events = super.sorterService.sortTheList(events);

        return events;
    }
}
