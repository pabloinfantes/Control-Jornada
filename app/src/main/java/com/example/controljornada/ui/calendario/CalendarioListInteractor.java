package com.example.controljornada.ui.calendario;

import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.repository.HorarioRepository;
import com.example.controljornada.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;
import java.util.List;

public class CalendarioListInteractor implements OnRepositoryListCallback {

    private CalendarioListContract.OnInteractorListener listener;
    private OnRepositoryListCallback callback;

    public CalendarioListInteractor(CalendarioListContract.OnInteractorListener listener) {
        this.listener = listener;
        this.callback = this;
    }

    public void selectAdminUser(String fechaDelDiaDeTrabajo) {
        HorarioRepository.getInstance().selectAdminUser(fechaDelDiaDeTrabajo,callback);
    }

    public void selectNormalUser(String emailUser, String fechaDelDiaDeTrabajo) {
        HorarioRepository.getInstance().selectNormalUser(emailUser,fechaDelDiaDeTrabajo,callback);
    }


    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }

    @Override
    public <T> void onSuccess(List<T> list) {
        listener.onSuccess((ArrayList<Horario>) list);
    }

    @Override
    public void onDeleteSucces(String message) {
        //
    }

    @Override
    public void onUndoSuccess(String message) {
        //
    }
}

