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

    @Query("SELECT * FROM  horario")
    List<Horario> select();

}
