package com.example.controljornada.ui.horario;


import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.JsonReader;
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

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.controljornada.R;
import com.example.controljornada.databinding.FragmentHorarioBinding;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ClientProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;


public class HorarioFragment extends Fragment implements View.OnClickListener {


    private FragmentHorarioBinding binding;
    private String result;



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