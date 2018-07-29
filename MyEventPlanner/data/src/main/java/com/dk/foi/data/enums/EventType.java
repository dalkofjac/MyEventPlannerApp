package com.dk.foi.data.enums;

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
