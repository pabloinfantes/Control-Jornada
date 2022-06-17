package com.example.controljornada.ui.horario;

import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.data.model.User;
import com.example.controljornada.ui.base.BasePresenter;
import com.example.controljornada.ui.base.OnRepositoryCallback;
import com.example.controljornada.ui.base.OnRepositoryListCallback;
import com.example.controljornada.ui.base.ReadFromAusencia;
import com.example.controljornada.ui.base.ReadFromObras;
import com.example.controljornada.ui.base.ReadFromRoomCallback;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;
/**
 * Esta clase es la encargada de obligar a implementar estos metodos
 * @author pablo
 *
 */
public interface HorarioContract
{

    interface View extends OnRepositoryCallback ,ReadFromRoomCallback, ReadFromObras, ReadFromAusencia {
        void setHora1AntesHora2();
        void setHora3AntesHora4();
    }

    interface Presenter extends BasePresenter {

        void leer(Horario horario);
        void leer(User user);
        void leerObras();
        void leerAusencia(Horario horario);


        void editNumHora(User user);
        void editHorario(Horario horario);


        void add(Horario horario) throws ParseException;
        void add(User user) throws ExecutionException, InterruptedException;
    }

    interface OnInteractorManageListener extends OnRepositoryCallback ,ReadFromRoomCallback , ReadFromObras,ReadFromAusencia{
        void onHora1AntesHora2();
        void onHora3AntesHora4();
    }

    interface Repository{
        void leer(Horario horario , ReadFromRoomCallback callback);
        void leer(User user, ReadFromRoomCallback callback);
        void leerObra(ReadFromObras callback);
        void leerAusencia(Horario horario,ReadFromAusencia callback);

        void editNumHora(User user, OnRepositoryCallback callback);
        void editHorario(Horario horario ,OnRepositoryCallback callback);

        void add(Horario horario ,OnRepositoryCallback callback);
        void add(User user, OnRepositoryCallback callback) throws ExecutionException, InterruptedException;
    }
}
