package com.example.controljornada.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.Obra;


import java.util.List;

@Dao
public interface HorarioDao {
    @Insert
    long insert(Horario horario);

    @Update
    void update(Horario horario);

    @Delete
    void delete(Horario horario);

    @Query("DELETE FROM  horario")
    void deleteAll();

    @Query("SELECT * FROM  horario WHERE iduser = :iduser AND fechaDelDiaDeTrabajo = :fechaDelDiaDeTrabajo")
    List<Horario> selectNormalUser(int iduser, String fechaDelDiaDeTrabajo);

    @Query("SELECT * FROM  horario WHERE fechaDelDiaDeTrabajo = :fechaDelDiaDeTrabajo")
    List<Horario> selectAdminUser( String fechaDelDiaDeTrabajo);

    @Query("SELECT SUM(numeroHoras) FROM horario where fechaDelDiaDeTrabajo BETWEEN date('now','localtime','start of month') AND date('now','start of month','+1 month','-1 day') AND iduser = :iduser")
    int obtenerNumHorasAlMes(int iduser);

}
