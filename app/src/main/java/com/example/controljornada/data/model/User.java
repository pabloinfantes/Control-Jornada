package com.example.controljornada.data.model;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable,Comparable {
    String nombre;
    String apellidos;
    String nombreCompleto;
    String genero;
    String telefono;
    int edad;
    String numeroHorasMensuales;

    public User(String nombreCompleto, String numeroHorasMensuales) {
        this.nombreCompleto = nombreCompleto;
        this.numeroHorasMensuales = numeroHorasMensuales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumeroHorasMensuales() {
        return numeroHorasMensuales;
    }

    public void setNumeroHorasMensuales(String numeroHorasMensuales) {
        this.numeroHorasMensuales = numeroHorasMensuales;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return ((User)obj).getNombreCompleto().equals(getNombreCompleto());
    }

    @Override
    public int compareTo(Object obj) {
        if (equals(obj))
            return ((User)obj).getNumeroHorasMensuales().compareTo(getNombreCompleto());
        else return ((User)obj).getNombreCompleto().compareTo(getNombreCompleto());
    }
}
