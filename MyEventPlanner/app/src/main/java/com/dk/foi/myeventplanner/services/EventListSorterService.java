package com.dk.foi.myeventplanner.services;

import com.dk.foi.data.entities.Event;
import com.dk.foi.myeventplanner.helpers.DateManager;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EventListSorterService {

    public EventListSorterService(){
    }

    /**
     * Method sorts the Event List based on event date
     * @param eventList entry Event List (unsorted)
     * @return exit Event List (sorted)
     */
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

    /**
     * Method attaches year value on each member of Event List
     * It is using current date to establish which year it should attach
     * @param targetedEventList entry Event List (events without year value)
     * @return exit Event List (events with year value)
     */
    public List<Event> attachYears(List<Event> targetedEventList){
        Calendar cal=Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        Date todayDate = DateManager.convertStringToDate(DateManager.getTodayDate());
        for(int i=0; i<targetedEventList.size(); i++){
            Event event = targetedEventList.get(i);
            Date eventDate = DateManager.convertStringToDate(event.getDate()+"/"+currentYear);

            if(eventDate.before(todayDate)){
                int newCurrentYear = currentYear+1;
                String newDate = event.getDate()+"/"+newCurrentYear;
                if(DateManager.isValidDate(newDate)) {
                    targetedEventList.get(i).setDate(newDate);
                } else {
                    for(int j=0; j<4;j++) {
                        newCurrentYear++;
                        if(DateManager.isValidDate(event.getDate()+"/"+newCurrentYear)) {
                            newDate = event.getDate()+"/"+newCurrentYear;
                            targetedEventList.get(i).setDate(newDate);
                            break;
                        }
                    }
                }
            }
            else{
                targetedEventList.get(i).setDate(event.getDate()+"/"+currentYear);
            }
        }
        return targetedEventList;
    }
}
