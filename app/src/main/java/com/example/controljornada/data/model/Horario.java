package com.example.controljornada.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Horario {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String horarioEntrada;
    @NonNull
    private String horarioMediodia;
    @NonNull
    private String horarioAlmuerzo;
    @NonNull
    private String horarioSalida;


    public Horario(String horarioEntrada, String horarioMediodia, String horarioAlmuerzo, String horarioSalida) {
        this.horarioEntrada = horarioEntrada;
        this.horarioMediodia = horarioMediodia;
        this.horarioAlmuerzo = horarioAlmuerzo;
        this.horarioSalida = horarioSalida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(String horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public String getHorarioMediodia() {
        return horarioMediodia;
    }

    public void setHorarioMediodia(String horarioMediodia) {
        this.horarioMediodia = horarioMediodia;
    }

    public String getHorarioAlmuerzo() {
        return horarioAlmuerzo;
    }

    public void setHorarioAlmuerzo(String horarioAlmuerzo) {
        this.horarioAlmuerzo = horarioAlmuerzo;
    }

    public String getHorarioSalida() {
        return horarioSalida;
    }

    public void setHorarioSalida(String horarioSalida) {
        this.horarioSalida = horarioSalida;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "horarioEntrada='" + horarioEntrada + '\'' +
                ", horarioMediodia='" + horarioMediodia + '\'' +
                ", horarioAlmuerzo='" + horarioAlmuerzo + '\'' +
                ", horarioSalida='" + horarioSalida + '\'' +
                '}';
    }
}
