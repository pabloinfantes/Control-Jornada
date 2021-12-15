package com.example.controljornada.ui.base;

import java.util.List;

public interface OnRepositoryListCallback {

    void onFailure(String message);
    <T> void onSuccess(List<T> list);
    void onDeleteSucces(String message);
    void onUndoSuccess(String message);

}
