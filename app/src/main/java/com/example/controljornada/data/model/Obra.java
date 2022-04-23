package com.example.controljornada.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Obra implements Comparable , Serializable {
    public static final String TAG = "obra";


    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;
    @NonNull
    private String shortname;

    private String description;

    @Ignore
    public Obra() {
    }


    public Obra(int id, String name, String shortname, String description) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
            return ((Obra)obj).getDescription().compareTo(getDescription());
        else
            return ((Obra)obj).getName().compareTo(getName());

    }
}
