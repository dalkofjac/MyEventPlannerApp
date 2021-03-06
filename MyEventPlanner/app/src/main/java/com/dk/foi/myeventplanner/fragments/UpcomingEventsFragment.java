package com.dk.foi.myeventplanner.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.data.services.GeneralEventDataService;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.adapters.UpcomingEventsAdapter;
import com.dk.foi.myeventplanner.services.EventListSorterService;
import com.dk.foi.myeventplanner.services.TemplateDataService;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class UpcomingEventsFragment extends Fragment {
    private List<Event> eventList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UpcomingEventsAdapter mAdapter;
    private EventListSorterService sorterService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_upcoming_event,container,false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getFragmentTitle());
        sorterService = new EventListSorterService();
        recyclerView = getView().findViewById(R.id.main_recycler_2);

        eventList = requestData();

        mAdapter = new UpcomingEventsAdapter(eventList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    private List<Event> requestData(){
        List<Event> events;

        GeneralEventDataService generalDataService = new GeneralEventDataService();
        TemplateDataService templateDataService = new TemplateDataService();

        // if local db is empty then load template data
        if(generalDataService.isEmpty()) {
            templateDataService.loadTemplateData();
        }

        events = generalDataService.getAll();

        events = sorterService.attachYears(events);
        events = sorterService.sortTheList(events);

        while(events.size()!=5){
            events.remove(events.size()-1);
        }

        return events;
    }

    private String getFragmentTitle() {
        return getResources().getString(R.string.upcoming_title);
    }
}
