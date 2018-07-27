package com.dk.foi.myeventplanner.services;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class TemplateDataServiceTest {

    private TemplateDataService templateDataService;

    private ArrayList<Event> eventList;
    private EventType eventType;

    private int actualNumberOfEvents = 0;
    private int expectedNumberOfEvents = 0;

    private EventType actualEventType;
    private EventType expectedEventType;

    @Before
    public void setUp() {
        templateDataService = new TemplateDataService();
    }

    @Test
    public void generateTemplateData() throws Exception {
        eventList = new ArrayList<>();
        eventType = EventType.HOLIDAY;

        eventList = templateDataService.generateTemplateData(eventType);

        actualNumberOfEvents = eventList.size();
        expectedNumberOfEvents = 5;
        assertEquals("TemplateDataService returns unexpected number of events",
                expectedNumberOfEvents, actualNumberOfEvents);

        actualEventType = eventList.get(0).getType();
        expectedEventType = eventType;
        assertTrue("TemplateDataService returns unexpected type of events",
                actualEventType == expectedEventType);

        eventList = new ArrayList<>();
        eventType = EventType.BIRTHDAY;

        eventList = templateDataService.generateTemplateData(eventType);

        actualNumberOfEvents = eventList.size();
        expectedNumberOfEvents = 2;
        assertEquals("TemplateDataService returns unexpected number of events",
                expectedNumberOfEvents, actualNumberOfEvents);

        actualEventType = eventList.get(0).getType();
        expectedEventType = eventType;
        assertTrue("TemplateDataService returns unexpected type of events",
                actualEventType == expectedEventType);

        eventList = new ArrayList<>();
        eventType = EventType.OTHER;

        eventList = templateDataService.generateTemplateData(eventType);

        actualNumberOfEvents = eventList.size();
        expectedNumberOfEvents = 2;
        assertEquals("TemplateDataService returns unexpected number of events",
                expectedNumberOfEvents, actualNumberOfEvents);

        actualEventType = eventList.get(0).getType();
        expectedEventType = eventType;
        assertTrue("TemplateDataService returns unexpected type of events",
                actualEventType == expectedEventType);
    }
}
