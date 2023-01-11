package com.example.indusbm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ViewHolder> {
    List<ElementClass> myList;
    Context context;
    String build_string_from_random;

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

    // Create a storage reference from our app
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ElementClass current = myList.get(position);
        holder.name.setText(current.getName());
        holder.description.setText(current.getDescription());
        holder.temperature.setText(current.getTemperature() +"");
        holder.puissance.setText(current.getPuissance()+"");
        holder.vibration.setText(current.getVibration()+"");
        holder.debit.setText(current.getDebit()+"");
        holder.frequency.setText(current.getFrenquence()+"");
        holder.editerinfo.setText(current.getEditerinfo()+"");
        holder.editertime.setText(current.getEditertime()+" ");
        holder.uploader.setText(current.getUploader()+"");

        // Get reference to the file

        build_string_from_random = "machines PDP/" + current.getImageUrl();
        StorageReference reference = storageRef.child(build_string_from_random);

        // Create file metadata including the content type
        StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentType("image/jpg")
                .setCustomMetadata("myCustomProperty", "myValue")
                .build();

        // Update metadata properties
        final long ONE_MEGABYTE = 1024 * 1024 * 10;
        reference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                holder.pdp.setImageBitmap(bmp);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        });
        //comparing references
        float t,v,f,d,p;
        t = Math.abs(Float.parseFloat(current.getTemperature()) -
                Float.parseFloat(current.getTemperatureRef()) );
        v = Math.abs(Float.parseFloat(current.getVibration()) -
                Float.parseFloat(current.getVibrationRef()) );
        f = Math.abs(Float.parseFloat(current.getFrenquence()) -
                Float.parseFloat(current.getFrenquenceRef()) );
        d = Math.abs(Float.parseFloat(current.getDebit()) -
                Float.parseFloat(current.getDebitRef()) );
        p = Math.abs(Float.parseFloat(current.getPuissance()) -
                Float.parseFloat(current.getPuissanceRef()) );
        if (t > 2 && t < 3){ //we have + or - 2 with abs = +2
            holder.temperatureL.setBackgroundResource(R.color.amber);
        }else if (t >= 3){
            holder.temperatureL.setBackgroundResource(R.color.red);
        }else if (t < 1){
            holder.temperatureL.setBackgroundResource(R.color.green);
        }
        ////////
        if (v > 2 && v < 3){ //we have + or - 2 with abs = +2
            holder.vibrationL.setBackgroundResource(R.color.amber);
        }else if (v >= 3){
            holder.vibrationL.setBackgroundResource(R.color.red);
        }
        else if (v < 1){
            holder.vibrationL.setBackgroundResource(R.color.green);
        }
        //////////
        if (f > 2 && f < 3){ //we have + or - 2 with abs = +2
            holder.frequencyL.setBackgroundResource(R.color.amber);
        }else if (f >= 3){
            holder.frequencyL.setBackgroundResource(R.color.red);
        }
        else if (f < 1){
            holder.frequencyL.setBackgroundResource(R.color.green);
        }
        //////
        if (d > 2 && d < 3){ //we have + or - 2 with abs = +2
            holder.debitL.setBackgroundResource(R.color.amber);
        }else if (d >= 3){
            holder.debitL.setBackgroundResource(R.color.red);
        }
        else if (d < 1){
            holder.debitL.setBackgroundResource(R.color.green);
        }
        ///////
        if (p > 2 && p < 3){ //we have + or - 2 with abs = +2
            holder.puissanceL.setBackgroundResource(R.color.amber);
        }else if (p >= 3){
            holder.puissanceL.setBackgroundResource(R.color.red);
        }
        else if (p < 1){
            holder.puissanceL.setBackgroundResource(R.color.green);
        }
        //holder.etatL.setBackgroundResource();
        //holder.etat.setText();
        boolean done = false;
        if (( (t >= 3) || (v >= 3) || (f >= 3) || (d >= 3) || (p >= 3) ) && !done ){ //we have + or - 2 with abs = +2
            holder.etatL.setBackgroundResource(R.color.red);
            holder.etat.setText("Bad");
            done = true;
        }else if(( (t > 2 && t < 3) || (v > 2 && v < 3) || (f > 2 && f < 3) || (d > 2 && d < 3) || (p > 2 && p < 3) )&& !done ){
            holder.etatL.setBackgroundResource(R.color.amber);
            holder.etat.setText("Amber");
            done = true;
        }else if (( (t > 1 && t <= 2) || (v > 1 && v <= 2) || (f > 1 && f <= 2) || (d > 1 && d <= 2) || (p > 1 && p <= 2) )&& !done ){
            holder.etatL.setBackgroundResource(R.color.blue);
            holder.etat.setText("Good");
            done = true;
        }
        else if (( (t < 1) || (v < 1) || (f < 1) || (d < 1) || (p < 1) ) && !done ){
            holder.etatL.setBackgroundResource(R.color.green);
            holder.etat.setText("Very Good");
            done = true;
        }
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    //FIREBASE REAL TIME DATABASE



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, description, etat, frequency, temperature, puissance, vibration, debit;
        TextView etatL,frequencyL, temperatureL, puissanceL, vibrationL, debitL;
        TextView editerinfo, editertime, uploader;
        Button btnEDIT;
        ImageView pdp;
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
            uploader = itemView.findViewById(R.id.uploader);
            btnEDIT = itemView.findViewById(R.id.btnEDIT);
            pdp = itemView.findViewById(R.id.pdp);
            btnEDIT.setOnClickListener(view -> {
                Intent intent = new Intent(context, DataListUser.class);
                intent.putExtra("card name", name.getText().toString());
                intent.putExtra("card description", description.getText().toString());
                intent.putExtra("card editerinfo", editerinfo.getText().toString());
                //info to avoid rewriting
                intent.putExtra("t", temperature.getText().toString());
                intent.putExtra("p", puissance.getText().toString());
                intent.putExtra("v", vibration.getText().toString());
                intent.putExtra("d", debit.getText().toString());
                intent.putExtra("f", frequency.getText().toString());
                context.startActivity(intent);
            });

        }
    }
}
