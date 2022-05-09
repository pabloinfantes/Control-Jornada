package com.example.controljornada.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controljornada.data.model.Obra;

import java.util.List;

@Dao
public interface ObraDao {

    @Insert
    long insert(Obra obra);

    @Update
    void update(Obra obra);

    @Delete
    void delete(Obra obra);

    @Query("DELETE FROM  obra")
    void deleteAll();

    @Query("SELECT * FROM  obra")
    List<Obra> select();

    @Query("SELECT * FROM  obra WHERE shortname=:shortname")
    Obra findByShortName(String shortname);
}
