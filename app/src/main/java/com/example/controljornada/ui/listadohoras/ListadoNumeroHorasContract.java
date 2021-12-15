package com.example.controljornada.ui.listadohoras;

import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.BasePresenter;
import com.example.controljornada.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;

public interface ListadoNumeroHorasContract {



    interface View extends OnRepositoryListCallback{
        void showNoData();
        void showData(ArrayList<User> list);

        //muestra el orden A-Z
        void showDataOrder();
        //Ordena de la Z a la A
        void showDataInverseOrder();
    }


    interface Presenter extends BasePresenter {


        void load();

        void delete(User user);

        void undo(User user);


        void order();

    }


    interface Repository{

        void getList();

        void delete(User user);

    }


    interface OnInteractorListener extends OnRepositoryListCallback {
    }
}
