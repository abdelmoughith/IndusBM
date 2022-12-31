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
        holder.temperature.setText(current.getTemperatureRef() +"");
        holder.puissance.setText(current.getPuissanceRef()+"");
        holder.vibration.setText(current.getVibrationRef()+"");
        holder.debit.setText(current.getDebitRef()+"");
        holder.frequency.setText(current.getFrenquenceRef()+"");
        holder.editerinfo.setText(current.getEditerinfo()+"");
        holder.editertime.setText(current.getEditertime()+"");
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    //FIREBASE REAL TIME DATABASE



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, description, etat, frequency, temperature, puissance, vibration, debit;
        TextView etatL,frequencyL, temperatureL, puissanceL, vibrationL, debitL;
        TextView editerinfo, editertime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            etat = itemView.findViewById(R.id.etat);
            temperature = itemView.findViewById(R.id.temperature);
            puissance = itemView.findViewById(R.id.puissance);
            vibration = itemView.findViewById(R.id.vibration);
            debit = itemView.findViewById(R.id.debit);
            frequency = itemView.findViewById(R.id.frequency);
            //first line green or red
            etatL = itemView.findViewById(R.id.etatLine);
            temperatureL = itemView.findViewById(R.id.temperatureLine);
            puissanceL = itemView.findViewById(R.id.puissanceLine);
            vibrationL = itemView.findViewById(R.id.vibrationLine);
            debitL = itemView.findViewById(R.id.debitLine);
            frequencyL = itemView.findViewById(R.id.frequencyLine);
            //
            editerinfo = itemView.findViewById(R.id.editerinfo);
            editertime = itemView.findViewById(R.id.editertime);

        }
    }
}
