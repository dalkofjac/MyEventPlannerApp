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
import com.dk.foi.myeventplanner.helpers.FragmentStarter;
import com.dk.foi.myeventplanner.services.EventListSorterService;
import com.dk.foi.myeventplanner.services.TemplateDataService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BirthdaysFragment extends Fragment {
    private List<Event> eventList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EventsAdapter mAdapter;
    private EventDataService dataService;
    private EventListSorterService sorterService;

    @BindView(R.id.fab_event)
    public FloatingActionButton fab;

    private String fragmentTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_event,container,false);
        ButterKnife.bind(this, view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putInt("EVENT_TYPE", EventType.BIRTHDAY.ordinal());

                AddNewEventFragment anef = new AddNewEventFragment();
                anef.setArguments(args);

                FragmentStarter.StartNewFragment(anef, getActivity(), FragmentLevel.LEVEL_TWO);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        fragmentTitle = getResources().getString(R.string.birthdays_title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(fragmentTitle);
        dataService = new EventDataService(EventType.BIRTHDAY);
        sorterService = new EventListSorterService();
        recyclerView = getView().findViewById(R.id.main_recycler_2);

        requestData();

        mAdapter = new EventsAdapter(eventList, getActivity(), EventType.BIRTHDAY);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    private void requestData(){
        if(dataService.isEmpty()){
            eventList = TemplateDataService.getBirthdaysData();
        }
        else{
            eventList = dataService.getAll();
        }
        eventList = sorterService.attachYears(eventList);
        eventList = sorterService.sortTheList(eventList);
    }
}
