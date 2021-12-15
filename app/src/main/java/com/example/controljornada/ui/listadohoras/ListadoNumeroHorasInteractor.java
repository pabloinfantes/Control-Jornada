package com.example.controljornada.ui.listadohoras;

import com.example.controljornada.data.model.User;
import com.example.controljornada.data.repository.UserRepository;
import com.example.controljornada.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;
import java.util.List;

public class ListadoNumeroHorasInteractor implements OnRepositoryListCallback {

    private ListadoNumeroHorasContract.OnInteractorListener listener;

    public ListadoNumeroHorasInteractor(ListadoNumeroHorasContract.OnInteractorListener listener) {
        this.listener = listener;
    }


    public void load(){
        UserRepository.getInstance(this).getList();
    }

    public void delete(User user) {
        UserRepository.getInstance(this).delete(user);
    }



    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {
        listener.onSuccess((ArrayList<User>)list);
    }

    @Override
    public void onDeleteSucces(String message) {
        listener.onDeleteSucces(message);
    }

    @Override
    public void onUndoSuccess(String message) {

    }
}
