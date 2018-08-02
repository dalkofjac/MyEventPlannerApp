package com.dk.foi.data.interfaces;

/**
 * Entity implementing this interface has to have created date getter and setter
 */
public interface DatedEntity {
    String getCreated();
    void setCreated(String created);
}
