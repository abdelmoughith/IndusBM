package com.example.indusbm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DataListUser extends AppCompatActivity {

    TextView editerinfo, name, description, uploader;
    FirebaseUser user;
    Button savechanges;
    //table info
    TextView  frequency, temperature, puissance, vibration, debit;
    String uploaderString ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list_user);
        editerinfo = findViewById(R.id.editerinfo);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        uploader = findViewById(R.id.uploader);

        savechanges = findViewById(R.id.savechanges);
        name.setText(getIntent().getExtras().getString("card name"));
        Toast.makeText(this, getIntent().getExtras().getString("card name"), Toast.LENGTH_SHORT).show();
        description.setText(getIntent().getExtras().getString("card description"));
        editerinfo.setText(getIntent().getExtras().getString("card editerinfo"));
        //in the table informations
        temperature = findViewById(R.id.temperature);
        puissance = findViewById(R.id.puissance);
        vibration = findViewById(R.id.vibration);
        debit = findViewById(R.id.debit);
        frequency = findViewById(R.id.frequency);
        temperature.setText(getIntent().getExtras().getString("t"));
        puissance.setText(getIntent().getExtras().getString("p"));
        vibration.setText(getIntent().getExtras().getString("v"));
        frequency.setText(getIntent().getExtras().getString("f"));
        debit.setText(getIntent().getExtras().getString("d"));


        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uploaderString = user.getDisplayName();
        }
        uploader.setText(uploaderString);
        savechanges.setOnClickListener(view -> {
            if (!(TextUtils.isEmpty(temperature.getText().toString()) ||
                    TextUtils.isEmpty(puissance.getText().toString()) ||
                    TextUtils.isEmpty(vibration.getText().toString()) ||
                    TextUtils.isEmpty(debit.getText().toString()) ||
                    TextUtils.isEmpty(frequency.getText().toString()))) {
                float t,v,f,d,p;
                t = Float.parseFloat(temperature.getText().toString());
                v = Float.parseFloat(vibration.getText().toString());
                f = Float.parseFloat(frequency.getText().toString());
                d = Float.parseFloat(debit.getText().toString());
                p = Float.parseFloat(puissance.getText().toString());
                ElementClass elementToUpload = new ElementClass(
                        String.valueOf(t),//temperature
                        String.valueOf(v),//vibration
                        String.valueOf(f),//frequency
                        String.valueOf(d),//debit
                        String.valueOf(p),//powerful
                        uploaderString, // oploader
                        getTime() //no uploader so no time
                );


                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Machinery Cards");
                myRef.
                        child(getIntent().getExtras().getString("card name")).
                        child("INFO").
                        setValue(elementToUpload)
                        .addOnSuccessListener(aVoid -> {
                            // Write was successful!
                            Toast.makeText(this, "new machine references created By " + name, Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            // Write failed
                            Toast.makeText(this, "Error while uploading", Toast.LENGTH_SHORT).show();
                        });
            } else {
                Toast.makeText(this, "fill all the informations", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update(String path_in_DB, String T, String P, String V, String D, String F){

    }
    private String getTime(){
        DateTimeFormatter dtf = null;
        ZonedDateTime now = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm");
            now = ZonedDateTime.now();
            return dtf.format(now);
        }
        return "hhh";
    }
}