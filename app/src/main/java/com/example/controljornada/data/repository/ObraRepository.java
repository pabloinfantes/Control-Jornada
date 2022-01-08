package com.example.controljornada.data.repository;


import com.example.controljornada.data.model.Obra;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.OnRepositoryListCallback;
import com.example.controljornada.ui.obra.ListadoObrasContract;
import com.example.controljornada.ui.obra.ObraManageContract;

import java.util.ArrayList;
import java.util.Collections;

public class ObraRepository implements ListadoObrasContract.Repository , ObraManageContract.Repository {

    private static ObraRepository instance;
    private ArrayList<Obra> list;


    private ObraRepository(){
        list = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        list.add(new Obra("IES Portada Alta","IPA","2"));
        list.add(new Obra("IES Puerto de la Torre","IPT","5"));
        list.add(new Obra("IES Fuente Alegre","IFA","1"));
        list.add(new Obra("Litoral","Lit","3"));
        list.add(new Obra("CEIP Los Morales","CLM","4"));
    }

    public static ObraRepository getInstance(){
        if (instance == null){
            instance = new ObraRepository();
        }
        return instance;
    }


    @Override
    public void getList(OnRepositoryListCallback callback) {
        Collections.sort(list);
        callback.onSuccess(list);

    }

    @Override
    public void delete(Obra obra,OnRepositoryListCallback callback) {
        list.remove(obra);
        callback.onDeleteSucces("Se ha eliminado la dependencia" +obra.getName());
    }

    @Override
    public void undo(Obra obra , OnRepositoryListCallback callback) {
        list.add(obra);
        callback.onUndoSuccess("Se ha vuelto ha añadir esta obra");
    }




    @Override
    public void add(Obra obra, OnRepositoryCallback callback) {
        for(Obra obra1 : list){
            if (obra.getShortname().equals(obra1.getShortname())){
                callback.onFailure("Error en la funcion de añadir");
                return;

            }

        }
        list.add(obra);
        callback.onSuccess("Se ha añadido correctamente");

    }

    @Override
    public void edit(Obra obra, OnRepositoryCallback callback) {
        for(Obra obra1 : list){
            if (obra.getShortname().toString().equals(obra1.getShortname().toString())){
                obra1.setName(obra.getName().toString());
                obra1.setDescription(obra.getDescription().toString());
                callback.onSuccess("Se ha editado correctamente");
                return;
            }
        }
        callback.onFailure("Error en la funcion de editar");
    }
}
