package com.example.controljornada.ui.calendario;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.controljornada.R;
import com.example.controljornada.databinding.FragmentCalendarioBinding;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class CalendarioFragment extends Fragment implements CalendarAdapter.OnItemListener {

    FragmentCalendarioBinding binding;
    private LocalDate DiaSeleccionado;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = FragmentCalendarioBinding.inflate(inflater, container, false);
        binding.btAjustes.setOnClickListener(view -> showAjustesFragment());
        binding.btSobreNosotros.setOnClickListener(view -> showAboutUsFragment());
        DiaSeleccionado = LocalDate.now();
        setMonthView();
        return binding.getRoot();


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        binding.tvMesYAnio.setText(mesDiaPorAño(DiaSeleccionado));
        ArrayList<String> DiasDelMes = DiasDelMesArray(DiaSeleccionado);

        CalendarAdapter calendarAdapter = new CalendarAdapter(DiasDelMes, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 7);
        binding.Rvcalendar.setLayoutManager(layoutManager);
        binding.Rvcalendar.setAdapter(calendarAdapter);
        binding.btAtras.setOnClickListener(view -> mesAnterior());
        binding.btSiguiente.setOnClickListener(view -> mesPosterior());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> DiasDelMesArray(LocalDate date) {
        ArrayList<String> DiasDelMesArray = new ArrayList<String>();
        YearMonth yearMonth = YearMonth.from(date);

        int DiasDelMes = yearMonth.lengthOfMonth();
        LocalDate PrimerDiaMes = DiaSeleccionado.withDayOfMonth(1);
        int DiaDeLaSemana = PrimerDiaMes.getDayOfWeek().getValue();
        for (int i = 1; i <= 42; i++) {
            if (i <= DiaDeLaSemana || i > DiasDelMes + DiaDeLaSemana) {
                DiasDelMesArray.add("");
            } else {
                DiasDelMesArray.add(String.valueOf(i - DiaDeLaSemana));
            }
        }
        return DiasDelMesArray;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String mesDiaPorAño(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void mesAnterior() {
        DiaSeleccionado = DiaSeleccionado.minusMonths(1);
        setMonthView();
    }


    public void mesPosterior() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DiaSeleccionado = DiaSeleccionado.plusMonths(1);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setMonthView();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String txtDia) {
        if (txtDia.equals("")) {
            String message = "Dia seleccionado: " + txtDia + " " + mesDiaPorAño(DiaSeleccionado);
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }



    private void showAjustesFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_calendarioFragment_to_ajustesFragment);
    }
    private void showAboutUsFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_calendarioFragment_to_aboutUsFragment);
    }
}