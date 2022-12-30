package com.example.indusbm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.dashbord:
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new FirstFragment()).commit();
                    return true;
            }
            return false;
        });
        loadFragment(new FirstFragment());


    }
    void loadFragment(Fragment fragment) {
        //to attach fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        //AlertDialog
        AlertDialog.Builder alBuilder;
        alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("Would you quit ?");
        alBuilder.setCancelable(false);
        alBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });
        alBuilder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        //after defining all details of our alert now we can show it to user
        AlertDialog dialog = alBuilder.create();
        dialog.show();
    }
}