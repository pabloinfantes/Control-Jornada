package com.example.controljornada.ui.horario;


import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

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
import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.User;
import com.example.controljornada.databinding.FragmentHorarioBinding;
import com.example.controljornada.ui.login.LoginActivity;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;


public class HorarioFragment extends Fragment implements View.OnClickListener ,HorarioContract.View{


    private FragmentHorarioBinding binding;
    private HorarioContract.Presenter presenter;

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
        int idUser = prefs.getInt("id",1);
        String email = prefs.getString("email","1");
        String name = prefs.getString("name","1");
        String admin = prefs.getString("admin","1");

        Log.d("UUUSEER",new User(idUser,email,name,Integer.parseInt(admin)).toString());
        presenter.add(new User(idUser,email,name, Integer.parseInt(admin)));


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

                presenter.add(new Horario(idUser,
                        email,
                        binding.tieLugarTrabajo.getText().toString(),
                        binding.tieLugarTrabajo2.getText().toString(),
                        fechaActual.toString(),
                        binding.tvHorarioIzq.getText().toString(),
                        binding.tvHorarioIzq2.getText().toString(),
                        binding.tvHorarioDer.getText().toString(),
                        binding.tvHorarioDer2.getText().toString(),
                        10)
                );

                /*
                presenter.add(new Horario(idUser,
                        email,
                        binding.tieLugarTrabajo.getText().toString(),
                        binding.tieLugarTrabajo2.getText().toString(),
                        "2022-05-31",
                        binding.tvHorarioIzq.getText().toString(),
                        binding.tvHorarioIzq2.getText().toString(),
                        binding.tvHorarioDer.getText().toString(),
                        binding.tvHorarioDer2.getText().toString(),
                        10)
                );
                */

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
}