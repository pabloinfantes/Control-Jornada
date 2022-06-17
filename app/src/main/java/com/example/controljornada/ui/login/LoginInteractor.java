package com.example.controljornada.ui.login;

import android.os.Handler;
import android.text.TextUtils;

import com.example.controljornada.data.model.User;
import com.example.controljornada.data.repository.LoginRepositoryImpl;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.utils.CommonUtils;

/**
 * Esta clase es la encargada de pasar informacion del presenter segun la respuesta del repository
 * @author pablo
 *
 */
public class LoginInteractor implements OnRepositoryCallback {

    private LoginContract.Repository repository;
    private LoginContract.OnInteractorListener listener;

    //El presentar que quiera utilizar esta clase debe implementar esta interfaz
    public LoginInteractor(LoginContract.OnInteractorListener listener) {
        this.listener = listener;
        //this.repository = LoginRepositoryImpl.getInstance(this);
    }

    public void validateCredentials(User user){
        //En base a lo que suceda avisara a su Listener//Presentador
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // A gestionar las alternativas del caso de uso
                if (TextUtils.isEmpty(user.getEmail())){
                    listener.onEmailEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(user.getPassword())){
                    listener.onPasswordEmptyError();
                    return;
                }
                if (!CommonUtils.isPasswordValid(user.getPassword())){
                    listener.onPasswordError();
                    return;
                }
                LoginRepositoryImpl.getInstance(LoginInteractor.this).login(user);


            }
        }, 2000);
    }


    //Estos metodos vienen de la respuesta que nos da el Repositorio
    @Override
    public void onSuccess(String message) {
        listener.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }
}
