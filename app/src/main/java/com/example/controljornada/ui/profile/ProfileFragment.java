package com.example.controljornada.ui.profile;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import com.example.controljornada.R;
import com.example.controljornada.data.model.User;
import com.example.controljornada.databinding.FragmentProfileBinding;
import com.example.controljornada.ui.listadohoras.ListManageFragmentArgs;
import com.example.controljornada.ui.login.LoginActivity;

/**
 * Esta clase es la encargada de gestionar lo que ocurre en esta vista en concreto
 * @author pablo
 *
 */
public class ProfileFragment extends Fragment implements UserContract.View {

    FragmentProfileBinding binding;
    String[] generos = {"Femenino","Masculino"};

    private UserContract.Presenter presenter;
    public User userLeido = null;



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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String email = prefs.getString("email","1");
        presenter.leer(email);

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

        initView();


        if (userLeido.getGenero() != null){
            switch (userLeido.getGenero()){
                case "Femenino":
                    binding.spinner.setSelection(0);
                    break;
                case "Masculino":
                    binding.spinner.setSelection(1);
                    break;

            }
        }

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
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                int idUser = prefs.getInt("id",1);
                String email = prefs.getString("email","1");
                String name = prefs.getString("name","1");
                String admin = prefs.getString("admin","1");
                String surname = prefs.getString("surname","1");

                User userNuevo = new User(idUser,email,name,Integer.parseInt(admin));
                userNuevo.setEmpresa(binding.tieEmpresa.getText().toString());
                userNuevo.setGenero(binding.spinner.getSelectedItem().toString());
                userNuevo.setEdad(binding.tieEdad.getText().toString());
                userNuevo.setTelefono(binding.tieTelefono.getText().toString());

                presenter.edit(userNuevo);
            }
        });
    }

    private void initView() {
        binding.tvUsuarioNombre.setText(userLeido.getNombre());
        binding.spinner.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item,generos));
        binding.tieEmpresa.setText(userLeido.getEmpresa());
        binding.tieEdad.setText(userLeido.getEdad());
        binding.tieTelefono.setText(userLeido.getTelefono());
    }


    @Override
    public void onSuccess(String message) {
        Toast.makeText(getContext(),"Se ha editado correctamente su usuario",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnSuccessReadUser(User user) {

        userLeido = user;
    }

    @Override
    public void OnFailureReadUser(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
}