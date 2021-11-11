package com.example.controljornada.ui.calendario;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controljornada.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public final TextView diaDelMes;
    private final CalendarAdapter.OnItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        diaDelMes = itemView.findViewById(R.id.tvCeldaDiaDelMes);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition(),(String) diaDelMes.getText());
    }
}
