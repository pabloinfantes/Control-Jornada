package com.example.controljornada.data.model;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable,Comparable {
    
    public static final String TAG = "user";
    private String nombre;
    private String apellidos;
    private String nombreCompleto;
    private String nombreCorto;
    private String genero;
    private String telefono;
    private String numeroHorasMensuales;
    private int edad;
    private String email;
    private String password;



    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String nombreCompleto, String numeroHorasMensuales , String nombreCorto) {
        this.nombreCompleto = nombreCompleto;
        this.numeroHorasMensuales = numeroHorasMensuales;
        this.nombreCorto = nombreCorto;
    }



    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
