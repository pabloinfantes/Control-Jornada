package com.example.controljornada.ui.base;

import com.example.controljornada.data.model.User;

/**
 * Esta clase es la encargada de dar la respuesta de los metodos del repository
 * @author pablo
 *
 */
public interface ReadFromRoomCallback {

    void OnSuccessReadHorario(String message);
    void OnFailureReadHorario(String message);

    void OnSuccessReadUser(String message);
    void OnFailureReadUser(String message);
}
