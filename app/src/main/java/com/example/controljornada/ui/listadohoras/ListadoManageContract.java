package com.example.controljornada.ui.listadohoras;

import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.BasePresenter;
import com.example.controljornada.ui.base.OnRepositoryCallback;
/**
 * Esta clase es la encargada de obligar a implementar estos metodos
 * @author pablo
 *
 */
public interface ListadoManageContract {

    interface View extends OnRepositoryCallback {

        void setNombreEmpty();
        void setNumHorasEmpty();
        void setNombreCortoEmpty();
        void setEmailEmpty();
    }

    interface Presenter extends BasePresenter {
        void add(User user);
        void edit(User user);
    }

    interface OnInteractorManageListener extends OnRepositoryCallback {
        void onNombreEmpty();
        void onNumHorasEmpty();
        void onNombreCortoEmpty();
        void onEmailEmpty();
    }

    interface Repository{
        void add(User user ,OnRepositoryCallback callback);
        void edit(User user, OnRepositoryCallback callback);
    }
}
