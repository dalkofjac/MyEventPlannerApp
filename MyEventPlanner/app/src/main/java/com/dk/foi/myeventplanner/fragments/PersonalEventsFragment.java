package com.dk.foi.myeventplanner.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.data.services.EventDataService;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.adapters.EventsAdapter;
import com.dk.foi.myeventplanner.enums.FragmentLevel;
import com.dk.foi.myeventplanner.fragments.base.EventFragmentBase;
import com.dk.foi.myeventplanner.helpers.FragmentStarter;
import com.dk.foi.myeventplanner.services.EventListSorterService;
import com.dk.foi.myeventplanner.services.TemplateDataService;
import com.dk.foi.myeventplanner.webservices.PersonalEventService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
