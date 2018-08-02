package com.dk.foi.data.interfaces;

import com.dk.foi.data.entities.Event;

import java.util.List;

/**
 * Interface for standard event service (for local db operations)
 */
public interface DataServiceInterface {
    List<Event> getAll();
    Event get(int id);
    Event getByName(String name);
    boolean isEmpty();
}
