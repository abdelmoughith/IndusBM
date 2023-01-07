package com.example.indusbm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.dashbord:
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new FirstFragment()).commit();
                    return true;
                case R.id.or:
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new Or()).commit();
                    return true;
                case R.id.rapport:
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new Rapport()).commit();
                    return true;
            }
            return false;
        });
        loadFragment(new FirstFragment());// just for test it must be new FirstFragment()
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