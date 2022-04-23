package com.example.controljornada.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;
@Entity
public class User implements Serializable,Comparable {
    
    public static final String TAG = "user";

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String nombreCompleto;
    @NonNull
    private String numeroHorasMensuales;
    @NonNull
    private String nombreCorto;


    private String nombre;
    private String apellidos;
    private String genero;
    private String telefono;
    private int edad;
    private String email;
    private String password;


    @Ignore
    public User() {
    }

    @Ignore
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id,String nombreCompleto, String numeroHorasMensuales , String nombreCorto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.numeroHorasMensuales = numeroHorasMensuales;
        this.nombreCorto = nombreCorto;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }


    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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
