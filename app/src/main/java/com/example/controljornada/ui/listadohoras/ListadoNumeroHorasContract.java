package com.example.controljornada.ui.listadohoras;

import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.BasePresenter;
import com.example.controljornada.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;

public interface ListadoNumeroHorasContract {



    interface View extends OnRepositoryListCallback{
        void showNoData();
        void showData(ArrayList<User> list);
        void showDataOrder();

    }


    interface Presenter extends BasePresenter {

        void load();
        void delete(User user);
        void undo(User user);
        void order();

    }

    interface OnInteractorListener extends OnRepositoryListCallback {
    }

    interface Repository{

        void getList(OnRepositoryListCallback callback);
        void delete(User user, OnRepositoryListCallback callback);
        void undo(User user, OnRepositoryListCallback callback);



    }



}
