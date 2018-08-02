package com.dk.foi.myeventplanner.services;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class EventListSorterServiceTest {

    private EventListSorterService service;

    private Event eventOne;
    private Event eventTwo;
    private List<Event> eventList;

    private int actualNumberOfEvents;
    private int expectedNumberOfEvents;

    @Before
    public void setUp() {
        service = new EventListSorterService();
    }

    @Test
    public void sortTheList() throws Exception {
        eventOne = new Event(EventType.HOLIDAY, "Example event one", "01/01/2018", "16/12/2018");
        eventTwo = new Event(EventType.HOLIDAY, "Example event two", "02/01/2018", "18/10/2018");

        eventList = new ArrayList<>();
        eventList.add(eventOne);
        eventList.add(eventTwo);

        List<Event> sortedEventList;
        sortedEventList = service.sortTheList(eventList);

        assertNotNull("Event list sorter returns null object", sortedEventList);

        actualNumberOfEvents = eventList.size();
        expectedNumberOfEvents = sortedEventList.size();

        assertEquals("Event list sorter returned wrong number of list objects", actualNumberOfEvents, expectedNumberOfEvents);
    }

    @Test
    public void attachYears() throws Exception {
        eventOne = new Event(EventType.HOLIDAY, "Example event one", "18/01", "13/12/2018");
        eventTwo = new Event(EventType.HOLIDAY, "Example event two", "20/01", "18/12/2018");

        eventList = new ArrayList<>();
        eventList.add(eventOne);
        eventList.add(eventTwo);

        List<Event> retrievedEventList;
        retrievedEventList = service.attachYears(eventList);

        assertNotNull("Attaching years onto a event list returned null object", retrievedEventList);

        actualNumberOfEvents = eventList.size();
        expectedNumberOfEvents = retrievedEventList.size();

        assertEquals("Event list sorter returned wrong number of list objects", expectedNumberOfEvents, actualNumberOfEvents);
    }
}
