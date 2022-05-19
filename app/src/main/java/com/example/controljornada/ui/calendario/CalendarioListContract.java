package com.example.controljornada.ui.calendario;

import com.example.controljornada.data.model.Horario;
import com.example.controljornada.ui.base.BasePresenter;
import com.example.controljornada.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;

public interface CalendarioListContract {


    interface View extends OnRepositoryListCallback {
        void showNoData();
        void showData(ArrayList<Horario> list);

    }

    interface Presenter extends BasePresenter {
        void selectAdminUser( String fechaDelDiaDeTrabajo);
        void selectNormalUser(int iduser, String fechaDelDiaDeTrabajo);


    }

    interface OnInteractorListener extends OnRepositoryListCallback {
    }


    interface Repository{
        void selectAdminUser(String fechaDelDiaDeTrabajo, OnRepositoryListCallback callback);
        void selectNormalUser(int iduser, String fechaDelDiaDeTrabajo, OnRepositoryListCallback callback);

    }

}
