package com.dk.foi.myeventplanner.fragments.base;

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
import com.dk.foi.myeventplanner.fragments.AddNewEventFragment;
import com.dk.foi.myeventplanner.helpers.FragmentStarter;
import com.dk.foi.myeventplanner.services.EventListSorterService;
import com.dk.foi.myeventplanner.services.TemplateDataService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class EventFragmentBase extends Fragment {
    private final String defaultFragmentTitle = "Events";

    private RecyclerView recyclerView;
    private EventsAdapter mAdapter;
    private EventListSorterService sorterService;
    private TemplateDataService templateDataService;

    @BindView(R.id.fab_event)
    public FloatingActionButton fab;

    private EventType eventType;

    protected List<Event> eventList = new ArrayList<>();
    protected EventDataService dataService;

    public EventFragmentBase(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_event,container,false);
        ButterKnife.bind(this, view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putInt("EVENT_TYPE", eventType.ordinal());

                AddNewEventFragment af = new AddNewEventFragment();
                af.setArguments(args);

                FragmentStarter.StartNewFragment(af, getActivity(), FragmentLevel.LEVEL_TWO);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getFragmentTitle());

        dataService = new EventDataService(eventType);
        templateDataService = new TemplateDataService();
        sorterService = new EventListSorterService();
        recyclerView = getView().findViewById(R.id.main_recycler_2);

        requestData();

        mAdapter = new EventsAdapter(eventList, getActivity(), eventType);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    protected void requestData(){
        if(dataService.isEmpty()){
            eventList = templateDataService.getTemplateData(eventType);
        }
        else{
            eventList = dataService.getAll();
        }
        eventList = sorterService.attachYears(eventList);
        eventList = sorterService.sortTheList(eventList);
    }

    protected String getFragmentTitle() {
        return defaultFragmentTitle;
    }
}
