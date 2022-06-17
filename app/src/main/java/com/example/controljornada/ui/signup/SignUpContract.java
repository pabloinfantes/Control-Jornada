package com.example.controljornada.ui.signup;

import com.example.controljornada.ui.base.BasePresenter;
import com.example.controljornada.ui.base.IProgressView;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.login.LoginContract;
/**
 * Esta clase es la encargada de obligar a implementar estos metodos
 * @author pablo
 *
 */
public interface SignUpContract {

    interface View extends LoginContract.View {
        void setUserEmptyError();
        void setConfirmPasswordEmptyError();
        void setPasswordDontMatch();
        void setEmailError();
    }

    interface Presenter extends BasePresenter {
        void validateSignUp(String user,String email,String password,String comfirmPassword);

    }


    interface OnSignUpInteractorListener extends LoginContract.OnInteractorListener , OnRepositoryCallback {
        void onUserEmptyError();
        void onConfirmPasswordEmptyError();
        void onEmailError();
        void onPasswordDontMatch();
    }


    interface Repository{
        void SignUp(String user,String email,String password,String comfirmPassword);

    }






}
