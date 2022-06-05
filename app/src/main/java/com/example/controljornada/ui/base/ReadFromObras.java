package com.example.controljornada.ui.base;

import java.util.ArrayList;

public interface ReadFromObras {

    void OnSuccessReadObra(ArrayList<String> obras);
    void OnFailureReadObra(String message);
}
