package com.dk.foi.myeventplanner.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.enums.FragmentLevel;
import com.dk.foi.myeventplanner.helpers.FragmentStarter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainScreenFragment extends Fragment {

    private String fragmentTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main_screen,container,false);
        ButterKnife.bind(this, view);

        fragmentTitle = getResources().getString(R.string.main_screen_title);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(fragmentTitle);
    }

    @OnClick(R.id.button_ms_holiday)
    public void onButtonMsHolidayClicked(){
        HolidaysFragment fragment = new HolidaysFragment();
        FragmentStarter.StartNewFragment(fragment, getActivity(), FragmentLevel.LEVEL_ONE);
    }

    @OnClick(R.id.button_ms_birthday)
    public void onButtonMsBirthdayClicked(){
        BirthdaysFragment fragment = new BirthdaysFragment();
        FragmentStarter.StartNewFragment(fragment, getActivity(), FragmentLevel.LEVEL_ONE);
    }

    @OnClick(R.id.button_ms_other)
    public void onButtonMsOtherEventClicked(){
        OtherEventsFragment fragment = new OtherEventsFragment();
        FragmentStarter.StartNewFragment(fragment, getActivity(), FragmentLevel.LEVEL_ONE);
    }

    @OnClick(R.id.button_ms_upcoming)
    public void onButtonMsUpcomingEventClicked(){
        UpcomingEventsFragment fragment = new UpcomingEventsFragment();
        FragmentStarter.StartNewFragment(fragment, getActivity(), FragmentLevel.LEVEL_ONE);
    }
}
