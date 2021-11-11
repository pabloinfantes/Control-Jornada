package com.example.controljornada.data.model;

public class Ausencia {
    String motivoAusencia;

    public Ausencia(String motivoAusencia) {
        this.motivoAusencia = motivoAusencia;
    }

    public String getMotivoAusencia() {
        return motivoAusencia;
    }

    public void setMotivoAusencia(String motivoAusencia) {
        this.motivoAusencia = motivoAusencia;
    }

    @Override
    public String toString() {
        return "Ausencia{" +
                "motivoAusencia='" + motivoAusencia + '\'' +
                '}';
    }
}
