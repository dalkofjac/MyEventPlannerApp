package com.dk.foi.data.entities;

import com.dk.foi.data.database.MainDatabase;
import com.dk.foi.data.enums.EventType;
import com.dk.foi.data.interfaces.DatedEntity;
import com.dk.foi.data.interfaces.IdentifiableEntity;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Index;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.List;

@Table(database = MainDatabase.class)
public class Event extends BaseModel implements IdentifiableEntity, DatedEntity {
    @PrimaryKey(autoincrement=true)
    @Column
    @NotNull
    int id;

    @Column
    @NotNull
    EventType type;

    @Column
    @NotNull
    @Index
    String name;

    @Column
    @NotNull
    String date;

    @Column
    String time;

    @Column
    String location;

    @Column
    @NotNull
    boolean reschedulable;

    @Column
    @NotNull
    String created;

    // Constructors
    public Event() {
    }
    public Event(EventType type, String name, String date, String time, String location, boolean reschedulable, String created) {
        this.type = type;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.reschedulable = reschedulable;
        this.created = created;
    }

    public Event(int id, EventType type, String name, String date, String time, String location, boolean reschedulable, String created) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.reschedulable = reschedulable;
        this.created = created;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isReschedulable() {
        return reschedulable;
    }

    public void setReschedulable(boolean reschedulable) {
        this.reschedulable = reschedulable;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
