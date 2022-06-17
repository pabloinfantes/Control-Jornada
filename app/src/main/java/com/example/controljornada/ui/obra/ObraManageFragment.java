package com.example.controljornada.ui.obra;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.controljornada.ControlJornadaAplication;
import com.example.controljornada.R;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.databinding.FragmentObraManageBinding;

import java.util.Random;

/**
 * Esta clase es la encargada de gestionar lo que ocurre en esta vista en concreto
 * @author pablo
 *
 */
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
        binding.tieName.setEnabled(false);
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
        if (ObraManageFragmentArgs.fromBundle(getArguments()).getObra() !=null)
            obra.setId(ObraManageFragmentArgs.fromBundle(getArguments()).getObra().getId());
        obra.setShortname(binding.tieShortName.getText().toString());
        obra.setName(binding.tieName.getText().toString());
        obra.setDescription(binding.tieDescription.getText().toString());
        return obra;
    }





    @Override
    public void onSuccess(String message) {

        //5.- Crear la notificacion
        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            builder = new Notification.Builder(getActivity(), ControlJornadaAplication.IDCHANEL)
                    .setSmallIcon(R.drawable.ic_action_email)
                    .setAutoCancel(true)
                    .setContentTitle(getResources().getString(R.string.notification_title_add_obra))
                    .setContentText("Ha cambiado algo en la lista de obras");
        }
        //6.- Añadir notificacion al manager
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(), builder.build());


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