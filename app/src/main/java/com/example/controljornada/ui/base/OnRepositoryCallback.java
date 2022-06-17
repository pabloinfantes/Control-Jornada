package com.example.controljornada.ui.base;

/**
 * Esta clase es la encargada de dar la respuesta de los metodos del repository
 * @author pablo
 *
 */
public interface OnRepositoryCallback {
    void onSuccess(String message);
    void onFailure(String message);
}
