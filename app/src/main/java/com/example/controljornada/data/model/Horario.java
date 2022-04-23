package com.example.controljornada.data.model;

public class Horario {
    String horarioEntrada;
    String horarioMediodia;
    String horarioAlmuerzo;
    String horarioSalida;

    public Horario(String horarioEntrada, String horarioMediodia, String horarioAlmuerzo, String horarioSalida) {
        this.horarioEntrada = horarioEntrada;
        this.horarioMediodia = horarioMediodia;
        this.horarioAlmuerzo = horarioAlmuerzo;
        this.horarioSalida = horarioSalida;
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
