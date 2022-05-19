package com.example.controljornada.data.repository;

import com.example.controljornada.data.ControlJornadaDatabase;
import com.example.controljornada.data.dao.HorarioDao;
import com.example.controljornada.data.dao.UserDao;
import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.OnRepositoryListCallback;
import com.example.controljornada.ui.calendario.CalendarioListContract;
import com.example.controljornada.ui.horario.HorarioContract;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HorarioRepository implements HorarioContract.Repository , CalendarioListContract.Repository {

    private static HorarioRepository instance;
    private ArrayList<Horario> list;

    private HorarioDao horarioDao;
    private UserDao userDao;

    private HorarioRepository(){
        list = new ArrayList<>();
        horarioDao = ControlJornadaDatabase.getDatabase().horarioDao();
        userDao = ControlJornadaDatabase.getDatabase().userDao();
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
        callback.onSuccess(horario.toString());
    }

    @Override
    public void add(User user, OnRepositoryCallback callback) {
        ControlJornadaDatabase.databaseWriteExecutor.submit(() -> userDao.insert(user));
        callback.onSuccess(user.toString());
    }

    @Override
    public void selectAdminUser(String fechaDelDiaDeTrabajo, OnRepositoryListCallback callback) {

        try {
            list = (ArrayList<Horario>) ControlJornadaDatabase.databaseWriteExecutor.submit(() -> horarioDao.selectAdminUser(fechaDelDiaDeTrabajo)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSuccess(list);
    }

    @Override
    public void selectNormalUser(int iduser, String fechaDelDiaDeTrabajo, OnRepositoryListCallback callback) {

        try {
            list = (ArrayList<Horario>) ControlJornadaDatabase.databaseWriteExecutor.submit(() -> horarioDao.selectNormalUser(iduser,fechaDelDiaDeTrabajo)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSuccess(list);
    }
}
