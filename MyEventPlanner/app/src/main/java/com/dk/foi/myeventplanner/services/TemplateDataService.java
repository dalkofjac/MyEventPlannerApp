package com.dk.foi.myeventplanner.services;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.helpers.DateManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TemplateDataService {

    public static ArrayList<Event> getHolidaysData() {
        ArrayList<Event> eventList = new ArrayList<>();
        Event event;
        EventType type = EventType.HOLIDAY;
        String todayDate = DateManager.getTodayDate();

        event = new Event(type, "Nova godina", "01/01", todayDate);
        eventList.add(event);
        event.save();

        event = new Event(type, "Sveta tri kralja", "06/01", todayDate);
        eventList.add(event);
        event.save();

        event = new Event(type, "Valentinovo", "14/02", todayDate);
        eventList.add(event);
        event.save();

        event = new Event(type, "Dan žena", "08/03", todayDate);
        eventList.add(event);
        event.save();

        event = new Event(type, "Praznik rada", "01/05", todayDate);
        eventList.add(event);
        event.save();

        event = new Event(type, "Noć vještica", "31/10", todayDate);
        eventList.add(event);
        event.save();

        event = new Event(type, "Dan svih svetih", "01/11", todayDate);
        eventList.add(event);
        event.save();

        event = new Event(type, "Božić", "25/12", todayDate);
        eventList.add(event);
        event.save();

        event = new Event(type, "Sveti Stjepan", "26/12", todayDate);
        eventList.add(event);
        event.save();

        return eventList;
    }
}
