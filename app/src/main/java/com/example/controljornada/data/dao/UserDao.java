package com.example.controljornada.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controljornada.data.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    long insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT * FROM user")
    List<User> select();

    @Query("SELECT * FROM user WHERE id=:id")
    List<User> findById(int id);

}
