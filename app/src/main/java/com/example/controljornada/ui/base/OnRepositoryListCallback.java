package com.example.controljornada.ui.base;

import java.util.List;

/**
 * Esta clase es la encargada de dar la respuesta de los metodos del repository
 * @author pablo
 *
 */
public interface OnRepositoryListCallback {

    void onFailure(String message);
    <T> void onSuccess(List<T> list);
    void onDeleteSucces(String message);
    void onUndoSuccess(String message);

}
