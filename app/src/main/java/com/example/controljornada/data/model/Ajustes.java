package com.example.controljornada.data.model;

import java.io.Serializable;
import java.util.Objects;

public class Ajustes {
    String nombre;

    public Ajustes(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ajustes ajustes = (Ajustes) o;
        return Objects.equals(nombre, ajustes.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "Ajustes{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
