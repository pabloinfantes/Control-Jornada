package com.example.controljornada.ui.listadohoras;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.controljornada.R;
import com.example.controljornada.data.model.User;
import com.example.controljornada.databinding.FragmentAjustesBinding;
import com.example.controljornada.databinding.FragmentListadoNumeroHorasBinding;

import java.util.ArrayList;
import java.util.List;


public class ListadoNumeroHorasFragment extends Fragment implements ListadoNumeroHorasContract.View ,ListadoNumeroHorasAdapter.OnManageListadoListener{

    private FragmentListadoNumeroHorasBinding binding;
    private ListadoNumeroHorasAdapter adapter;
    private ListadoNumeroHorasContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter = new ListadoNumeroHorasPresenter(this);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentListadoNumeroHorasBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvNumHoras();
    }

    private void initRvNumHoras() {

        adapter = new ListadoNumeroHorasAdapter(new ArrayList<>(), this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        binding.rvUser.setLayoutManager(linearLayoutManager);

        binding.rvUser.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_listadohoras_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_order_byNombre:
                presenter.order();
                return true;
            case R.id.action_order_byNumeroHoras:
                adapter.orderByNumeroHoras();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }



    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {

    }

    @Override
    public void onDeleteSucces(String message) {

    }

    @Override
    public void onUndoSuccess(String message) {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showData(ArrayList<User> list) {
        adapter.update(list);
    }

    @Override
    public void showDataOrder() {
        adapter.order();
    }

    @Override
    public void showDataInverseOrder() {
        adapter.inverseOrder();
    }




    @Override
    public void onEditUser(User user) {
        Toast.makeText(getActivity(), "Se quiere editar el usuario: " + user.getNombreCompleto(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteUser(User user) {
        Toast.makeText(getActivity(), "Se quiere eliminar el usuario: " + user.getNombreCompleto(),Toast.LENGTH_SHORT).show();
    }
}