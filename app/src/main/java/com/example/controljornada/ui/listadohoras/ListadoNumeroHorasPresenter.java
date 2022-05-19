package com.example.controljornada.ui.listadohoras;

import com.example.controljornada.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class ListadoNumeroHorasPresenter implements ListadoNumeroHorasContract.Presenter, ListadoNumeroHorasContract.OnInteractorListener {

    private ListadoNumeroHorasContract.View view;
    private ListadoNumeroHorasInteractor interactor;
    private Boolean order=false;


    public ListadoNumeroHorasPresenter(ListadoNumeroHorasContract.View view) {
        this.view = view;
        this.interactor = new ListadoNumeroHorasInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor =null;
    }

    @Override
    public void load() {
        interactor.load();
    }

    @Override
    public void delete(User user) {
        interactor.delete(user);
    }

    @Override
    public void undo(User user) {
        interactor.undo(user);
    }


    @Override
    public void order() {
        view.showDataOrder();
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }

    @Override
    public <T> void onSuccess(List<T> list) {
        if (list.size() == 0){
            view.showNoData();
        }else
            view.showData((ArrayList<User>) list);


    }

    @Override
    public void onDeleteSucces(String message) {
        view.onDeleteSucces(message);
    }

    @Override
    public void onUndoSuccess(String message) {
        view.onUndoSuccess(message);
    }
}
