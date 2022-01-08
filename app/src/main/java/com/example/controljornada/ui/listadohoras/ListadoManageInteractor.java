package com.example.controljornada.ui.listadohoras;

import android.text.TextUtils;

import com.example.controljornada.data.model.User;
import com.example.controljornada.data.repository.UserRepository;
import com.example.controljornada.ui.base.OnRepositoryCallback;

public class ListadoManageInteractor implements OnRepositoryCallback {

    ListadoManageContract.OnInteractorManageListener listener;
    OnRepositoryCallback callback;

    public ListadoManageInteractor(ListadoManageContract.OnInteractorManageListener listener) {
        this.listener = listener;
        callback = this;
    }

    public void add(User user) {
        if (TextUtils.isEmpty(user.getNombreCompleto())){
            listener.onNombreEmpty();
            return;
        }
        if (TextUtils.isEmpty(user.getNumeroHorasMensuales())){
            listener.onNumHorasEmpty();
            return;
        }
        if (TextUtils.isEmpty(user.getNombreCorto())){
            listener.onNombreCortoEmpty();
            return;
        }

        UserRepository.getInstance().add(user,callback);
    }

    public void edit(User user) {
        if (TextUtils.isEmpty(user.getNombreCompleto())){
            listener.onNombreEmpty();
            return;
        }
        if (TextUtils.isEmpty(user.getNumeroHorasMensuales())){
            listener.onNumHorasEmpty();
            return;
        }
        if (TextUtils.isEmpty(user.getNombreCorto())){
            listener.onNombreCortoEmpty();
            return;
        }

        UserRepository.getInstance().edit(user,callback);
    }



    @Override
    public void onSuccess(String message) {
        listener.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {listener.onFailure(message);
    }
}
