package com.example.controljornada.ui.signup;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.controljornada.data.model.User;
import com.example.controljornada.data.repository.LoginRepositoryImpl;
import com.example.controljornada.data.repository.UserRepository;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.utils.CommonUtils;

/**
 * Esta clase es la encargada de pasar informacion del presenter segun la respuesta del repository
 * @author pablo
 *
 */
public class SignUpInteractor implements OnRepositoryCallback {

    private SignUpContract.OnSignUpInteractorListener listener;

    public SignUpInteractor(SignUpContract.OnSignUpInteractorListener listener) {
        this.listener = listener;

    }

    public void validateSignUp(String user, String email, String password, String comfirmPassword) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // A gestionar las alternativas del caso de uso
                if (TextUtils.isEmpty(user)){
                    listener.onUserEmptyError();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    listener.onPasswordEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(comfirmPassword)){
                    listener.onConfirmPasswordEmptyError();
                    return;
                }
                if (!CommonUtils.isPasswordValid(password)){
                    listener.onPasswordError();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    listener.onEmailEmptyError();
                    return;
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    listener.onEmailError();
                    return;
                }

                if (!password.equals(comfirmPassword)){
                    listener.onPasswordDontMatch();
                    return;
                }
                LoginRepositoryImpl.getInstance(SignUpInteractor.this).SignUp(user, email, password, comfirmPassword);


            }
        }, 2000);
    }


    @Override
    public void onSuccess(String message) {
        listener.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }


}
