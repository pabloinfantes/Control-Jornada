package com.example.controljornada.data.repository;

import com.example.controljornada.data.ControlJornadaDatabase;
import com.example.controljornada.data.dao.HorarioDao;
import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.horario.HorarioContract;

import java.util.ArrayList;

public class HorarioRepository implements HorarioContract.Repository {

    private static HorarioRepository instance;
    private ArrayList<Horario> list;

    private HorarioDao horarioDao;

    private HorarioRepository(){
        list = new ArrayList<>();
        horarioDao = ControlJornadaDatabase.getDatabase().horarioDao();
    }

    public static HorarioRepository getInstance(){
        if (instance == null){
            instance = new HorarioRepository();
        }
        return instance;
    }

    @Override
    public void add(Horario horario, OnRepositoryCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(() -> horarioDao.insert(horario));
        callback.onSuccess("Se ha añadido correctamente");
    }
}
