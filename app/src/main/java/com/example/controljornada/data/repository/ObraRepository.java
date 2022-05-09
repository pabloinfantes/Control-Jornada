package com.example.controljornada.data.repository;


import com.example.controljornada.data.ControlJornadaDatabase;
import com.example.controljornada.data.dao.ObraDao;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.OnRepositoryListCallback;
import com.example.controljornada.ui.obra.ListadoObrasContract;
import com.example.controljornada.ui.obra.ObraManageContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class ObraRepository implements ListadoObrasContract.Repository , ObraManageContract.Repository {

    private static ObraRepository instance;
    private ArrayList<Obra> list;

    private ObraDao obraDao;

    private ObraRepository(){
        list = new ArrayList<>();
        obraDao = ControlJornadaDatabase.getDatabase().obraDao();
    }


    public static ObraRepository getInstance(){
        if (instance == null){
            instance = new ObraRepository();
        }
        return instance;
    }


    @Override
    public void getList(OnRepositoryListCallback callback) {
        try {
            list = (ArrayList<Obra>) ControlJornadaDatabase.databaseWriteExecutor.submit(() -> obraDao.select()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSuccess(list);

    }

    @Override
    public void delete(Obra obra,OnRepositoryListCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(()-> obraDao.delete(obra));
        callback.onDeleteSucces("Se ha eliminado la dependencia" +obra.getName());
    }

    @Override
    public void undo(Obra obra , OnRepositoryListCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(()-> obraDao.insert(obra));
        callback.onUndoSuccess("Se ha vuelto ha añadir esta obra");
    }




    @Override
    public void add(Obra obra, OnRepositoryCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(()-> obraDao.insert(obra));
        callback.onSuccess("Se ha añadido correctamente");

    }

    @Override
    public void edit(Obra obra, OnRepositoryCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(()-> obraDao.update(obra));
        callback.onSuccess("Se ha editado correctamente");
    }
}
