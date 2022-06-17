package com.example.controljornada.ui.ausencia;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.User;
import com.example.controljornada.databinding.FragmentAusenciaBinding;
import com.example.controljornada.ui.horario.HorarioContract;
import com.example.controljornada.ui.horario.HorarioFragmentPresenter;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Esta clase es la encargada de hacer que la ausencia de un usuario en caso de que la haya
 * funcione correctamente
 * @author pablo
 *
 */
public class AusenciaFragment extends Fragment implements HorarioContract.View {

    private FragmentAusenciaBinding binding;
    private HorarioContract.Presenter presenter;
    private String ausencia = "";
    private String firmado = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HorarioFragmentPresenter(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAusenciaBinding.inflate(inflater, container, false);
        binding.btEnviar.setOnClickListener(view -> {
            try {
                showHorarioFragment();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        binding.btCancelar.setOnClickListener(view -> showHorarioFragmentCancelar());
        return binding.getRoot();

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LocalDate fechaActual = LocalDate.now();
        int mes = fechaActual.getMonthValue();
        String resulta = "";
        switch(mes){
            case 1:
            {
                resulta="Enero";
                break;
            }
            case 2:
            {
                resulta="Febrero";
                break;
            }
            case 3:
            {
                resulta="Marzo";
                break;
            }
            case 4:
            {
                resulta="Abril";
                break;
            }
            case 5:
            {
                resulta="Mayo";
                break;
            }
            case 6:
            {
                resulta="Junio";
                break;
            }
            case 7:
            {
                resulta="Julio";
                break;
            }
            case 8:
            {
                resulta="Agosto";
                break;
            }
            case 9:
            {
                resulta="Septiembre";
                break;
            }
            case 10:
            {
                resulta="Octubre";
                break;
            }
            case 11:
            {
                resulta="Noviembre";
                break;
            }
            case 12:
            {
                resulta="Diciembre";
                break;
            }
            default:
            {
                resulta="Error";
                break;
            }
        }

        binding.tvFechaAusencia.setText(fechaActual.getDayOfMonth()+" de "  + resulta+ " de "  +fechaActual.getYear());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showHorarioFragment() throws ParseException {

        if (binding.enfermedad.isChecked()){
            ausencia = binding.enfermedad.getText().toString();

        }else if (binding.personal.isChecked()){
            ausencia = binding.personal.getText().toString();

        }else if(binding.festivo.isChecked()){
            ausencia = binding.festivo.getText().toString();
        }
        else if(binding.vacaciones.isChecked()){
            ausencia = binding.vacaciones.getText().toString();
        }
        else if(binding.recuperacion.isChecked()){
            ausencia = binding.recuperacion.getText().toString();
        }
        else if(binding.maternidad.isChecked()){
            ausencia = binding.maternidad.getText().toString();
        }
        else if(binding.paternidad.isChecked()){
            ausencia = binding.paternidad.getText().toString();
        }
        else if(binding.otros.isChecked()){
            ausencia = binding.otros.getText().toString();
        }



        LocalDate fechaActual = LocalDate.now();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int idUser = prefs.getInt("id",1);
        String email = prefs.getString("email","1");

        Horario horario = new Horario(
                idUser,
                email,
                "",
                "",
                fechaActual.toString(),
                "",
                "",
                "",
                "",
                0,
                ausencia);

        presenter.leer(horario);

        if (firmado.equals("0")){
            presenter.add(horario);
            Toast.makeText(getContext(), "horario añadido", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "Ya has firmado", Toast.LENGTH_SHORT).show();
        }

    }

    private void showHorarioFragmentCancelar() {
        NavHostFragment.findNavController(this).navigateUp();
    }


    @Override
    public void onSuccess(String message) {
        NavHostFragment.findNavController(this).navigateUp();

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void setHora1AntesHora2() {
        //
    }

    @Override
    public void setHora3AntesHora4() {
        //
    }

    @Override
    public void OnSuccessReadHorario(String message) {
        firmado = message;
    }

    @Override
    public void OnFailureReadHorario(String message) {

    }

    @Override
    public void OnSuccessReadUser(String message) {
        //
    }

    @Override
    public void OnFailureReadUser(String message) {
        //
    }



    @Override
    public void OnSuccessReadObra(ArrayList<String> obras) {
        //
    }

    @Override
    public void OnFailureReadObra(String message) {
//
    }

    @Override
    public void OnSuccessReadAusencia(Horario horario) {
        //
    }

    @Override
    public void OnFailureReadAusencia(String message) {
        //
    }
}