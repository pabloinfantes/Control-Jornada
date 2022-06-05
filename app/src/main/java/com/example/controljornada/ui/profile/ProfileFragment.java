package com.example.controljornada.ui.profile;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import com.example.controljornada.R;
import com.example.controljornada.data.model.User;
import com.example.controljornada.databinding.FragmentProfileBinding;
import com.example.controljornada.ui.login.LoginActivity;


public class ProfileFragment extends Fragment implements UserContract.View {

    FragmentProfileBinding binding;
    String[] generos = {"Femenino","Masculino"};

    private UserContract.Presenter presenter;


    public static Fragment newInstance(Bundle bundle){
        ProfileFragment fragment = new ProfileFragment();
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ProfileFragmentPresenter(this);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int idUser = prefs.getInt("id",1);
        String email = prefs.getString("email","1");
        String name = prefs.getString("name","1");
        String admin = prefs.getString("admin","1");
        String surname = prefs.getString("surname","1");
        binding.tvUsuarioNombre.setText(name);


        binding.spinner.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item,generos));
        binding.btCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });


        binding.btGuardarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new User(idUser,email,name,Integer.parseInt(admin),"0");
                user.setApellidos(surname);
                user.setEmpresa(binding.tieEmpresa.getText().toString());
                user.setGenero(binding.spinner.getSelectedItem().toString());
                user.setEdad(Integer.parseInt(binding.tieEdad.getText().toString()));
                user.setTelefono(binding.tieTelefono.getText().toString());

                presenter.edit(user);
            }
        });
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
}