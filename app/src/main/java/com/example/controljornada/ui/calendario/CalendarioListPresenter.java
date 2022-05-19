package com.example.controljornada.ui.calendario;

import com.example.controljornada.data.model.Horario;

import java.util.ArrayList;
import java.util.List;

public class CalendarioListPresenter implements CalendarioListContract.Presenter, CalendarioListContract.OnInteractorListener{

    private CalendarioListContract.View view;
    private CalendarioListInteractor interactor;

    public CalendarioListPresenter(CalendarioListContract.View view) {
        this.view = view;
        this.interactor = new CalendarioListInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
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
            view.showData((ArrayList<Horario>) list);
    }

    @Override
    public void onDeleteSucces(String message) {
        //
    }

    @Override
    public void onUndoSuccess(String message) {
        //
    }

    @Override
    public void selectAdminUser(String fechaDelDiaDeTrabajo) {
        interactor.selectAdminUser(fechaDelDiaDeTrabajo);
    }

    @Override
    public void selectNormalUser(int iduser, String fechaDelDiaDeTrabajo) {
        interactor.selectNormalUser(iduser,fechaDelDiaDeTrabajo);
    }
}
