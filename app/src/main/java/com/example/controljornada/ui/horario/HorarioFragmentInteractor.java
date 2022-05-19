package com.example.controljornada.ui.horario;

import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.User;
import com.example.controljornada.data.repository.HorarioRepository;
import com.example.controljornada.data.repository.UserRepository;
import com.example.controljornada.ui.base.OnRepositoryCallback;

public class HorarioFragmentInteractor implements OnRepositoryCallback {

    private HorarioContract.OnInteractorManageListener listener;
    private OnRepositoryCallback callback;

    public HorarioFragmentInteractor(HorarioContract.OnInteractorManageListener listener) {
        this.listener = listener;
        this.callback = this;
    }

    public void add(Horario horario) {
        HorarioRepository.getInstance().add(horario,callback);
    }

    public void add(User user) {
        HorarioRepository.getInstance().add(user,callback);
    }

    @Override
    public void onSuccess(String message) {
        listener.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }
}
