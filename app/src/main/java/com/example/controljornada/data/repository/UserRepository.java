package com.example.controljornada.data.repository;

import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.OnRepositoryListCallback;
import com.example.controljornada.ui.listadohoras.ListadoNumeroHorasContract;

import java.util.ArrayList;
import java.util.Collections;

public class UserRepository implements ListadoNumeroHorasContract.Repository {


    private OnRepositoryListCallback callback;
    private static UserRepository instance;
    private ArrayList<User> list;


    private UserRepository(){
        list = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        list.add(new User("Pablo Infantes Sosa","100"));
        list.add(new User("Rafael Rio Perez","101"));
        list.add(new User("Jose Miguel Godoy","108"));
        list.add(new User("Elena Guirado","107"));
        list.add(new User("Ricardo Merelo","103"));
        list.add(new User("Pepito Martinez","102"));

    }

    public static  UserRepository getInstance(OnRepositoryListCallback callback){
        if (instance == null){
            instance = new UserRepository();
        }
        instance.callback = callback;
        return instance;
    }

    @Override
    public void getList() {
        Collections.sort(list);
        callback.onSuccess(list);

    }

    @Override
    public void delete(User user) {
        list.remove(user);
        callback.onDeleteSucces("Se ha eliminado el usuario" +user.getNombreCompleto());
    }
}
