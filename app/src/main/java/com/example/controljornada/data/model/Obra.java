package com.example.controljornada.data.model;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Obra implements Comparable , Serializable {

    public static final String TAG = "obra";
    String name;
    String shortname;
    String description;


    public Obra() {
    }

    public Obra(String name, String shortname, String description) {
        this.name = name;
        this.shortname = shortname;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Obra{" +
                "name='" + name + '\'' +
                ", shortname='" + shortname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return ((Obra)obj).getName().equals(getName());
    }


    @Override
    public int compareTo(Object obj) {

        if (equals(obj))
            return ((Obra)obj).getDescription().compareTo(getName());
        else
            return ((Obra)obj).getName().compareTo(getName());
    }
}
