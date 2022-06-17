package com.example.controljornada.ui.obra;

import com.example.controljornada.data.model.Obra;
import com.example.controljornada.ui.base.BasePresenter;
import com.example.controljornada.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;
/**
 * Esta clase es la encargada de obligar a implementar estos metodos
 * @author pablo
 *
 */
public interface ListadoObrasContract {



    interface View extends OnRepositoryListCallback{
        void showNoData();
        void showData(ArrayList<Obra> list);
        void showDataOrder();
    }


    interface Presenter extends BasePresenter {

        void load();
        void delete(Obra obra);
        void undo(Obra obra);
        void order();

    }

    interface OnInteractorListener extends OnRepositoryListCallback {
    }

    interface Repository{

        void getList(OnRepositoryListCallback callback);
        void delete(Obra obra, OnRepositoryListCallback callback);
        void undo(Obra obra, OnRepositoryListCallback callback);

    }



}
