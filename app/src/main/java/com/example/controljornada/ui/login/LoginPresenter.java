package com.example.controljornada.ui.login;


import com.example.controljornada.data.model.User;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.OnInteractorListener {

    private LoginContract.View view;
    private LoginInteractor interactor;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        interactor = new LoginInteractor(this);
    }


    //region metodos del contrato presenter-interactor

    @Override
    public void validateCredentials(User user) {
        interactor.validateCredentials(user);
        view.showProgress();
    }

    @Override
    public void onEmailEmptyError() {
        view.hideProgress();
        view.setEmailEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgress();
        view.setPasswordEmptyError();
    }

    @Override
    public void onPasswordError() {
        view.hideProgress();
        view.setPasswordError();
    }


    @Override
    public void onSuccess(String message) {
        view.hideProgress();
        view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgress();
        view.onFailure(message);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }


    //endregion

}
