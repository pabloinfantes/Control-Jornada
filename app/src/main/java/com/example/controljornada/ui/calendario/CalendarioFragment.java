package com.example.controljornada.ui.calendario;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controljornada.data.model.Horario;
import com.example.controljornada.data.model.User;
import com.example.controljornada.databinding.FragmentCalendarioBinding;
import com.example.controljornada.ui.login.LoginActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CalendarioFragment extends Fragment implements CalendarioListContract.View {

    private FragmentCalendarioBinding binding;
    private CalendarioListContract.Presenter presenter;
    private CalendarioAdapter adapter;

    public static Fragment newInstance(Bundle bundle) {
        CalendarioFragment fragment = new CalendarioFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CalendarioListPresenter(this);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = FragmentCalendarioBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRv();
        binding.calendario.setOnDateChangeListener((calendarView, year, month, day) -> {

            binding.rvCalendario.removeAllViewsInLayout();
            String actualdate;

            if ((month +1 ) < 10){
                String monthGood = "0" +(month + 1);

                if (day < 10){
                     actualdate = year + "-" + monthGood + "-" + "0"+day;
                    Log.d("Calendario1", actualdate);
                }else {
                    actualdate = year + "-" + monthGood + "-" + day;
                    Log.d("Calendario2", actualdate);
                }

            }else {

                if (day < 10){
                     actualdate = year + "-" + (month+1) + "-" + "0"+day;
                    Log.d("Calendario3", actualdate);
                }else {
                     actualdate = year + "-" + (month+1) + "-" + day;
                    Log.d("Calendario4", actualdate);
                }
            }

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

            String admin = prefs.getString("admin","1");
            String emailUser = prefs.getString("emailUser","1");

            if (admin.equals("1")){
                presenter.selectAdminUser(actualdate);
            }else {
                presenter.selectNormalUser(emailUser, actualdate );
            }

        });
    }

    private void initRv() {

            //1.- Sera inicializar dicho adapter
            adapter = new CalendarioAdapter(new ArrayList<Horario>());
            //2.- OBLIGATORIOMENTE se debe indicae que diseño (layout) tendra el recycler view
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            //3.- Asgino el layout al recyclerView
            binding.rvCalendario.setLayoutManager(linearLayoutManager);
            //4.- Asigno a la vista sus datos (modelo)
            binding.rvCalendario.setAdapter(adapter);

    }


    @Override
    public void onFailure(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public <T> void onSuccess(List<T> list) {
        //
    }

    @Override
    public void onDeleteSucces(String message) {
        //
    }

    @Override
    public void onUndoSuccess(String message) {
        //
    }

    @Override
    public void showNoData() {
        Toast.makeText(getContext(),"mm",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(ArrayList<Horario> list) {
        Toast.makeText(getContext(),list.toString(),Toast.LENGTH_SHORT).show();
        adapter.update(list);
    }
}






