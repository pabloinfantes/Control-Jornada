package com.example.controljornada.ui.ajustes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.controljornada.R;
import com.example.controljornada.databinding.FragmentAjustesBinding;


public class AjustesFragment extends Fragment {
    FragmentAjustesBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAjustesBinding.inflate(inflater, container, false);
        binding.ajustesBtPerfil.setOnClickListener(view -> showProfile());
        binding.ajustesBtNumeroHorasMensuales.setOnClickListener(view -> showNumeroHorasFragment());
        return binding.getRoot();

    }

    private void showNumeroHorasFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_ajustesFragment_to_listadoNumeroHorasFragment);
    }

    private void showProfile() {
        NavHostFragment.findNavController(this).navigate(R.id.action_ajustesFragment_to_profileFragment);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }





}