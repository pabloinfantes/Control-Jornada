package com.example.controljornada.ui.horario;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controljornada.R;
import com.example.controljornada.databinding.FragmentHorarioBinding;


public class HorarioFragment extends Fragment implements View.OnClickListener{

    private FragmentHorarioBinding binding;
    private HorarioAdapter adapter;

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

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvHorario();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btAusencia:
                showAusenciaFragment();
                break;
            case R.id.btFirma:
                showCalendarioFragment();
                break;

        }
    }

    private void showAusenciaFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_horarioFragment_to_ausenciaFragment);
    }
    private void showCalendarioFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_horarioFragment_to_calendarioFragment);
    }
    private void initRvHorario() {
        //1.- Sera inicializar dicho adapter
        adapter = new HorarioAdapter();
        //2.- OBLIGATORIOMENTE se debe indicae que diseño (layout) tendra el recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        //3.- Asgino el layout al recyclerView
        binding.rvHorario.setLayoutManager(linearLayoutManager);
        //4.- Asigno a la vista sus datos (modelo)
        binding.rvHorario.setAdapter(adapter);
    }



}