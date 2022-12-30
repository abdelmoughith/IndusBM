package com.example.indusbm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ViewHolder> {
    List<ElementClass> myList;
    Context context;

    public AdapterItems(List<ElementClass> myList, Context context) {
        this.myList = myList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ElementClass current = myList.get(position);
        holder.name.setText(current.getName());
        holder.description.setText(current.getDescription());
        holder.etat.setText(current.getEtat());
        holder.temperature.setText(current.getTemperature() +"");
        holder.puissance.setText(current.getPuissance()+"");
        holder.vibration.setText(current.getVibration()+"");
        holder.debit.setText(current.getDebit()+"");
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, description, etat, temperature, puissance, vibration, debit;
        TextView etatL, temperatureL, puissanceL, vibrationL, debitL;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            etat = itemView.findViewById(R.id.etat);
            temperature = itemView.findViewById(R.id.temperature);
            puissance = itemView.findViewById(R.id.puissance);
            vibration = itemView.findViewById(R.id.vibration);
            debit = itemView.findViewById(R.id.debit);
            //first line green or red
            etatL = itemView.findViewById(R.id.etatLine);
            temperatureL = itemView.findViewById(R.id.temperatureLine);
            puissanceL = itemView.findViewById(R.id.puissanceLine);
            vibrationL = itemView.findViewById(R.id.vibrationLine);
            debitL = itemView.findViewById(R.id.debitLine);
        }
    }
}
