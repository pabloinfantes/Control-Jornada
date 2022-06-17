package com.example.controljornada.ui.profile;

import com.example.controljornada.data.model.User;
import com.example.controljornada.data.repository.UserRepository;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.ReadFromObras;
import com.example.controljornada.ui.base.ReadFromUser;

import java.util.ArrayList;
/**
 * Esta clase es la encargada de pasar informacion del presenter segun la respuesta del repository
 * @author pablo
 *
 */
public class ProfileFragmentInteractor implements OnRepositoryCallback, ReadFromUser {

    private UserContract.OnInteractorListener listener;
    private OnRepositoryCallback callback;
    private ReadFromUser readFromUser;

    public ProfileFragmentInteractor(UserContract.OnInteractorListener listener) {
        this.listener = listener;
        this.callback = this;
        this.readFromUser = this;
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

    public void leer(String userEmail) {
        UserRepository.getInstance().leer(userEmail,readFromUser);
    }

    @Override
    public void OnSuccessReadUser(User user) {
        listener.OnSuccessReadUser(user);
    }

    @Override
    public void OnFailureReadUser(String message) {
        listener.OnFailureReadUser(message);
    }
}
