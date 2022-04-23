package com.example.controljornada.ui.horario;


import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.controljornada.R;
import com.example.controljornada.databinding.FragmentHorarioBinding;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;


public class HorarioFragment extends Fragment implements View.OnClickListener {


    private FragmentHorarioBinding binding;
    private HorarioAdapter adapter;
    //private OnSendObraListener listener;

    public static Fragment newInstance(Bundle bundle) {
        HorarioFragment fragment = new HorarioFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

//    public interface OnSendObraListener{
//        void onSendObra(Obra obra);
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        URL url = null;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL("http://158.101.203.234/add/controlJornada/controlJornada.php");
            //url = new URL("https://www.google.com");
            urlConnection = (HttpURLConnection) url.openConnection();
            Log.d("url", String.valueOf(urlConnection));

            //InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Log.d("entra", "entra");
            //Log.d("IIN", String.valueOf(in));
        }

        catch (MalformedURLException e) {
            Log.d("entra", "entra1");
            e.printStackTrace();
        }
        catch (IOException e) {
            Log.d("entra", "entra2");
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }

        LocalDate  fechaActual = LocalDate.now();
        int mes = fechaActual.getMonthValue();
        String result = "";
        switch(mes){
            case 1:
            {
                result="Enero";
                break;
            }
            case 2:
            {
                result="Febrero";
                break;
            }
            case 3:
            {
                result="Marzo";
                break;
            }
            case 4:
            {
                result="Abril";
                break;
            }
            case 5:
            {
                result="Mayo";
                break;
            }
            case 6:
            {
                result="Junio";
                break;
            }
            case 7:
            {
                result="Julio";
                break;
            }
            case 8:
            {
                result="Agosto";
                break;
            }
            case 9:
            {
                result="Septiembre";
                break;
            }
            case 10:
            {
                result="Octubre";
                break;
            }
            case 11:
            {
                result="Noviembre";
                break;
            }
            case 12:
            {
                result="Diciembre";
                break;
            }
            default:
            {
                result="Error";
                break;
            }
        }
        binding.tvFechaHorario.setText(String.valueOf(fechaActual.getDayOfMonth())+" de "  + result+ " de "  +String.valueOf(fechaActual.getYear()));

        binding.tvHorarioIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                //inicializar el time picker
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                                //inicializar horas y minutos
                                binding.tvHorarioIzq.setText(hours + ":" + minutes);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });
        binding.tvHorarioDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
                int minute1 = calendar.get(Calendar.MINUTE);
                //inicializar el time picker
                TimePickerDialog timePickerDialog1 = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                                //inicializar horas y minutos
                                binding.tvHorarioDer.setText(hours + ":" + minutes);
                            }
                        }, hour1, minute1, false);
                timePickerDialog1.show();
            }
        });
        binding.tvHorarioIzq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour2 = calendar.get(Calendar.HOUR_OF_DAY);
                int minute2 = calendar.get(Calendar.MINUTE);
                //inicializar el time picker
                TimePickerDialog timePickerDialog2 = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                                //inicializar horas y minutos
                                binding.tvHorarioIzq2.setText(hours + ":" + minutes);
                            }
                        }, hour2, minute2, false);
                timePickerDialog2.show();
            }
        });
        binding.tvHorarioDer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour3 = calendar.get(Calendar.HOUR_OF_DAY);
                int minute3 = calendar.get(Calendar.MINUTE);
                //inicializar el time picker
                TimePickerDialog timePickerDialog3 = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                                //inicializar horas y minutos
                                binding.tvHorarioDer2.setText(hours + ":" + minutes);
                            }
                        }, hour3, minute3, false);
                timePickerDialog3.show();
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


}