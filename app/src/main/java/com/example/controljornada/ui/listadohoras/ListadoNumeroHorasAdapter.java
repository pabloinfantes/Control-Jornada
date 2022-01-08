package com.example.controljornada.ui.listadohoras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.controljornada.R;
import com.example.controljornada.data.model.User;
import com.example.controljornada.data.model.UserComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListadoNumeroHorasAdapter extends RecyclerView.Adapter<ListadoNumeroHorasAdapter.ViewHolder> {

    private ArrayList<User> list;
    private OnManageListadoListener listener;



    public interface OnManageListadoListener{

        void onEditUser(User user);

        void onDeleteUser(User user);
    }

    public ListadoNumeroHorasAdapter(ArrayList<User> list, OnManageListadoListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListadoNumeroHorasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListadoNumeroHorasAdapter.ViewHolder holder, int position) {

        ColorGenerator generator = ColorGenerator.MATERIAL;
        //Generar color aleatorio
        int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .toUpperCase()
                .bold()
                .endConfig()
                .buildRound(list.get(position).getNombreCompleto().substring(0,1), color);
        holder.icon.setImageDrawable(drawable);
        holder.bind(list.get(position),listener);
        holder.tvNombreUsuario.setText(list.get(position).getNombreCompleto());
        holder.tvNumeroHoras.setText(list.get(position).getNumeroHorasMensuales());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<User> list) {
        this.list.clear();
        this.list.addAll(list);

        notifyDataSetChanged();

    }
    public void undo(User deleted){
        list.add(deleted);
        notifyDataSetChanged();
    }

    public void delete(User deleted) {
        list.remove(deleted);
        notifyDataSetChanged();
    }

    public void order() {
        Collections.sort(list);
        notifyDataSetChanged();
    }

    public void inverseOrder() {
        Collections.reverse(list);
        notifyDataSetChanged();
    }


    public void orderByNumeroHoras() {
        Collections.sort(list,new UserComparator());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreUsuario;
        TextView tvNumeroHoras;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombreUsuario=itemView.findViewById(R.id.tvUserName);
            tvNumeroHoras=itemView.findViewById(R.id.tvNumeroHorasLabel);
            icon = itemView.findViewById(R.id.icon);
        }


        public void bind(User user, OnManageListadoListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEditUser(user);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onDeleteUser(user);

                    return true;
                }
            });
        }
    }
}
