package com.dk.foi.myeventplanner.helpers;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.entities.User;
import com.dk.foi.data.enums.EventType;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyJsonParserTest {

    private MyJsonParser parser;

    @Before
    public void setUp() {
        parser = new MyJsonParser();
    }

    @Test
    public void parseUserInfo() throws Exception {
        User user = new User(15, "TestName", "TestSurname", "user@testmail.com", "01/01/2018");
        String jsonUser = "{\"user\":[{\"id\":\"15\",\"name\":\"TestName\",\"surname\":\"TestSurname\",\"email\":\"user@testmail.com\",\"created\":\"01\\/01\\/2018\"}]}";

        assertNotNull("MyJsonParser returns null object data for User", parser.parseUserInfo(jsonUser));
        assertEquals("MyJsonParser fails to parse Users id", user.getId(), parser.parseUserInfo(jsonUser).getId());
        assertEquals("MyJsonParser fails to parse Users name", user.getName(), parser.parseUserInfo(jsonUser).getName());
        assertEquals("MyJsonParser fails to parse Users surname", user.getSurname(), parser.parseUserInfo(jsonUser).getSurname());
        assertEquals("MyJsonParser fails to parse Users email", user.getEmail(), parser.parseUserInfo(jsonUser).getEmail());
        assertEquals("MyJsonParser fails to parse Users created date", user.getCreated(), parser.parseUserInfo(jsonUser).getCreated());
    }

    @Test
    public void parseEventsInfo() throws Exception {
        List<Event> eventList = new ArrayList<>();
        Event event;

        event = new Event(1, EventType.OTHER, "TestEventOne", "10/05", "01/01/2018");
        eventList.add(event);

        event = new Event(2, EventType.OTHER, "TestEventTwo", "08/06", "02/01/2018");
        eventList.add(event);

        String jsonEventList = "{\"event\":[{\"id\":\"1\",\"type\":\"3\",\"name\":\"TestEventOne\",\"date\":\"10\\/05\",\"created\":\"01\\/01\\/2018\"}," +
                "{\"id\":\"2\",\"type\":\"3\",\"name\":\"TestEventTwo\",\"date\":\"08\\/06\",\"created\":\"02\\/01\\/2018\"}]}";

        assertNotNull("MyEventParser returns null object data for Event list", parser.parseEventsInfo(jsonEventList));

        int actualNumberOfEvents = parser.parseEventsInfo(jsonEventList).size();
        int expectedNumberOfEvents = eventList.size();
        assertEquals("MyEventParser returns wrong number of events in list", actualNumberOfEvents, expectedNumberOfEvents);

        String actualNameValue = parser.parseEventsInfo(jsonEventList).get(0).getName();
        String expectedNameValue = eventList.get(0).getName();
        assertEquals("MyEventParser fails to parse Events data (name)", actualNameValue, expectedNameValue);

    }


}
