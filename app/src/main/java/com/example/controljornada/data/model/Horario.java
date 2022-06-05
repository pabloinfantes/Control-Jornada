package com.example.controljornada.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;



public class Horario {

    public static final String TAG = "horario";


    private int id;


    private int iduser;


    private String emailUser;

    private String lugarTrabajoMñn;

    private String lugarTrabajoTarde;


    private String fechaDelDiaDeTrabajo;


    private String horarioEntradaMñn;

    private String horarioSalidaMñn;

    private String horarioEntradaTarde;

    private String horarioSalidaTarde;

    private int numeroHoras;

    private String motivoAusencia;

    public Horario(int iduser,  String emailUser, String lugarTrabajoMñn, String lugarTrabajoTarde,  String fechaDelDiaDeTrabajo, String horarioEntradaMñn, String horarioSalidaMñn, String horarioEntradaTarde, String horarioSalidaTarde, int numeroHoras, String motivoAusencia) {
        this.iduser = iduser;
        this.emailUser = emailUser;
        this.lugarTrabajoMñn = lugarTrabajoMñn;
        this.lugarTrabajoTarde = lugarTrabajoTarde;
        this.fechaDelDiaDeTrabajo = fechaDelDiaDeTrabajo;
        this.horarioEntradaMñn = horarioEntradaMñn;
        this.horarioSalidaMñn = horarioSalidaMñn;
        this.horarioEntradaTarde = horarioEntradaTarde;
        this.horarioSalidaTarde = horarioSalidaTarde;
        this.numeroHoras = numeroHoras;
        this.motivoAusencia = motivoAusencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }


    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser( String emailUser) {
        this.emailUser = emailUser;
    }

    public String getLugarTrabajoMñn() {
        return lugarTrabajoMñn;
    }

    public void setLugarTrabajoMñn(String lugarTrabajoMñn) {
        this.lugarTrabajoMñn = lugarTrabajoMñn;
    }

    public String getLugarTrabajoTarde() {
        return lugarTrabajoTarde;
    }

    public void setLugarTrabajoTarde(String lugarTrabajoTarde) {
        this.lugarTrabajoTarde = lugarTrabajoTarde;
    }


    public String getFechaDelDiaDeTrabajo() {
        return fechaDelDiaDeTrabajo;
    }

    public void setFechaDelDiaDeTrabajo( String fechaDelDiaDeTrabajo) {
        this.fechaDelDiaDeTrabajo = fechaDelDiaDeTrabajo;
    }

    public String getHorarioEntradaMñn() {
        return horarioEntradaMñn;
    }

    public void setHorarioEntradaMñn(String horarioEntradaMñn) {
        this.horarioEntradaMñn = horarioEntradaMñn;
    }

    public String getHorarioSalidaMñn() {
        return horarioSalidaMñn;
    }

    public void setHorarioSalidaMñn(String horarioSalidaMñn) {
        this.horarioSalidaMñn = horarioSalidaMñn;
    }

    public String getHorarioEntradaTarde() {
        return horarioEntradaTarde;
    }

    public void setHorarioEntradaTarde(String horarioEntradaTarde) {
        this.horarioEntradaTarde = horarioEntradaTarde;
    }

    public String getHorarioSalidaTarde() {
        return horarioSalidaTarde;
    }

    public void setHorarioSalidaTarde(String horarioSalidaTarde) {
        this.horarioSalidaTarde = horarioSalidaTarde;
    }

    public int getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(int numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    public String getMotivoAusencia() {
        return motivoAusencia;
    }

    public void setMotivoAusencia(String motivoAusencia) {
        this.motivoAusencia = motivoAusencia;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "id=" + id +
                ", iduser=" + iduser +
                ", emailUser='" + emailUser + '\'' +
                ", lugarTrabajoMñn='" + lugarTrabajoMñn + '\'' +
                ", lugarTrabajoTarde='" + lugarTrabajoTarde + '\'' +
                ", fechaDelDiaDeTrabajo='" + fechaDelDiaDeTrabajo + '\'' +
                ", horarioEntradaMñn='" + horarioEntradaMñn + '\'' +
                ", horarioSalidaMñn='" + horarioSalidaMñn + '\'' +
                ", horarioEntradaTarde='" + horarioEntradaTarde + '\'' +
                ", horarioSalidaTarde='" + horarioSalidaTarde + '\'' +
                ", numeroHoras=" + numeroHoras +
                ", motivoAusencia='" + motivoAusencia + '\'' +
                '}';
    }
}
