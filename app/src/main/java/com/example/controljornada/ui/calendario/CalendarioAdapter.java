package com.example.controljornada.ui.calendario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controljornada.R;
import com.example.controljornada.data.model.Horario;

import java.util.ArrayList;
import java.util.List;

public class CalendarioAdapter extends RecyclerView.Adapter<CalendarioAdapter.ViewHolder>{
    private ArrayList<Horario> list;


    public CalendarioAdapter(ArrayList<Horario> list ) {
        this.list = list;
    }


    @NonNull
    @Override
    public CalendarioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarioAdapter.ViewHolder holder, int position) {
        holder.tvUsuario.setText(list.get(position).getEmailUser().toString());
        holder.tvNumeroDeHoras.setText(String.valueOf(list.get(position).getNumeroHoras()));
        holder.tvSitioTrabajoMañana.setText(list.get(position).getLugarTrabajoMñn().toString());
        holder.tvSitioTrabajoTarde.setText(list.get(position).getLugarTrabajoTarde().toString());
        holder.tvHorarioEntradaMañana.setText(list.get(position).getHorarioEntradaMñn().toString());
        holder.tvHorarioSalidaMañana.setText(list.get(position).getHorarioSalidaMñn().toString());
        holder.tvHorarioEntradaTarde.setText(list.get(position).getHorarioEntradaTarde().toString());
        holder.tvHorarioSalidaTarde.setText(list.get(position).getHorarioSalidaTarde().toString());
    }

    /**
     * Este metodo devuelve el numero de elementos del adapter y es utilizado por el sistema operativo cuando se
     * incializar el componentente Recycler View y si se deja a 0, NUNCA se muestran los elemementos del adapter
     * en el recycler ya que no se llama al metodo onCreateViewHolder
     * @return
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvUsuario;
        TextView tvNumeroDeHoras;
        TextView tvSitioTrabajoMañana;
        TextView tvSitioTrabajoTarde;
        TextView tvHorarioEntradaMañana;
        TextView tvHorarioSalidaMañana;
        TextView tvHorarioEntradaTarde;
        TextView tvHorarioSalidaTarde;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             tvUsuario = itemView.findViewById(R.id.tvUsuario);
             tvNumeroDeHoras= itemView.findViewById(R.id.tvNumeroDeHoras);
             tvSitioTrabajoMañana = itemView.findViewById(R.id.tvSitioTrabajoMañana);
             tvSitioTrabajoTarde = itemView.findViewById(R.id.tvSitioTrabajoTarde);
             tvHorarioEntradaMañana = itemView.findViewById(R.id.tvHorarioEntradaMañana);
             tvHorarioSalidaMañana= itemView.findViewById(R.id.tvHorarioSalidaMañana);
             tvHorarioEntradaTarde =  itemView.findViewById(R.id.tvHorarioEntradaTarde);
             tvHorarioSalidaTarde= itemView.findViewById(R.id.tvHorarioSalidaTarde);

        }


    }


    public void update(List<Horario> list) {
        this.list.clear();
        this.list.addAll(list);

        // OJOOOOOOOO PARA EL EXAMEN ESTO HAY QUE HACERLO SI O SI
        notifyDataSetChanged();

    }

}
