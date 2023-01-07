package com.example.indusbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class AddActivity extends AppCompatActivity {

    //private FirebaseAuth mAuth;
    FirebaseUser user;
    TextView username;
    EditText nameinput, temperature, powerful, vibration, frequency, debit, description;
    //realtime DB
    ImageButton save;
    String name = "";
    Button add_img;
    ImageView image_To_Upload;
    String RANDOM_URL = "";
    int PUSH_RESULTAT = 0;
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
        add_img = findViewById(R.id.add_img);
        image_To_Upload = findViewById(R.id.image_To_Upload);
        //mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            name = user.getDisplayName();
        }
        username.setText(name);
        save.setOnClickListener(view -> upload());
        add_img.setOnClickListener(view -> {
            //permission step
            if (checkPer(getApplicationContext())){
                imageChooser();
            }else {
                requestPermissions();
            }
        });
    }

    private void upload() {
        if (   !(TextUtils.isEmpty(nameinput.getText().toString()) ||
                TextUtils.isEmpty(temperature.getText().toString()) ||
                TextUtils.isEmpty(powerful.getText().toString()) ||
                TextUtils.isEmpty(vibration.getText().toString()) ||
                TextUtils.isEmpty(frequency.getText().toString()) ||
                TextUtils.isEmpty(debit.getText().toString()) ||
                TextUtils.isEmpty(description.getText().toString()) ||
                !hasImage(image_To_Upload)) ) {

            ElementClass elementToUpload = new ElementClass(
                    nameinput.getText().toString(), //machine name
                    description.getText().toString(), //description
                    temperature.getText().toString(),//temperature ref
                    vibration.getText().toString(),//vibration ref
                    frequency.getText().toString(),//frequency ref
                    debit.getText().toString(),//debit ref
                    powerful.getText().toString(),//powerful ref
                    name //created by
            );
            ElementClass elementToUploadINFO = new ElementClass(
                    "0", //temperature
                    "0", //vibration
                    "0", //frequency
                    "0", //debit
                    "0", //powerful
                    "", //oploader
                    ""  //no uploader so no time
            );
            /////////////////////////////FB
            //upload image url

            elementToUpload.setImageUrl(RANDOM_URL);
            /////////////////////////////////////////////////////////
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Machinery Cards");
            myRef.
                    child(nameinput.getText().toString()).
                    child("REF").
                    setValue(elementToUpload)
                    .addOnSuccessListener(aVoid -> {
                        if (PUSH_RESULTAT == 1){
                            // Write was successful!
                            Toast.makeText(this, "new machine references created By "+ name, Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    })
                    .addOnFailureListener(e -> {
                        // Write failed
                        Toast.makeText(this, "Error while uploading", Toast.LENGTH_SHORT).show();
                    });
            DatabaseReference myRef2 = FirebaseDatabase.getInstance().getReference("Machinery Cards");
            myRef2.
                    child(nameinput.getText().toString()).
                    child("INFO").
                    setValue(elementToUploadINFO)
                    .addOnSuccessListener(aVoid -> {
                        if (PUSH_RESULTAT == 1){
                            // Write was successful!
                            Toast.makeText(this, "new machine references created By "+ name, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(e -> {
                        // Write failed
                        Toast.makeText(this, "Error while uploading", Toast.LENGTH_SHORT).show();
                    });
        }else{
            Toast.makeText(this, "fill all the informations", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean checkPer(Context context) {
        int callPermissionResult =
                ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        return callPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissions() {
        ActivityCompat.requestPermissions(
                AddActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                101//any code
        );
    }

    //check image if exists
    private boolean hasImage(@NonNull ImageView view) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);

        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
        }

        return hasImage;
    }
    int SELECT_PICTURE = 200;
    // this function is triggered when
    // the Select Image Button is clicked
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    image_To_Upload.setImageURI(selectedImageUri);
                }
            }
        }
        Random random = new Random();
        StorageReference storageRef = FirebaseStorage.getInstance().getReference("machines PDP");
        RANDOM_URL = String.valueOf(random.nextInt(1000000));
        StorageReference imagesRef = storageRef.child(RANDOM_URL);

        // Get the data from an ImageView as bytes
        image_To_Upload.setDrawingCacheEnabled(true);
        image_To_Upload.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) image_To_Upload.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] dataz = baos.toByteArray();

        UploadTask uploadTask = imagesRef.putBytes(dataz);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                PUSH_RESULTAT = 0;
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                PUSH_RESULTAT = 1;
            }
        });
    }
}