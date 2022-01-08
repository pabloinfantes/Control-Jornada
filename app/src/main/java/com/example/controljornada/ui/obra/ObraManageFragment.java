package com.example.controljornada.ui.obra;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.controljornada.R;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.databinding.FragmentObraManageBinding;


public class ObraManageFragment extends Fragment implements ObraManageContract.View{

    private FragmentObraManageBinding binding;
    private ObraManageContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ObraManagePresenter(this);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentObraManageBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (ObraManageFragmentArgs.fromBundle(getArguments()).getObra()!=null){

            getActivity().setTitle(getString(R.string.titleEditObra));
            initView(ObraManageFragmentArgs.fromBundle(getArguments()).getObra());

            initFabEdit();
        }else {
            initFabAdd();
        }

    }

    private void initFabAdd() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.add(getObra());
            }
        });
    }

    private void initFabEdit() {
        binding.fab.setImageResource(R.drawable.ic_edit);
        binding.tieShortName.setEnabled(false);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.edit(getObra());
            }


        });
    }


    private void initView(Obra obra) {
        binding.tieShortName.setText(obra.getShortname());
        binding.tieName.setText(obra.getName());
        binding.tieDescription.setText(obra.getDescription());

    }

    private Obra getObra() {
        Obra obra = new Obra();
        obra.setShortname(binding.tieShortName.getText().toString());
        obra.setName(binding.tieName.getText().toString());
        obra.setDescription(binding.tieDescription.getText().toString());
        return obra;
    }




    @Override
    public void onSuccess(String message) {
        NavHostFragment.findNavController(this).navigateUp();

    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }




    @Override
    public void setShortNameEmpty() {

    }

    @Override
    public void setNameEmpty() {

    }

    @Override
    public void setDescriptionEmpty() {

    }

}