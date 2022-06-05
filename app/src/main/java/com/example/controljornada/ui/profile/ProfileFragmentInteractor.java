package com.example.controljornada.ui.profile;

import com.example.controljornada.data.model.User;
import com.example.controljornada.data.repository.UserRepository;
import com.example.controljornada.ui.base.OnRepositoryCallback;

public class ProfileFragmentInteractor implements OnRepositoryCallback {

    private UserContract.OnInteractorListener listener;
    private OnRepositoryCallback callback;

    public ProfileFragmentInteractor(UserContract.OnInteractorListener listener) {
        this.listener = listener;
        this.callback = this;
    }

    public void add(User user) {
        UserRepository.getInstance().add(user,callback);
    }

    public void edit(User user) {
        UserRepository.getInstance().edit(user,callback);
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
