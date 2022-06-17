package com.example.controljornada.ui.base;

import com.example.controljornada.data.model.User;


/**
 * Esta clase es la encargada de dar la respuesta de los metodos del repository
 * @author pablo
 *
 */
public interface ReadFromUser {

        void OnSuccessReadUser(User user);
        void OnFailureReadUser(String message);

}
