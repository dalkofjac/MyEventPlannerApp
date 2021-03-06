package com.dk.foi.myeventplanner.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.data.services.GeneralEventDataService;
import com.dk.foi.myeventplanner.R;
import com.dk.foi.myeventplanner.helpers.DateManager;
import com.dk.foi.myeventplanner.webservices.PersonalEventService;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewEventFragment extends Fragment {
    private EventType eventType;
    private String fragmentTitle;
    private String addingMsg;
    private String badInput;
    private String badFormat;
    private String unDate;
    private String nameTaken;

    private GeneralEventDataService dataService;

    @BindView(R.id.editText_add_event_name)
    EditText newEventName;

    @BindView(R.id.editText_add_event_date)
    EditText newEventDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_add_new_event,container,false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        eventType = EventType.fromInteger(getArguments().getInt("EVENT_TYPE"));

        if(eventType == EventType.HOLIDAY) {
            fragmentTitle = getResources().getString(R.string.add_holiday_title);
        } else if (eventType == EventType.BIRTHDAY) {
            fragmentTitle = getResources().getString(R.string.add_birthday_title);
        } else if (eventType == EventType.PERSONAL){
            fragmentTitle = getResources().getString(R.string.add_personal_event_title);
        } else {
            fragmentTitle = getResources().getString(R.string.add_other_event_title);
        }

        addingMsg = getResources().getString(R.string.added_event);
        badInput = getResources().getString(R.string.bad_input);
        badFormat = getResources().getString(R.string.bad_date_format);
        unDate = getResources().getString(R.string.invalid_date);
        nameTaken = getResources().getString(R.string.name_alr_exist);

        dataService = new GeneralEventDataService();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(fragmentTitle);
    }

    @OnClick(R.id.button_add_event)
    public void AddEventButtonPressed(){
        String eventName = newEventName.getText().toString();
        String eventDate = newEventDate.getText().toString();

        try {
            if (eventType == EventType.HOLIDAY && dateCheck(eventDate) == true && availableNameCheck(eventName) == true) {
                Event event = new Event(EventType.HOLIDAY, eventName, eventDate, DateManager.getTodayDate());
                event.save();
                getActivity().onBackPressed();

                Toast.makeText(getActivity(), addingMsg, Toast.LENGTH_SHORT).show();
            }
            if (eventType == EventType.BIRTHDAY && dateCheck(eventDate) == true && availableNameCheck(eventName) == true) {
                Event event = new Event(EventType.BIRTHDAY, eventName, eventDate, DateManager.getTodayDate());
                event.save();
                getActivity().onBackPressed();

                Toast.makeText(getActivity(), addingMsg, Toast.LENGTH_SHORT).show();
            }
            if (eventType == EventType.OTHER && dateCheck(eventDate) == true && availableNameCheck(eventName) == true) {
                Event event = new Event(EventType.OTHER, eventName, eventDate, DateManager.getTodayDate());
                event.save();
                getActivity().onBackPressed();

                Toast.makeText(getActivity(), addingMsg, Toast.LENGTH_SHORT).show();
            }
            if (eventType == EventType.PERSONAL && dateCheck(eventDate) == true) {
                Event event = new Event(EventType.PERSONAL, eventName, eventDate, DateManager.getTodayDate());
                addPersonalEvent(getActivity().getIntent().getStringExtra("USER_ID"), event);
                getActivity().onBackPressed();

                Toast.makeText(getActivity(), addingMsg, Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getActivity(), badInput, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean dateCheck(String date){
        // Specific check for 29/02 (happening only every 4 years)
        if(date.matches("29/02") || date.matches("29/2")) {
            return true;
        }

        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        date = date + "/" + String.valueOf(currentYear);

        if(!DateManager.isValidDate(date)){
            Toast.makeText(getActivity(), unDate, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private boolean availableNameCheck(String name){
        List<Event> eventList = dataService.getAll();
        for(int i=0;i<eventList.size(); i++){
            if(eventList.get(i).getName().matches(name)){
                Toast.makeText(getActivity(), nameTaken, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void addPersonalEvent(String userId, Event event){
        PersonalEventService eventService = new PersonalEventService();
        eventService.create(userId, event);
    }
}
