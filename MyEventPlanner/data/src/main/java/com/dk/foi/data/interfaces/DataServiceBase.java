package com.dk.foi.data.interfaces;

import com.dk.foi.data.entities.Event;

import java.util.List;

public interface DataServiceBase {
    List<Event> getAll();
    Event get(int id);
    Event getByName(String name);
    boolean isEmpty();
}
