package com.example.controljornada.ui.horario;

import android.util.Log;

import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.data.model.User;
import com.example.controljornada.data.repository.HorarioRepository;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.OnRepositoryListCallback;
import com.example.controljornada.ui.base.ReadFromObras;
import com.example.controljornada.ui.base.ReadFromRoomCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HorarioFragmentInteractor implements OnRepositoryCallback, ReadFromRoomCallback, ReadFromObras {

    private HorarioContract.OnInteractorManageListener listener;
    private OnRepositoryCallback callback;
    private ReadFromRoomCallback readFromRoomCallback;
    private ReadFromObras listCallback;

    public HorarioFragmentInteractor(HorarioContract.OnInteractorManageListener listener) {
        this.listener = listener;
        this.callback = this;
        this.readFromRoomCallback = this;
        this.listCallback = this;
    }

    public void add(Horario horario) throws ParseException {
        Log.d("ausen",horario.toString());
        if(horario.getMotivoAusencia() == null){
            int hora1 = Integer.parseInt(horario.getHorarioEntradaMñn().substring(0,2));
            int hora2 = Integer.parseInt(horario.getHorarioSalidaMñn().substring(0,2));
            int hora3 = Integer.parseInt(horario.getHorarioEntradaTarde().substring(0,2));
            int hora4 = Integer.parseInt(horario.getHorarioSalidaTarde().substring(0,2));

            SimpleDateFormat date = new SimpleDateFormat("HH:mm");
            Date fecha1 = date.parse(horario.getHorarioEntradaMñn());
            Date fecha2 = date.parse(horario.getHorarioSalidaMñn());
            Date fecha3 = date.parse(horario.getHorarioEntradaTarde());
            Date fecha4 = date.parse(horario.getHorarioSalidaTarde());

            if ( fecha1.before(fecha2) && hora2 > 14){
                listener.onHora1AntesHora2();
                return;
            }

            if (fecha3.before(fecha4) && hora4 > 20){
                listener.onHora3AntesHora4();
                return;
            }
            HorarioRepository.getInstance().add(horario,callback);
        }else {
            HorarioRepository.getInstance().add(horario,callback);
        }

    }

    public void add(User user) throws ExecutionException, InterruptedException {
        HorarioRepository.getInstance().add(user,callback);
    }

    @Override
    public void onSuccess(String message) {
        listener.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }



    public void leer(Horario horario) {
        HorarioRepository.getInstance().leer(horario,readFromRoomCallback);
    }
    public void leer(User user) {
        HorarioRepository.getInstance().leer(user,readFromRoomCallback);
    }

    @Override
    public void OnSuccessReadHorario(String message) {
        listener.OnSuccessReadHorario(message);
    }

    @Override
    public void OnFailureReadHorario(String message) {
        listener.OnFailureReadHorario(message);
    }

    @Override
    public void OnSuccessReadUser(String message) {
        listener.OnSuccessReadUser(message);
    }

    @Override
    public void OnFailureReadUser(String message) {
        listener.OnFailureReadUser(message);
    }



    public void leerObras() {
        HorarioRepository.getInstance().leerObra(listCallback);
    }


    @Override
    public void OnSuccessReadObra(ArrayList<String> obras) {
        listener.OnSuccessReadObra(obras);
    }

    @Override
    public void OnFailureReadObra(String message) {
        listener.OnFailureReadObra(message);
    }

    public void editNumHora(User user) {
        HorarioRepository.getInstance().editNumHora(user,callback);
    }
}
