package com.dk.foi.data.enums;

/**
 * Enum for establishing which event type is certain event
 */
public enum EventType {
    HOLIDAY,
    BIRTHDAY,
    OTHER,
    PERSONAL;

    public static EventType fromInteger(int x) {
        switch(x) {
            case 0:
                return HOLIDAY;
            case 1:
                return BIRTHDAY;
            case 2:
                return OTHER;
            case 3:
                return PERSONAL;
        }
        return null;
    }
}
