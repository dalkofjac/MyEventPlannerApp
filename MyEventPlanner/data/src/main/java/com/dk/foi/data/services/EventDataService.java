package com.dk.foi.data.services;

import com.dk.foi.data.entities.Event;
import com.dk.foi.data.entities.Event_Table;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.data.interfaces.DataServiceBase;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class EventDataService implements DataServiceBase{
    private EventType eventType;

    public EventDataService(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public List<Event> getAll() {
        return SQLite.select().from(Event.class)
                .where(Event_Table.type.eq(eventType)).queryList();
    }

    @Override
    public Event get(int id) {
        return SQLite.select().from(Event.class)
                .where(Event_Table.type.eq(eventType))
                .and(Event_Table.id.eq(id))
                .querySingle();
    }

    @Override
    public Event getByName(String name) {
        return SQLite.select().from(Event.class)
                .where(Event_Table.type.eq(eventType))
                .and(Event_Table.name.eq(name))
                .querySingle();
    }
}
