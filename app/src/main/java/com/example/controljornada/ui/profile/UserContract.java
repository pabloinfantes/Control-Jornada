package com.example.controljornada.ui.profile;

import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.BasePresenter;
import com.example.controljornada.ui.base.OnRepositoryCallback;

public interface UserContract {

    interface View extends OnRepositoryCallback {

    }


    interface Presenter extends BasePresenter {
        void add(User user);
        void edit(User user);
    }


    interface OnInteractorListener extends OnRepositoryCallback {


    }

    interface Repository {
        void add(User user,OnRepositoryCallback callback);
        void edit(User user, OnRepositoryCallback callback);
    }
}
