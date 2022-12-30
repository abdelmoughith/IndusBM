package com.example.indusbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUp extends AppCompatActivity {

    ImageButton backButton;
    EditText email, fullname, password;
    Button continueBtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        backButton = findViewById(R.id.backButton);
        email = findViewById(R.id.email);
        fullname = findViewById(R.id.fullname);
        password = findViewById(R.id.password);
        continueBtn = findViewById(R.id.continueBtn);
        backButton.setOnClickListener(view -> {
            finish();
        });
        mAuth = FirebaseAuth.getInstance();
        continueBtn.setOnClickListener(view -> {
            //check if email ends with @gmail.com
            String checkemail="";
            try {
                checkemail = email.getText().toString().substring(email.length() -10, email.length());
            }catch(java.lang.StringIndexOutOfBoundsException e) {
                checkemail= "invalid";
            }
            if( TextUtils.isEmpty(email.getText().toString()) ||
                    TextUtils.isEmpty(fullname.getText().toString()) ||
                    TextUtils.isEmpty(password.getText().toString())){
                //check if an input value is empty
                Toast.makeText(SignUp.this, "Please fill in all the information", Toast.LENGTH_SHORT).show();
            }else if(!checkemail.equals("@gmail.com")){
                // check if adress isnt gmail
                Toast.makeText(SignUp.this, "you need a valid gmail adress", Toast.LENGTH_SHORT).show();
            }else{
                //alright user isnt catched by other tests so the informations is correct
                createAccount(email.getText().toString(), fullname.getText().toString(), password.getText().toString());
            }
        });


    }
    private void createAccount(String email, String userName, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(SignUp.this, "Account created",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, HomeActivity.class));
                        setUserName(userName);

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(SignUp.this, "Invalid information",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void setUserName(String userName){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(userName)
                //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                    }
                });
    }
}