package com.example.controljornada.ui.horario;

import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.User;

public class HorarioFragmentPresenter implements HorarioContract.Presenter, HorarioContract.OnInteractorManageListener {

    private HorarioContract.View view;
    private HorarioFragmentInteractor interactor;

    public HorarioFragmentPresenter(HorarioContract.View view) {
        this.view = view;
        this.interactor = new HorarioFragmentInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.interactor = null;
        this.view = null;
    }

    @Override
    public void add(Horario horario) {
        interactor.add(horario);
    }

    @Override
    public void add(User user) {
        interactor.add(user);
    }


    @Override
    public void onSuccess(String message) {
        view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }
}
