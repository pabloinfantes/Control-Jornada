package com.example.controljornada.ui.obra;

import com.example.controljornada.data.model.Obra;
import com.example.controljornada.ui.base.BasePresenter;
import com.example.controljornada.ui.base.OnRepositoryCallback;
/**
 * Esta clase es la encargada de obligar a implementar estos metodos
 * @author pablo
 *
 */
public interface ObraManageContract {


    interface View extends OnRepositoryCallback {
        void setShortNameEmpty();
        void setNameEmpty();
        void setDescriptionEmpty();


    }

    interface Presenter extends BasePresenter {
        void add(Obra obra);
        void edit(Obra obra);
    }

    interface OnInteractorManageListener extends OnRepositoryCallback{
        void onShortNameEmpty();
        void onNameEmpty();
        void onDescriptionEmpty();

    }

    interface Repository{
        void add(Obra obra ,OnRepositoryCallback callback);
        void edit(Obra obra, OnRepositoryCallback callback);
    }

}
