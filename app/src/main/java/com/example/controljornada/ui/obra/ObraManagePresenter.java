package com.example.controljornada.ui.obra;


import com.example.controljornada.data.model.Obra;

/**
 * Esta clase es la encargada de pasar la informacion entre la vista y el interactor
 * @author pablo
 *
 */
public class ObraManagePresenter implements ObraManageContract.Presenter , ObraManageContract.OnInteractorManageListener{

     private ObraManageContract.View view;
     private ObraManageInteractor interactor;


    public ObraManagePresenter(ObraManageContract.View view) {
        this.view = view;
        this.interactor = new ObraManageInteractor(this);
    }


    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void add(Obra obra) {
        interactor.add(obra);
    }

    @Override
    public void edit(Obra obra) {
        interactor.edit(obra);
    }



    @Override
    public void onShortNameEmpty() {
        view.setShortNameEmpty();
    }

    @Override
    public void onNameEmpty() {
        view.setNameEmpty();
    }

    @Override
    public void onDescriptionEmpty() {
        view.setDescriptionEmpty();
    }



    @Override
    public void onSuccess(String message) {
        view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }
}
