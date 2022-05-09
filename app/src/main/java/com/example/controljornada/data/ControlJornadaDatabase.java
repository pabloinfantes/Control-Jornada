package com.example.controljornada.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.controljornada.data.dao.ObraDao;
import com.example.controljornada.data.dao.UserDao;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.data.model.User;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//1.- Definir la configuracion de la base de datos
@Database(entities = {User.class, Obra.class}, version = 1)
public abstract class ControlJornadaDatabase extends androidx.room.RoomDatabase {


    //2.- Crear los metodos de obtencion de los DAO.
    public abstract UserDao userDao();
    public abstract ObraDao obraDao();

    private static volatile ControlJornadaDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static ControlJornadaDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ControlJornadaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ControlJornadaDatabase.class, "jornada")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

  
    public static void create(final Context context) {
        if (INSTANCE == null) {
            synchronized (ControlJornadaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ControlJornadaDatabase.class, "jornada")
                            .build();
                }
            }
        }
    }

    public static ControlJornadaDatabase getDatabase() {
        return INSTANCE;

    }
}
