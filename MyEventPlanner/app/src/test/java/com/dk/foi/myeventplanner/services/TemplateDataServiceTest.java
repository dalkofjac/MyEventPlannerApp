package com.dk.foi.myeventplanner.services;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.enums.EventType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class TemplateDataServiceTest {

    private TemplateDataService templateDataService;

    @Before
    public void setUp() {
        templateDataService = new TemplateDataService();
    }

    @Test
    public void generateTemplateData() throws Exception {
        ArrayList<Event> eventList = new ArrayList<>();
        int actualNumberOfEvents = 0;
        int expectedNumberOfEvents = 0;

        eventList = templateDataService.generateTemplateData();

        assertNotNull("TemplateDataService returns null object", eventList);

        actualNumberOfEvents = eventList.size();
        expectedNumberOfEvents = 9;
        assertEquals("TemplateDataService returns unexpected number of events",
                expectedNumberOfEvents, actualNumberOfEvents);


    }
}
