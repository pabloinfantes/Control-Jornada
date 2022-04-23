package com.example.controljornada.ui.listadohoras;




import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.fragment.NavHostFragment;

import com.example.controljornada.ControlJornadaAplication;
import com.example.controljornada.R;
import com.example.controljornada.data.model.User;
import com.example.controljornada.databinding.FragmentListManageBinding;
import com.example.controljornada.ui.obra.ObraManageFragmentArgs;

import java.util.Random;


public class ListManageFragment extends Fragment implements ListadoManageContract.View {


    private FragmentListManageBinding binding;
    private ListadoManageContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListManagePresenter(this);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentListManageBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (ListManageFragmentArgs.fromBundle(getArguments()).getUser()!=null){

            getActivity().setTitle(getString(R.string.tituloEditar));

            initView(ListManageFragmentArgs.fromBundle(getArguments()).getUser());

            initFabEdit();
        }else {
            initFabAdd();
        }

    }

    private void initFabAdd() {
        binding.fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.add(getUser());
            }
        });
    }

    private User getUser() {
        User user = new User();
        if (ListManageFragmentArgs.fromBundle(getArguments()).getUser() !=null)
            user.setId(ListManageFragmentArgs.fromBundle(getArguments()).getUser().getId());
        user.setNombreCompleto(binding.tieName2.getText().toString());
        user.setNumeroHorasMensuales(binding.tieNumeroHoras.getText().toString());
        user.setNombreCorto(binding.tieNombreCorto2.getText().toString());
        return user;
    }


    private void initFabEdit() {

        binding.fab2.setImageResource(R.drawable.ic_edit);
        binding.tieNombreCorto2.setEnabled(false);
        binding.fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.edit(getUser());
            }


        });
    }

    private void initView(User user) {
        binding.tieName2.setText(user.getNombreCompleto());
        binding.tieNumeroHoras.setText(user.getNumeroHorasMensuales());
        binding.tieNombreCorto2.setText(user.getNombreCorto());
    }

    @Override
    public void onSuccess(String message) {


        Bundle bundle = new Bundle();
        bundle.putSerializable(User.TAG, getUser());


        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.listManageFragment)
                .setArguments(bundle)
                .createPendingIntent();

        //5.- Crear la notificacion
        Notification.Builder builder = new Notification.Builder(getActivity(), ControlJornadaAplication.IDCHANEL)
                .setSmallIcon(R.drawable.ic_action_email)
                .setAutoCancel(true)
                .setContentTitle(getResources().getString(R.string.notification_title_add_usuario))
                .setContentText(message)
                .setContentIntent(pendingIntent);
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
    public void setNombreEmpty() {

    }

    @Override
    public void setNumHorasEmpty() {

    }

    @Override
    public void setNombreCortoEmpty() {

    }


}