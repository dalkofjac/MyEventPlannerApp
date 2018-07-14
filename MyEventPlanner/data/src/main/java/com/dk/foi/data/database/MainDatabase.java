package com.dk.foi.data.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = MainDatabase.NAME, version = MainDatabase.VERSION)
public class MainDatabase {
    public static final String NAME = "MyEventPlannerDatabase";
    public static final int VERSION = 1;
}
