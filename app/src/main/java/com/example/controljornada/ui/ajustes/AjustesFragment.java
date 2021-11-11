package com.example.controljornada.ui.ajustes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.controljornada.databinding.FragmentAjustesBinding;


public class AjustesFragment extends Fragment {
    FragmentAjustesBinding binding;
    private AjustesAdapter adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAjustesBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvAjustes();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    private void initRvAjustes() {
        //1.- Sera inicializar dicho adapter
        adapter = new AjustesAdapter();
        //2.- OBLIGATORIOMENTE se debe indicae que diseño (layout) tendra el recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        //3.- Asgino el layout al recyclerView
        binding.rvAjustes.setLayoutManager(linearLayoutManager);
        //4.- Asigno a la vista sus datos (modelo)
        binding.rvAjustes.setAdapter(adapter);
    }
}