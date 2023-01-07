package com.example.indusbm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterString extends RecyclerView.Adapter<AdapterString.ViewHolder> {
    Context context;
    List<ElementRappotClass> list;

    public AdapterString(List<ElementRappotClass> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterString.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.elementrapport, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterString.ViewHolder holder, int position) {
        ElementRappotClass current = list.get(position);
        holder.titleY.setText(current.getName()+" : ");
        holder.needs_in.setText(current.getND());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView needs_in, titleY;
        public ViewHolder(View itemView) {
            super(itemView);
            titleY = itemView.findViewById(R.id.name_rapport);
            needs_in = itemView.findViewById(R.id.needs_in);
        }
    }
}
