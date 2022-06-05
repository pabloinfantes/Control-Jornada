package com.example.controljornada.ui.horario;


import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import com.example.controljornada.R;
import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.data.model.User;
import com.example.controljornada.databinding.FragmentHorarioBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class HorarioFragment extends Fragment implements View.OnClickListener ,HorarioContract.View{


    private FragmentHorarioBinding binding;
    private HorarioContract.Presenter presenter;
    private String firmado;
    private String existeUser;
    public static ArrayList<String> listObras = new ArrayList<>();

    public static Fragment newInstance(Bundle bundle) {
        HorarioFragment fragment = new HorarioFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new HorarioFragmentPresenter(this);
        presenter.leerObras();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentHorarioBinding.inflate(inflater, container, false);
        binding.btAusencia.setOnClickListener(this);
        binding.btFirma.setOnClickListener(this);

        return binding.getRoot();

    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int idUser = prefs.getInt("id",44);
        String email = prefs.getString("email","1");
        String name = prefs.getString("name","1");
        String admin = prefs.getString("admin","1");
        String surname = prefs.getString("surname","1");



        binding.spinnerLugarTrabajo1.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item,listObras));
        binding.spinnerLugarTrabajo2.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item,listObras));


        try {

            User user = new User(idUser,email,name, Integer.parseInt(admin),"0");
            Log.d("leerUser",user.toString());
            presenter.leer(user);
            Log.d("existeUser",existeUser);

            if (existeUser.equals("existe")){
                presenter.editNumHora(user);
            }else {
                user.setApellidos(surname);
                Log.d("aaaaad",user.toString());
                presenter.add(user);
            }



        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        LocalDate  fechaActual = LocalDate.now();
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
        binding.tvFechaHorario.setText(fechaActual.getDayOfMonth()+" de "  + resulta+ " de "  +fechaActual.getYear());

        binding.tvHorarioIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                //inicializar el time picker
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                                //inicializar horas y minutos
                                calendar.set(Calendar.HOUR_OF_DAY, hours);
                                calendar.set(Calendar.MINUTE, minutes);
                                SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
                                String formatedDate = timeformat.format(calendar.getTime());
                                binding.tvHorarioIzq.setText(formatedDate);

                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY),  calendar.get(Calendar.MINUTE), false);
                timePickerDialog.show();
            }
        });
        binding.tvHorarioDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                //inicializar el time picker
                TimePickerDialog timePickerDialog1 = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                                //inicializar horas y minutos
                                calendar.set(Calendar.HOUR_OF_DAY, hours);
                                calendar.set(Calendar.MINUTE, minutes);
                                SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
                                String formatedDate = timeformat.format(calendar.getTime());
                                binding.tvHorarioDer.setText(formatedDate);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY),  calendar.get(Calendar.MINUTE), false);
                timePickerDialog1.show();
            }
        });
        binding.tvHorarioIzq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                //inicializar el time picker
                TimePickerDialog timePickerDialog2 = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                                //inicializar horas y minutos
                                calendar.set(Calendar.HOUR_OF_DAY, hours);
                                calendar.set(Calendar.MINUTE, minutes);
                                SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
                                String formatedDate = timeformat.format(calendar.getTime());
                                binding.tvHorarioIzq2.setText(formatedDate);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY),  calendar.get(Calendar.MINUTE), false);
                timePickerDialog2.show();
            }
        });
        binding.tvHorarioDer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                //inicializar el time picker
                TimePickerDialog timePickerDialog3 = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                                //inicializar horas y minutos
                                calendar.set(Calendar.HOUR_OF_DAY, hours);
                                calendar.set(Calendar.MINUTE, minutes);
                                SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm ");
                                String formatedDate = timeformat.format(calendar.getTime());
                                binding.tvHorarioDer2.setText(formatedDate);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY),  calendar.get(Calendar.MINUTE), false);
                timePickerDialog3.show();
            }
        });

        binding.btFirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Horario horario =new Horario(idUser,
                        email,
                        binding.spinnerLugarTrabajo1.getSelectedItem().toString(),
                        binding.spinnerLugarTrabajo2.getSelectedItem().toString(),
                        fechaActual.toString(),
                        binding.tvHorarioIzq.getText().toString(),
                        binding.tvHorarioDer.getText().toString(),
                        binding.tvHorarioIzq2.getText().toString(),
                        binding.tvHorarioDer2.getText().toString(),
                        10,
                        null);


                presenter.leer(horario);

                if (firmado.equals("0")){
                    try {
                        Log.d("hora1",horario.getHorarioEntradaMñn());
                        Log.d("hora2",horario.getHorarioSalidaMñn());
                        Log.d("hora3",horario.getHorarioEntradaTarde());
                        Log.d("hora4",horario.getHorarioSalidaTarde());

                        int hora1 = Integer.parseInt(horario.getHorarioEntradaMñn().substring(0,2));
                        int hora2 = Integer.parseInt(horario.getHorarioSalidaMñn().substring(0,2));
                        int hora3 = Integer.parseInt(horario.getHorarioEntradaTarde().substring(0,2));
                        int hora4 = Integer.parseInt(horario.getHorarioSalidaTarde().substring(0,2));

                        int numHorasFinal = (hora4 -hora1) -1;
                        horario.setNumeroHoras(numHorasFinal);

                        Log.d("numHoras", String.valueOf(numHorasFinal));
                        presenter.add(horario);


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(getContext(),"Ya has firmado hoy",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btAusencia:
                showAusenciaFragment();
                break;

        }

    }

    private void showAusenciaFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_horarioContenedorFragment_to_ausenciaFragment);
    }


    @Override
    public void onSuccess(String message) {

        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }



    @Override
    public void setHora1AntesHora2() {
        Toast.makeText(getContext(),"hora 1 mal",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setHora3AntesHora4() {
        Toast.makeText(getContext(),"hora 2 mal",Toast.LENGTH_SHORT).show();
    }



    @Override
    public void OnSuccessReadHorario(String message) {
        firmado = message;
    }

    @Override
    public void OnFailureReadHorario(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnSuccessReadUser(String message) {
        existeUser = message;
    }

    @Override
    public void OnFailureReadUser(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void OnSuccessReadObra(ArrayList<String> obras) {
        listObras.clear();
        for (String obra: obras) {
            listObras.add(obra);
        }
    }

    @Override
    public void OnFailureReadObra(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
}