package com.example.controljornada.ui.obra;

import com.example.controljornada.data.model.Obra;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es la encargada de pasar la informacion entre la vista y el interactor
 * @author pablo
 *
 */
public class ListadoObrasPresenter implements ListadoObrasContract.Presenter, ListadoObrasContract.OnInteractorListener {

    private ListadoObrasContract.View view;
    private ListadoObrasInteractor interactor;


    public ListadoObrasPresenter(ListadoObrasContract.View view) {
        this.view = view;
        this.interactor = new ListadoObrasInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor =null;
    }

    @Override
    public void load() {
        interactor.load();
    }

    @Override
    public void delete(Obra obra) {
        interactor.delete(obra);
    }

    @Override
    public void undo(Obra obra) {
        interactor.undo(obra);
    }

    @Override
    public void order() {
            view.showDataOrder();
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {
        if (list.size() == 0){
            view.showNoData();
        }else
            view.showData((ArrayList<Obra>) list);


    }

    @Override
    public void onDeleteSucces(String message) {
        view.onDeleteSucces(message);
    }

    @Override
    public void onUndoSuccess(String message) {
        view.onUndoSuccess(message);
    }
}
