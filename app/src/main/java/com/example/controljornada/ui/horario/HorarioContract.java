package com.example.controljornada.ui.horario;

import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.BasePresenter;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.OnRepositoryListCallback;

public interface HorarioContract
{

    interface View extends OnRepositoryCallback {

    }

    interface Presenter extends BasePresenter {
        void add(Horario horario);
        void add(User user);
    }

    interface OnInteractorManageListener extends OnRepositoryCallback{

    }

    interface Repository{
        void add(Horario horario ,OnRepositoryCallback callback);
        void add(User user, OnRepositoryCallback callback);
    }
}
