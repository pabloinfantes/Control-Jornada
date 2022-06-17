package com.example.controljornada.ui.horario;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.controljornada.R;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.databinding.FragmentHorarioContenedorBinding;
import com.example.controljornada.ui.calendario.CalendarioFragment;
import com.example.controljornada.ui.profile.ProfileFragment;

import java.util.Calendar;

/**
 * Esta clase es la encargada de gestionar lo que ocurre en esta vista en concreto
 * @author pablo
 *
 */
public class HorarioContenedorFragment extends Fragment {

    private FragmentHorarioContenedorBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHorarioContenedorBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavigation();
    }

    private void initNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.horario:
                    loadFragment(HorarioFragment.newInstance(null));
                    break;
                case R.id.calendario:
                    loadFragment(CalendarioFragment.newInstance(null));
                    break;
                case R.id.profile:
                    loadFragment(ProfileFragment.newInstance(null));
                    break;
            }
            return true;
        });
    }




    @Override
    public void onStart() {
        super.onStart();
        loadFragment(HorarioFragment.newInstance(null));
    }

    private void loadFragment(Fragment newInstance) {
        if (newInstance != null){
            getChildFragmentManager().beginTransaction().replace(R.id.productContent,newInstance).commit();
        }
    }

}