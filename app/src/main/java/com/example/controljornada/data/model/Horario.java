package com.example.controljornada.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity (tableName = Horario.TAG,
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "iduser",
                onDelete = ForeignKey.CASCADE))
public class Horario {

    public static final String TAG = "horario";

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private int iduser;

    @NonNull
    private String emailUser;
    @NonNull
    private String lugarTrabajoMñn;
    @NonNull
    private String lugarTrabajoTarde;
    @NonNull
    private String fechaDelDiaDeTrabajo;
    @NonNull
    private String horarioEntradaMñn;
    @NonNull
    private String horarioSalidaMñn;
    @NonNull
    private String horarioEntradaTarde;
    @NonNull
    private String horarioSalidaTarde;

    @NonNull
    private int numeroHoras;

    public Horario( int iduser, @NonNull String emailUser, @NonNull String lugarTrabajoMñn, @NonNull String lugarTrabajoTarde, @NonNull String fechaDelDiaDeTrabajo, @NonNull String horarioEntradaMñn, @NonNull String horarioSalidaMñn, @NonNull String horarioEntradaTarde, @NonNull String horarioSalidaTarde, int numeroHoras) {

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

    @NonNull
    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(@NonNull String emailUser) {
        this.emailUser = emailUser;
    }

    @NonNull
    public String getLugarTrabajoMñn() {
        return lugarTrabajoMñn;
    }

    public void setLugarTrabajoMñn(@NonNull String lugarTrabajoMñn) {
        this.lugarTrabajoMñn = lugarTrabajoMñn;
    }

    @NonNull
    public String getLugarTrabajoTarde() {
        return lugarTrabajoTarde;
    }

    public void setLugarTrabajoTarde(@NonNull String lugarTrabajoTarde) {
        this.lugarTrabajoTarde = lugarTrabajoTarde;
    }

    @NonNull
    public String getFechaDelDiaDeTrabajo() {
        return fechaDelDiaDeTrabajo;
    }

    public void setFechaDelDiaDeTrabajo(@NonNull String fechaDelDiaDeTrabajo) {
        this.fechaDelDiaDeTrabajo = fechaDelDiaDeTrabajo;
    }

    @NonNull
    public String getHorarioEntradaMñn() {
        return horarioEntradaMñn;
    }

    public void setHorarioEntradaMñn(@NonNull String horarioEntradaMñn) {
        this.horarioEntradaMñn = horarioEntradaMñn;
    }

    @NonNull
    public String getHorarioSalidaMñn() {
        return horarioSalidaMñn;
    }

    public void setHorarioSalidaMñn(@NonNull String horarioSalidaMñn) {
        this.horarioSalidaMñn = horarioSalidaMñn;
    }

    @NonNull
    public String getHorarioEntradaTarde() {
        return horarioEntradaTarde;
    }

    public void setHorarioEntradaTarde(@NonNull String horarioEntradaTarde) {
        this.horarioEntradaTarde = horarioEntradaTarde;
    }

    @NonNull
    public String getHorarioSalidaTarde() {
        return horarioSalidaTarde;
    }

    public void setHorarioSalidaTarde(@NonNull String horarioSalidaTarde) {
        this.horarioSalidaTarde = horarioSalidaTarde;
    }

    public int getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(int numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "iduser=" + iduser +
                ", emailUser='" + emailUser + '\'' +
                ", lugarTrabajoMñn='" + lugarTrabajoMñn + '\'' +
                ", lugarTrabajoTarde='" + lugarTrabajoTarde + '\'' +
                ", fechaDelDiaDeTrabajo='" + fechaDelDiaDeTrabajo + '\'' +
                ", horarioEntradaMñn='" + horarioEntradaMñn + '\'' +
                ", horarioSalidaMñn='" + horarioSalidaMñn + '\'' +
                ", horarioEntradaTarde='" + horarioEntradaTarde + '\'' +
                ", horarioSalidaTarde='" + horarioSalidaTarde + '\'' +
                ", numeroHoras=" + numeroHoras +
                '}';
    }
}
