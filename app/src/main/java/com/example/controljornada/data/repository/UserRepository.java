package com.example.controljornada.data.repository;

import com.example.controljornada.data.ControlJornadaDatabase;
import com.example.controljornada.data.dao.UserDao;
import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.OnRepositoryListCallback;
import com.example.controljornada.ui.listadohoras.ListadoManageContract;
import com.example.controljornada.ui.listadohoras.ListadoNumeroHorasContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class UserRepository implements ListadoNumeroHorasContract.Repository, ListadoManageContract.Repository {


    private static UserRepository instance;
    private ArrayList<User> list;

    private UserDao userDao;


    private UserRepository(){
        list = new ArrayList<>();
        userDao = ControlJornadaDatabase.getDatabase().userDao();
    }



    public static UserRepository getInstance(){
        if (instance == null){
            instance = new UserRepository();
        }
        return instance;
    }



    @Override
    public void getList(OnRepositoryListCallback callback) {
        try {
            list = (ArrayList<User>) ControlJornadaDatabase.databaseWriteExecutor.submit(() -> userDao.select()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSuccess(list);
    }

    @Override
    public void delete(User user, OnRepositoryListCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(()-> userDao.delete(user));
        callback.onDeleteSucces("Se ha eliminado el usuario" +user.getNombreCompleto());
    }

    @Override
    public void undo(User user, OnRepositoryListCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(()-> userDao.insert(user));
        callback.onUndoSuccess("Se ha vuelto ha añadir este usuario");

    }




    @Override
    public void add(User user, OnRepositoryCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(()-> userDao.insert(user));
        callback.onSuccess("Se ha añadido correctamente");
    }

    @Override
    public void edit(User user, OnRepositoryCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(()-> userDao.update(user));
        callback.onSuccess("Se ha editado correctamente");
    }
}
