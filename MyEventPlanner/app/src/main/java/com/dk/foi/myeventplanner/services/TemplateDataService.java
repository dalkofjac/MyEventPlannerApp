package com.dk.foi.myeventplanner.services;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TemplateDataService {

    public static ArrayList<Event> getHolidaysData() {
        ArrayList<Event> eventList = new ArrayList<>();
        Event event;
        EventType type = EventType.HOLIDAY;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        event = new Event(type, "Nova godina", "01/01", "00:00", "Global", true, timeStamp);
        eventList.add(event);
        event.save();

        event = new Event(type, "Sveta tri kralja", "06/01", "00:00", "Global", true, timeStamp);
        eventList.add(event);
        event.save();

        event = new Event(type, "Valentinovo", "14/02", "00:00", "Global", true, timeStamp);
        eventList.add(event);
        event.save();

        event = new Event(type, "Dan žena", "08/03", "00:00", "Global", true, timeStamp);
        eventList.add(event);
        event.save();

        event = new Event(type, "Praznik rada", "01/05", "00:00", "Global", true, timeStamp);
        eventList.add(event);
        event.save();

        event = new Event(type, "Noć vještica", "31/10", "00:00", "Global", true, timeStamp);
        eventList.add(event);
        event.save();

        event = new Event(type, "Dan svih svetih", "01/11", "00:00", "Global", true, timeStamp);
        eventList.add(event);
        event.save();

        event = new Event(type, "Božić", "25/12", "00:00", "Global", true, timeStamp);
        eventList.add(event);
        event.save();

        event = new Event(type, "Sveti Stjepan", "26/12", "00:00", "Global", true, timeStamp);
        eventList.add(event);
        event.save();

        return eventList;
    }
}
