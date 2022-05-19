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

    @PrimaryKey(autoGenerate = false)
    private int id;
    @NonNull
    private String email;
    @NonNull
    private String nombreCompleto;
    @NonNull
    private int admin;


    private String nombreCorto;
    private String nombre;
    private String apellidos;
    private String genero;
    private String telefono;
    private int edad;
    private String password;
    private String numeroHorasMensuales;

    @Ignore
    public User() {
    }

    @Ignore
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public User(int id,String email, String nombreCompleto, int admin) {
        this.id = id;
        this.email = email;
        this.nombreCompleto = nombreCompleto;
        this.admin = admin;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(@NonNull String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumeroHorasMensuales() {
        return numeroHorasMensuales;
    }

    public void setNumeroHorasMensuales(String numeroHorasMensuales) {
        this.numeroHorasMensuales = numeroHorasMensuales;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", admin=" + admin +
                ", nombreCorto='" + nombreCorto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", genero='" + genero + '\'' +
                ", telefono='" + telefono + '\'' +
                ", edad=" + edad +
                ", password='" + password + '\'' +
                ", numeroHorasMensuales='" + numeroHorasMensuales + '\'' +
                '}';
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
