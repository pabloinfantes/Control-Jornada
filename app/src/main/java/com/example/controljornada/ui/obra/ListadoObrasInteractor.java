package com.example.controljornada.ui.obra;

import com.example.controljornada.data.model.Obra;
import com.example.controljornada.data.repository.ObraRepository;
import com.example.controljornada.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;
import java.util.List;
/**
 * Esta clase es la encargada de pasar informacion del presenter segun la respuesta del repository
 * @author pablo
 *
 */
public class ListadoObrasInteractor implements OnRepositoryListCallback {

    private ListadoObrasContract.OnInteractorListener listener;
    private OnRepositoryListCallback callback;

    public ListadoObrasInteractor(ListadoObrasContract.OnInteractorListener listener) {
        this.listener = listener;
        callback = this;
    }


    public void load(){
        ObraRepository.getInstance().getList(callback);
    }

    public void delete(Obra obra) {
        ObraRepository.getInstance().delete(obra,callback);
    }

    public void undo(Obra obra){
        ObraRepository.getInstance().undo(obra,callback);
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {
        listener.onSuccess((ArrayList<Obra>)list);
    }

    @Override
    public void onDeleteSucces(String message) {
        listener.onDeleteSucces(message);
    }

    @Override
    public void onUndoSuccess(String message) {
        listener.onUndoSuccess(message);
    }
}
