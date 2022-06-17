package com.example.controljornada.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;



/**
 * Esta clase es el pojo user
 * @author pablo
 *
 */
public class User implements Serializable,Comparable {
    
    public static final String TAG = "user";


    private int id;

    private String email;
    private String nombre;
    private int admin;
    private String password;
    private String apellidos;
    private String genero;
    private String telefono;
    private String edad;
    private String numeroHorasMensuales;
    private String empresa;


    public User() {
    }


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id, String email, String nombre, int admin, String numeroHorasMensuales) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.admin = admin;
        this.numeroHorasMensuales = numeroHorasMensuales;
    }

    public User(int id, String email, String nombre, int admin) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
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
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getNumeroHorasMensuales() {
        return numeroHorasMensuales;
    }

    public void setNumeroHorasMensuales(String numeroHorasMensuales) {
        this.numeroHorasMensuales = numeroHorasMensuales;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", admin=" + admin +
                ", apellidos='" + apellidos + '\'' +
                ", genero='" + genero + '\'' +
                ", telefono='" + telefono + '\'' +
                ", edad=" + edad +
                ", numeroHorasMensuales='" + numeroHorasMensuales + '\'' +
                ", empresa='" + empresa + '\'' +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return ((User)obj).getNombre().equals(getNombre());
    }

    @Override
    public int compareTo(Object obj) {
        if (equals(obj))
            return ((User)obj).getNumeroHorasMensuales().compareTo(getNombre());
        else return ((User)obj).getNombre().compareTo(getNombre());
    }
}
