package com.example.indusbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView register;
    EditText emailEditText, passwordEditText;
    Button loginBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        register = findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(view ->
            startActivity(new Intent(this, SignUp.class)));
        //action to signIn
        loginBtn.setOnClickListener(view -> {
            if (TextUtils.isEmpty(emailEditText.getText().toString()) ||
                    TextUtils.isEmpty(passwordEditText.getText().toString())) {
                /* I made if check only if the input values are not empty
                because other test about informations is checked by signIn() in else bloc
                 */
                Toast.makeText(this, "please fill all the informations", Toast.LENGTH_SHORT).show();
            } else {
                signIn(emailEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

    }


    //to check if the user is already connected, directly go to home page
    //according to activity lifecycle onstart comes before on create
    //so the reloading of checking information comes before reload on create
    //less time
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    //fct() to sign in with email and password
    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(MainActivity.this, "connected succesfuly", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, HomeActivity.class));
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(MainActivity.this, "Invalid information", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}