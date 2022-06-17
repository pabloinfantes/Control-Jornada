package com.example.controljornada.ui.listadohoras;

import com.example.controljornada.data.model.User;

/**
 * Esta clase es la encargada de pasar la informacion entre la vista y el interactor
 * @author pablo
 *
 */
public class ListManagePresenter implements ListadoManageContract.Presenter ,ListadoManageContract.OnInteractorManageListener{

    private ListadoManageContract.View view;
    private ListadoManageInteractor interactor;


    public ListManagePresenter(ListadoManageContract.View view) {
        this.view = view;
        this.interactor = new ListadoManageInteractor(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void add(User user) {
        interactor.add(user);
    }

    @Override
    public void edit(User user) {
        interactor.edit(user);
    }



    @Override
    public void onSuccess(String message) {
        view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }

    @Override
    public void onNombreEmpty() {
        view.setNombreEmpty();
    }

    @Override
    public void onNumHorasEmpty() {
        view.setNumHorasEmpty();
    }

    @Override
    public void onNombreCortoEmpty() {
        view.setNombreCortoEmpty();
    }

    @Override
    public void onEmailEmpty() {
        view.setEmailEmpty();
    }
}
