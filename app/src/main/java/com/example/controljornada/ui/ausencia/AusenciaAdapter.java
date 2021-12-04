package com.example.controljornada.ui.ausencia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controljornada.R;
import com.example.controljornada.data.model.Ausencia;

import java.util.ArrayList;

public class AusenciaAdapter extends RecyclerView.Adapter<AusenciaAdapter.ViewHolder> {
    ArrayList<Ausencia> list;
    private View.OnClickListener listener;
    int[] images;

    public AusenciaAdapter(int[] images){
        this.images = images;
        this.list = new ArrayList<Ausencia>();
        list.add(new Ausencia("Enfermedad"));
        list.add(new Ausencia("Personal"));
        list.add(new Ausencia("Festivo"));
        list.add(new Ausencia("Vacaciones"));
        list.add(new Ausencia("Recuperación de horas"));
        list.add(new Ausencia("Maternidad"));
        list.add(new Ausencia("Paternidad"));
        list.add(new Ausencia("Otros"));

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ausencia_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rbAusencia.setText(list.get(position).getMotivoAusencia());
        holder.imageView.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton rbAusencia;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rbAusencia = itemView.findViewById(R.id.rbAusencia);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
