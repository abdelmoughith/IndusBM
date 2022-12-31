package com.example.indusbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AddActivity extends AppCompatActivity {

    //private FirebaseAuth mAuth;
    FirebaseUser user;
    TextView username;
    EditText nameinput, temperature, powerful, vibration, frequency, debit, description;
    //realtime DB
    ImageButton save;
    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);
        username = findViewById(R.id.username);
        nameinput = findViewById(R.id.name);
        temperature = findViewById(R.id.temperature);
        powerful = findViewById(R.id.puissance);
        vibration = findViewById(R.id.vibration);
        frequency = findViewById(R.id.frequency);
        debit = findViewById(R.id.debit);
        description = findViewById(R.id.description);
        save = findViewById(R.id.save);
        //mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            name = user.getDisplayName();
        }
        username.setText(name);
        save.setOnClickListener(view -> upload());
    }

    private void upload() {
        if (   !(TextUtils.isEmpty(nameinput.getText().toString()) ||
                TextUtils.isEmpty(temperature.getText().toString()) ||
                TextUtils.isEmpty(powerful.getText().toString()) ||
                TextUtils.isEmpty(vibration.getText().toString()) ||
                TextUtils.isEmpty(frequency.getText().toString()) ||
                TextUtils.isEmpty(debit.getText().toString()) ||
                TextUtils.isEmpty(description.getText().toString()) ) ) {

            ElementClass elementToUpload = new ElementClass(
                    nameinput.getText().toString(),
                    description.getText().toString(),
                    temperature.getText().toString(),
                    vibration.getText().toString(),
                    frequency.getText().toString(),
                    debit.getText().toString(),
                    powerful.getText().toString(),
                    getTime(),
                    name
            );
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Machinery Cards");
            myRef.
                    child(nameinput.getText().toString()).
                    setValue(elementToUpload)
                    .addOnSuccessListener(aVoid -> {
                        // Write was successful!
                        Toast.makeText(this, "new machine references created By "+ name, Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        // Write failed
                        Toast.makeText(this, "Error while uploading", Toast.LENGTH_SHORT).show();
                    });
        }else{
            Toast.makeText(this, "fill all the informations", Toast.LENGTH_SHORT).show();
        }
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