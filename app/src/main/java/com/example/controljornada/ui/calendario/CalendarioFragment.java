package com.example.controljornada.ui.calendario;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.controljornada.databinding.FragmentCalendarioBinding;


public class CalendarioFragment extends Fragment {

    private FragmentCalendarioBinding binding;


    public static Fragment newInstance(Bundle bundle) {
        CalendarioFragment fragment = new CalendarioFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
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

        binding.calendario.setOnDateChangeListener((calendarView, year, month, day) -> {
            String actualdate = year + "/" + (month+1) + "/" + day;
            Log.d("Calendario", actualdate);

        });
    }


}






