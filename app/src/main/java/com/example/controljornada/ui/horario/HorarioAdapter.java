package com.example.controljornada.ui.horario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controljornada.R;
import com.example.controljornada.data.model.Horario;

import java.util.ArrayList;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.ViewHolder>{
    ArrayList<Horario> list;

    public HorarioAdapter(){
        this.list = new ArrayList<Horario>();
        list.add(new Horario("08:00","14:00","15:00","18:00"));
        list.add(new Horario("08:00","14:00","15:00","19:00"));

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horario_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvHorIzq.setText(list.get(position).getHorarioEntrada());
        holder.tvHorDer.setText(list.get(position).getHorarioSalida());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvHorIzq;
        TextView tvHorDer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHorIzq = itemView.findViewById(R.id.tvHorarioIzq);
            tvHorDer = itemView.findViewById(R.id.tvHorarioDer);
        }
    }
}
