package com.example.controljornada.ui.profile;

import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.BasePresenter;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.ReadFromUser;
/**
 * Esta clase es la encargada de obligar a implementar estos metodos
 * @author pablo
 *
 */
public interface UserContract {

    interface View extends OnRepositoryCallback, ReadFromUser {

    }


    interface Presenter extends BasePresenter {
        void add(User user);
        void edit(User user);
        void leer(String userEmail);

    }


    interface OnInteractorListener extends OnRepositoryCallback ,ReadFromUser{


    }

    interface Repository {
        void add(User user,OnRepositoryCallback callback);
        void edit(User user, OnRepositoryCallback callback);
        void leer(String userEmail, ReadFromUser callback);
    }
}
