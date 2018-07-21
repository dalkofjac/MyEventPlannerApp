package com.dk.foi.myeventplanner.services;

import com.dk.foi.data.entities.Event;
import com.dk.foi.myeventplanner.helpers.DateManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventListSorterService {

    public EventListSorterService(){
    }

    public List<Event> sortTheList(List<Event> eventList){
        Event eventHigh;
        Event eventLow;
        for(int i=0; i<eventList.size();i++){
            eventHigh = eventList.get(i);
            Date eventDateHigh = DateManager.convertStringToDate(eventHigh.getDate());
            for(int j=0;j<i;j++){
                eventLow = eventList.get(j);
                Date eventDateLow = DateManager.convertStringToDate(eventLow.getDate());
                if(eventDateLow.after(eventDateHigh)){
                    Collections.swap(eventList, i, j);
                }
            }
        }
        return eventList;
    }
    public List<Event> attachYears(List<Event> targetedEventList){
        Calendar cal=Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        Date todayDate = DateManager.convertStringToDate(DateManager.getTodayDate());
        for(int i=0; i<targetedEventList.size(); i++){
            Event event = targetedEventList.get(i);
            Date eventDate = DateManager.convertStringToDate(event.getDate()+"/"+currentYear);

            if(eventDate.before(todayDate)){
                int newCurrentYear = currentYear+1;
                targetedEventList.get(i).setDate(event.getDate()+"/"+newCurrentYear);
            }
            else{
                targetedEventList.get(i).setDate(event.getDate()+"/"+currentYear);
            }
        }
        return targetedEventList;
    }
}
