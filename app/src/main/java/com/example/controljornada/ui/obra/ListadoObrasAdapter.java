package com.example.controljornada.ui.obra;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.controljornada.R;
import com.example.controljornada.data.model.Obra;
import com.example.controljornada.data.model.ObraComparator;
import com.example.controljornada.data.model.User;
import com.example.controljornada.data.model.UserComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Esta clase es la encargada de dar controlar la vista de los recyclerview
 * @author pablo
 *
 */
public class ListadoObrasAdapter extends RecyclerView.Adapter<ListadoObrasAdapter.ViewHolder> {

    private ArrayList<Obra> list;
    private OnManageListadoListener listener;



    public interface OnManageListadoListener{

        void onEditObra(Obra obra);

        void onDeleteObra(Obra obra);
    }

    public ListadoObrasAdapter(ArrayList<Obra> list, OnManageListadoListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.obra_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ColorGenerator generator = ColorGenerator.MATERIAL;
        //Generar color aleatorio
        int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .toUpperCase()
                .bold()
                .endConfig()
                .buildRound(list.get(position).getName().substring(0,1), color);
        holder.icon.setImageDrawable(drawable);
        holder.bind(list.get(position),listener);
        holder.tvName.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<Obra> list) {
        this.list.clear();
        this.list.addAll(list);

        notifyDataSetChanged();

    }
    public void undo(Obra deleted){
        list.add(deleted);
        notifyDataSetChanged();
    }

    public void delete(Obra deleted) {
        list.remove(deleted);
        notifyDataSetChanged();
    }

    public void order(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Boolean order = prefs.getBoolean("orderObra",false);
        if (order){
            Collections.sort(list);
            notifyDataSetChanged();
        }else{
            Toast.makeText(context,"Si desea ordenar debe activar el boton en los ajustes",Toast.LENGTH_SHORT).show();
        }

    }



    public void orderByDescripcion() {
        Collections.sort(list,new ObraComparator());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView tvName;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);
            icon = itemView.findViewById(R.id.icon);
        }


        public void bind(Obra obra, OnManageListadoListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEditObra(obra);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onDeleteObra(obra);

                    return true;
                }
            });
        }
    }
}
