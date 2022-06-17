package com.example.controljornada.ui.base;

import java.util.ArrayList;

/**
 * Esta clase es la encargada de dar la respuesta de los metodos del repository
 * @author pablo
 *
 */
public interface ReadFromObras {

    void OnSuccessReadObra(ArrayList<String> obras);
    void OnFailureReadObra(String message);
}
