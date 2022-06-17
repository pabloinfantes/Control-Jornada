package com.example.controljornada.ui.login;


import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.BasePresenter;
import com.example.controljornada.ui.base.IProgressView;
import com.example.controljornada.ui.base.OnRepositoryCallback;
/**
 * Esta clase es la encargada de obligar a implementar estos metodos
 * @author pablo
 *
 */
public interface LoginContract {

    interface View extends OnRepositoryCallback, IProgressView {

        void setEmailEmptyError();
        void setPasswordEmptyError();
        void setPasswordError();
    }


    interface Presenter extends BasePresenter {
        void validateCredentials(User user);
    }


    interface Repository {
        void login(User user);
    }


    interface OnInteractorListener extends OnRepositoryCallback {
        //Alternativa del caso de uso
        void onEmailEmptyError();
        void onPasswordEmptyError();
        void onPasswordError();
    }


}
