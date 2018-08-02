package com.dk.foi.myeventplanner.services;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.myeventplanner.helpers.DateManager;

import java.util.ArrayList;

public class TemplateDataService {

    public TemplateDataService() { }

    /**
     * Method which returns Event List with template data for certain event type
     * Also, it saves newly created events to local database
     * @param type type of event that data has to represent
     * @return exit Event List with template data
     */
    public ArrayList<Event> getTemplateData(EventType type) {
        ArrayList<Event> eventList = generateTemplateData(type);

        for(int i=0; i<eventList.size(); i++) {
            Event event = eventList.get(i);
            event.save();
        }

        return eventList;
    }

    /**
     * Method generates template event data
     * @param type type of event that data has to represent
     * @return exit Event List with template data
     */
    public ArrayList<Event> generateTemplateData(EventType type) {
        ArrayList<Event> eventList = new ArrayList<>();
        Event event;
        String todayDate = DateManager.getTodayDate();

        if(type == EventType.HOLIDAY) {
            event = new Event(type, "New Years", "01/01", todayDate);
            eventList.add(event);

            event = new Event(type, "Valentine's day", "14/02", todayDate);
            eventList.add(event);

            event = new Event(type, "Labor Day", "01/05", todayDate);
            eventList.add(event);

            event = new Event(type, "Halloween", "31/10", todayDate);
            eventList.add(event);

            event = new Event(type, "Christmas", "25/12", todayDate);
            eventList.add(event);
        } else if(type == EventType.BIRTHDAY) {
            event = new Event(type, "Ronaldinho", "21/03", todayDate);
            eventList.add(event);

            event = new Event(type, "Leo Messi", "24/06", todayDate);
            eventList.add(event);
        } else if(type == EventType.OTHER) {
            event = new Event(type, "Summer Start", "21/06", todayDate);
            eventList.add(event);

            event = new Event(type, "Winter Start", "21/12", todayDate);
            eventList.add(event);
        }
        return eventList;
    }
}
