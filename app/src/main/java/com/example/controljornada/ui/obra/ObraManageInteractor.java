package com.example.controljornada.ui.obra;

import android.text.TextUtils;

import com.example.controljornada.data.model.Obra;
import com.example.controljornada.data.repository.ObraRepository;
import com.example.controljornada.ui.base.OnRepositoryCallback;

public class ObraManageInteractor implements OnRepositoryCallback {

    ObraManageContract.OnInteractorManageListener listener;
    OnRepositoryCallback callback;

    public ObraManageInteractor(ObraManageContract.OnInteractorManageListener listener) {
        this.listener = listener;
        callback = this;
    }


    public void add(Obra obra){
        if (TextUtils.isEmpty(obra.getShortname())){
            listener.onShortNameEmpty();
            return;
        }
        if (TextUtils.isEmpty(obra.getName())){
            listener.onNameEmpty();
            return;
        }
        if (TextUtils.isEmpty(obra.getDescription())){
            listener.onDescriptionEmpty();
            return;
        }


        ObraRepository.getInstance().add(obra,callback);
    }


    public void edit(Obra obra){
        if (TextUtils.isEmpty(obra.getShortname())){
            listener.onShortNameEmpty();
            return;
        }
        if (TextUtils.isEmpty(obra.getName())){
            listener.onNameEmpty();
            return;
        }
        if (TextUtils.isEmpty(obra.getDescription())){
            listener.onDescriptionEmpty();
            return;
        }


        ObraRepository.getInstance().edit(obra,callback);
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
