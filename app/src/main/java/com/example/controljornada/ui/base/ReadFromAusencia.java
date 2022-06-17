package com.example.controljornada.ui.base;

import com.example.controljornada.data.model.Horario;


/**
 * Esta clase es la encargada de dar la respuesta de los metodos del repository
 * @author pablo
 *
 */
public interface ReadFromAusencia {

    void OnSuccessReadAusencia(Horario horario);
    void OnFailureReadAusencia(String message);
}
