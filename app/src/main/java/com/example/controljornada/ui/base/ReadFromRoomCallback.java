package com.example.controljornada.ui.base;

import com.example.controljornada.data.model.User;

public interface ReadFromRoomCallback {

    void OnSuccessReadHorario(String message);
    void OnFailureReadHorario(String message);

    void OnSuccessReadUser(String message);
    void OnFailureReadUser(String message);
}
