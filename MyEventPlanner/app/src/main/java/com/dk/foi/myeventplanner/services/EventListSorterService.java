package com.dk.foi.myeventplanner.services;

import com.dk.foi.data.entities.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventListSorterService {
    private Date convertStringToDate(String sDate){
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        Date convertedDate = new Date();
        try {
            return convertedDate = dateFormat.parse(sDate);
        }catch(ParseException e){
            return null;
        }
    }
    public void sortTheList(List<Event> eventList){
        Event eventHigh;
        Event eventLow;
        for(int i=0; i<eventList.size();i++){
            eventHigh = eventList.get(i);
            Date eventDateHigh = convertStringToDate(eventHigh.getDate());
            for(int j=0;j<i;j++){
                eventLow = eventList.get(j);
                Date eventDateLow = convertStringToDate(eventLow.getDate());
                if(eventDateLow.after(eventDateHigh)){
                    Collections.swap(eventList, i, j);
                }
            }
        }
    }
    public void attachYears(List<Event> targetedEventList){
        Calendar cal=Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        Date todayDate = convertStringToDate(getTodayDate());
        for(int i=0; i<targetedEventList.size(); i++){
            Event event = targetedEventList.get(i);
            Date eventDate = convertStringToDate(event.getDate()+"/"+currentYear);

            if(eventDate.before(todayDate)){
                int newCurrentYear = currentYear+1;
                targetedEventList.get(i).setDate(event.getDate()+"/"+newCurrentYear);
            }
            else{
                targetedEventList.get(i).setDate(event.getDate()+"/"+currentYear);
            }
        }
    }
    public String getTodayDate(){
        String tDate = "";
        Calendar cal = Calendar.getInstance();
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        int currentYear = cal.get(Calendar.YEAR);
        if(currentDay < 10){
            tDate = tDate + "0" + currentDay + "/";
        }
        else{
            tDate = tDate + currentDay + "/";
        }
        if(currentMonth < 10){
            tDate = tDate + "0" + currentMonth;
        }
        else{
            tDate = tDate + currentMonth;
        }
        tDate = tDate + "/" + currentYear;
        return tDate;
    }
}
