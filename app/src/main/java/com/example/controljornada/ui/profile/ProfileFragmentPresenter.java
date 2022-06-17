package com.example.controljornada.ui.profile;

import com.example.controljornada.data.model.User;

/**
 * Esta clase es la encargada de pasar la informacion entre la vista y el interactor
 * @author pablo
 *
 */
public class ProfileFragmentPresenter implements UserContract.Presenter ,UserContract.OnInteractorListener{


    private UserContract.View view;
    private ProfileFragmentInteractor interactor;

    public ProfileFragmentPresenter(UserContract.View view) {
        this.view = view;
        this.interactor = new ProfileFragmentInteractor(this);
    }
    @Override
    public void onDestroy() {
        this.interactor = null;
        this.view = null;
    }

    @Override
    public void onSuccess(String message) {
        view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }

    @Override
    public void add(User user) {
        interactor.add(user);
    }

    @Override
    public void edit(User user) {
        interactor.edit(user);
    }

    @Override
    public void leer(String userEmail) {
        interactor.leer(userEmail);
    }

    @Override
    public void OnSuccessReadUser(User user) {
        view.OnSuccessReadUser(user);
    }

    @Override
    public void OnFailureReadUser(String message) {
        view.OnFailureReadUser(message);
    }
}
