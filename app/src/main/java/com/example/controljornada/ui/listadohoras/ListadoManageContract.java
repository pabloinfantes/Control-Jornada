package com.example.controljornada.ui.listadohoras;

import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.BasePresenter;
import com.example.controljornada.ui.base.OnRepositoryCallback;

public interface ListadoManageContract {

    interface View extends OnRepositoryCallback {

        void setNombreEmpty();
        void setNumHorasEmpty();
        void setNombreCortoEmpty();

    }

    interface Presenter extends BasePresenter {
        void add(User user);
        void edit(User user);
    }

    interface OnInteractorManageListener extends OnRepositoryCallback {
        void onNombreEmpty();
        void onNumHorasEmpty();
        void onNombreCortoEmpty();
    }

    interface Repository{
        void add(User user ,OnRepositoryCallback callback);
        void edit(User user, OnRepositoryCallback callback);
    }
}
