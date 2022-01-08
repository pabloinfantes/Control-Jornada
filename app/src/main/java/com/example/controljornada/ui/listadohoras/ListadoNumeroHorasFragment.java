package com.example.controljornada.ui.listadohoras;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.example.controljornada.R;
import com.example.controljornada.data.model.User;
import com.example.controljornada.databinding.FragmentListadoNumeroHorasBinding;
import com.example.controljornada.ui.base.BaseDialogFragment;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class ListadoNumeroHorasFragment extends Fragment implements ListadoNumeroHorasContract.View ,ListadoNumeroHorasAdapter.OnManageListadoListener{

    private FragmentListadoNumeroHorasBinding binding;
    private ListadoNumeroHorasAdapter adapter;
    private ListadoNumeroHorasContract.Presenter presenter;
    
    private User userDeleted;

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
        initFab();
    }

    private void initFab() {
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListadoNumeroHorasFragmentDirections.ActionListadoNumeroHorasFragmentToListManageFragment action = ListadoNumeroHorasFragmentDirections.actionListadoNumeroHorasFragmentToListManageFragment(null);
                NavHostFragment.findNavController(ListadoNumeroHorasFragment.this).navigate(action);
            }
        });
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
        Log.d(TAG,"onDeleteSucces");
        Snackbar.make(getView(), message, BaseTransientBottomBar.LENGTH_SHORT).setAction(getString(R.string.undo), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.undo(userDeleted);
            }
        }).show();
        adapter.delete(userDeleted);
        if (adapter.getItemCount() ==0 )
            showNoData();
    }

    @Override
    public void onUndoSuccess(String message) {
        adapter.undo(userDeleted);
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
        ListadoNumeroHorasFragmentDirections.ActionListadoNumeroHorasFragmentToListManageFragment action = ListadoNumeroHorasFragmentDirections.actionListadoNumeroHorasFragmentToListManageFragment(user);
        NavHostFragment.findNavController(ListadoNumeroHorasFragment.this).navigate(action);
    }

    @Override
    public void onDeleteUser(User user) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, getString(R.string.title_delete_user));
        bundle.putString(BaseDialogFragment.MESSAGE, String.format(getString(R.string.message_delete_user), user.getNombreCorto()));


        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialogFragment.REQUEST, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //Si la respuesta del usuario es true, se llama al presentador
                if (result.getBoolean(BaseDialogFragment.KEY_BUNDLE)) {
                    userDeleted = user;
                    presenter.delete(user);

                }
            }
        });

        NavHostFragment.findNavController(this).navigate(R.id.action_listadoNumeroHorasFragment_to_baseDialogFragment, bundle);
    }
}