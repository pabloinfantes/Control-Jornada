package com.example.controljornada.ui.listadohoras;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
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
        if (ListManageFragmentArgs.fromBundle(getArguments()).getUser() != null) {

            getActivity().setTitle(getString(R.string.tituloEditar));

            initView(ListManageFragmentArgs.fromBundle(getArguments()).getUser());

            initFabEdit();
        }

    }


    private User getUser() {
        User user = new User();
        if (ListManageFragmentArgs.fromBundle(getArguments()).getUser() != null)
            user.setId(ListManageFragmentArgs.fromBundle(getArguments()).getUser().getId());
        user.setNombre(binding.tieName2.getText().toString());
        user.setAdmin(Integer.parseInt(binding.tieAdmin.getText().toString()));
        user.setNumeroHorasMensuales(binding.tieNumHoras.getText().toString());
        user.setEmail(binding.tieEmail2.getText().toString());
        return user;
    }


    private void initFabEdit() {
        binding.fab2.setImageResource(R.drawable.ic_edit);
        binding.tilName2.setEnabled(false);
        binding.tilEmail2.setEnabled(false);
        binding.tilNumHoras.setEnabled(false);
        binding.fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.edit(getUser());
            }

        });
    }

    private void initView(User user) {
        binding.tieName2.setText(user.getNombre());
        binding.tieAdmin.setText(String.valueOf(user.getAdmin()));
        binding.tieEmail2.setText(user.getEmail());
        binding.tieNumHoras.setText(user.getNumeroHorasMensuales());
    }


    @Override
    public void onSuccess(String message) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL("http://158.101.203.234/add/controlJornada/updateUser.php?email=" + getUser().getEmail().toString() + "&admin=" + binding.tieAdmin.getText());
                    Log.d("url", String.valueOf(url));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.connect();

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream is = connection.getErrorStream();
                    } else {
                        InputStream err = connection.getErrorStream();
                    }

                    connection.disconnect();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
        thread.start();


        Bundle bundle = new Bundle();
        bundle.putSerializable(User.TAG, getUser());

        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.listManageFragment)
                .setArguments(bundle)
                .createPendingIntent();

        //5.- Crear la notificacion
        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(getActivity(), ControlJornadaAplication.IDCHANEL)
                    .setSmallIcon(R.drawable.ic_action_email)
                    .setAutoCancel(true)
                    .setContentTitle(getResources().getString(R.string.notification_title_add_usuario))
                    .setContentText(message)
                    .setContentIntent(pendingIntent);
        }
        //6.- Añadir notificacion al manager
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(), builder.build());


        NavHostFragment.findNavController(this).navigateUp();

    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
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

    @Override
    public void setEmailEmpty() {

    }


}