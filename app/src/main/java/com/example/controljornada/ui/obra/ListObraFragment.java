package com.example.controljornada.ui.obra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controljornada.R;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.databinding.FragmentListObraBinding;
import com.example.controljornada.ui.base.BaseDialogFragment;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es la encargada de gestionar lo que ocurre en esta vista en concreto
 * @author pablo
 *
 */
public class ListObraFragment extends Fragment implements ListadoObrasContract.View,ListadoObrasAdapter.OnManageListadoListener{

    private FragmentListObraBinding binding;
    private ListadoObrasAdapter adapter;
    private ListadoObrasContract.Presenter presenter;

    private Obra obraDeleted;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter = new ListadoObrasPresenter(this);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentListObraBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvNumHoras();
        presenter.load();
        initFab();
    }



    private void initFab() {
        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListObraFragmentDirections.ActionListObraFragmentToObraManageFragment action = ListObraFragmentDirections.actionListObraFragmentToObraManageFragment(null);
                NavHostFragment.findNavController(ListObraFragment.this).navigate(action);
            }
        });
    }

    private void initRvNumHoras() {
        adapter = new ListadoObrasAdapter(new ArrayList<>(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.rvObra.setLayoutManager(linearLayoutManager);
        binding.rvObra.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_listadoobras_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_order_obras:
                presenter.order();
                return true;
            case R.id.action_order_bydescription:
                adapter.orderByDescripcion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {

    }

    @Override
    public void onDeleteSucces(String message) {
        Snackbar.make(getView(), message, BaseTransientBottomBar.LENGTH_SHORT).setAction(getString(R.string.undo), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.undo(obraDeleted);
            }
        }).show();
        adapter.delete(obraDeleted);
        if (adapter.getItemCount() ==0 )
            showNoData();
    }

    @Override
    public void onUndoSuccess(String message) {
        adapter.undo(obraDeleted);
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showData(ArrayList<Obra> list) {
        adapter.update(list);
    }

    @Override
    public void showDataOrder() {
        adapter.order(getContext());
    }


    @Override
    public void onEditObra(Obra obra) {
        ListObraFragmentDirections.ActionListObraFragmentToObraManageFragment action = ListObraFragmentDirections.actionListObraFragmentToObraManageFragment(obra);
        NavHostFragment.findNavController(ListObraFragment.this).navigate(action);
    }


    @Override
    public void onDeleteObra(Obra obra) {

        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, getString(R.string.title_delete_obra));
        bundle.putString(BaseDialogFragment.MESSAGE, String.format(getString(R.string.message_delete_obra), obra.getShortname()));


        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialogFragment.REQUEST, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //Si la respuesta del usuario es true, se llama al presentador
                if (result.getBoolean(BaseDialogFragment.KEY_BUNDLE)) {
                    obraDeleted = obra;
                    presenter.delete(obra);

                }
            }
        });

        NavHostFragment.findNavController(this).navigate(R.id.action_listObraFragment_to_baseDialogFragment, bundle);
    }




}