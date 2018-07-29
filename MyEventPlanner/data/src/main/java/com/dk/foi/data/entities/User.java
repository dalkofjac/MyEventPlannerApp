package com.dk.foi.data.entities;

import com.dk.foi.data.interfaces.DatedEntity;
import com.dk.foi.data.interfaces.IdentifiableEntity;

public class User implements IdentifiableEntity, DatedEntity {
    int id;
    String name;
    String surname;
    String email;
    String created;

    public User() {
    }

    public User(int id, String name, String surname, String email, String created) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.created = created;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getCreated() {
        return created;
    }

    @Override
    public void setCreated(String created) {
        this.created = created;
    }
}
