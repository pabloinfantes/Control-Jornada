package com.example.controljornada.ui.ajustes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controljornada.R;
import com.example.controljornada.data.model.Ajustes;

import java.util.ArrayList;

public class AjustesAdapter extends RecyclerView.Adapter<AjustesAdapter.ViewHolder> {
    ArrayList<Ajustes> list;

    public AjustesAdapter() {
        this.list = new ArrayList<Ajustes>();
        list.add(new Ajustes("Generar Informes"));
        list.add(new Ajustes("Sobre Nosotros"));
        list.add(new Ajustes("Manual del usuario"));
        list.add(new Ajustes("Version"));
        list.add(new Ajustes("Preferencias"));
        list.add(new Ajustes("Generar Informes"));
        list.add(new Ajustes("Sobre Nosotros"));
        list.add(new Ajustes("Manual del usuario"));
        list.add(new Ajustes("Version"));
        list.add(new Ajustes("Preferencias"));
        list.add(new Ajustes("Generar Informes"));
        list.add(new Ajustes("Sobre Nosotros"));
        list.add(new Ajustes("Manual del usuario"));
        list.add(new Ajustes("Version"));
        list.add(new Ajustes("Preferencias"));


    }

    @NonNull
    @Override
    public AjustesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ajustes_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AjustesAdapter.ViewHolder holder, int position) {
        holder.button.setText(list.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btAjustesRv);
        }
    }
}
