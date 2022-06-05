package com.example.controljornada.ui.horario;

import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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
    public void add(Horario horario) throws ParseException {
        interactor.add(horario);
    }

    @Override
    public void add(User user) throws ExecutionException, InterruptedException {
        interactor.add(user);
    }

    @Override
    public void leer(Horario horario) {
        interactor.leer(horario);
    }

    @Override
    public void leer(User user) {
        interactor.leer(user);
    }

    @Override
    public void leerObras() {
        interactor.leerObras();
    }

    @Override
    public void editNumHora(User user) {
        interactor.editNumHora(user);
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
    public void onHora1AntesHora2() {
        view.setHora1AntesHora2();
    }

    @Override
    public void onHora3AntesHora4() {
        view.setHora3AntesHora4();
    }

    @Override
    public void OnSuccessReadHorario(String message) {
        view.OnSuccessReadHorario(message);
    }

    @Override
    public void OnFailureReadHorario(String message) {
        view.OnFailureReadHorario(message);
    }

    @Override
    public void OnSuccessReadUser(String message) {
        view.OnSuccessReadUser(message);
    }

    @Override
    public void OnFailureReadUser(String message) {
        view.OnFailureReadUser(message);
    }

    @Override
    public void OnSuccessReadObra(ArrayList<String> obras) {
        view.OnSuccessReadObra(obras);
    }

    @Override
    public void OnFailureReadObra(String message) {
        view.OnFailureReadObra(message);
    }
}
