package com.example.controljornada.ui.ausencia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.controljornada.databinding.FragmentAusenciaBinding;



public class AusenciaFragment extends Fragment {

    private FragmentAusenciaBinding binding;
    private AusenciaAdapter adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAusenciaBinding.inflate(inflater, container, false);
        binding.btEnviar.setOnClickListener(view -> showHorarioFragment());
        binding.btCancelar.setOnClickListener(view -> showHorarioFragment());
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvAusencia();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initRvAusencia() {
        //1.- Sera inicializar dicho adapter
        adapter = new AusenciaAdapter();
        //2.- OBLIGATORIOMENTE se debe indicae que diseño (layout) tendra el recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        //3.- Asgino el layout al recyclerView
        binding.rvAusencia.setLayoutManager(linearLayoutManager);
        //4.- Asigno a la vista sus datos (modelo)
        binding.rvAusencia.setAdapter(adapter);
    }


    private void showHorarioFragment() {
        NavHostFragment.findNavController(this).navigateUp();
    }


}