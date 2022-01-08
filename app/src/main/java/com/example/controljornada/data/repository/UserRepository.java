package com.example.controljornada.data.repository;

import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.OnRepositoryListCallback;
import com.example.controljornada.ui.listadohoras.ListadoManageContract;
import com.example.controljornada.ui.listadohoras.ListadoNumeroHorasContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class UserRepository implements ListadoNumeroHorasContract.Repository, ListadoManageContract.Repository {


    private static UserRepository instance;
    private ArrayList<User> list;


    private UserRepository(){
        list = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        list.add(new User("Pablo Infantes Sosa","100","PIS"));
        list.add(new User("Rafael Rio Perez","101","RRP"));
        list.add(new User("Jose Miguel Godoy","108","JSG"));
        list.add(new User("Elena Guirado","107","EG"));
        list.add(new User("Ricardo Merelo","103","RM"));
        list.add(new User("Pepito Martinez","102","PM"));

    }

    public static UserRepository getInstance(){
        if (instance == null){
            instance = new UserRepository();
        }
        return instance;
    }



    @Override
    public void getList(OnRepositoryListCallback callback) {
        Collections.sort(list);
        callback.onSuccess(list);
    }

    @Override
    public void delete(User user, OnRepositoryListCallback callback) {
        list.remove(user);
        callback.onDeleteSucces("Se ha eliminado el usuario" +user.getNombreCompleto());
    }

    @Override
    public void undo(User user, OnRepositoryListCallback callback) {
            list.add(user);
            callback.onUndoSuccess("Se ha vuelto ha añadir este usuario");

    }


    @Override
    public void add(User user, OnRepositoryCallback callback) {
        for(User user1 : list){
            if (user.getNombreCorto().equals(user1.getNombreCorto())){
                callback.onFailure("Error en la funcion de añadir");
                return;

            }

        }
        list.add(user);
        callback.onSuccess("Se ha añadido correctamente");
    }

    @Override
    public void edit(User user, OnRepositoryCallback callback) {
        for(User user1 : list){
            if (user.getNombreCorto().toString().equals(user1.getNombreCorto())){
                user1.setNombreCompleto(user.getNombreCompleto().toString());
                user1.setNumeroHorasMensuales(user.getNumeroHorasMensuales().toString());
                callback.onSuccess("Se ha editado correctamente");
                return;

            }
        }
        callback.onFailure("Error al editar");
    }
}
