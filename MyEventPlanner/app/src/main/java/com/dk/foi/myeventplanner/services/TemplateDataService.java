package com.dk.foi.myeventplanner.services;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.helpers.DateManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TemplateDataService {

    public static ArrayList<Event> getTemplateData(EventType type) {
        ArrayList<Event> eventList = new ArrayList<>();
        Event event;
        String todayDate = DateManager.getTodayDate();

        if(type == EventType.HOLIDAY) {
            event = new Event(type, "Nova godina", "01/01", todayDate);
            eventList.add(event);
            event.save();

            event = new Event(type, "Valentinovo", "14/02", todayDate);
            eventList.add(event);
            event.save();

            event = new Event(type, "Praznik rada", "01/05", todayDate);
            eventList.add(event);
            event.save();

            event = new Event(type, "Noć vještica", "31/10", todayDate);
            eventList.add(event);
            event.save();

            event = new Event(type, "Božić", "25/12", todayDate);
            eventList.add(event);
            event.save();
        } else if(type == EventType.BIRTHDAY) {
            event = new Event(type, "Ronaldinho", "21/03", todayDate);
            eventList.add(event);
            event.save();

            event = new Event(type, "Leo Messi", "24/06", todayDate);
            eventList.add(event);
            event.save();
        } else if(type == EventType.OTHER) {
            event = new Event(type, "Other event one", "30/12", todayDate);
            eventList.add(event);
            event.save();

            event = new Event(type, "Other event two", "05/08", todayDate);
            eventList.add(event);
            event.save();
        }
        return eventList;
    }
}
