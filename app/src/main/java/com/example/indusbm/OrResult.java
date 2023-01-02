package com.example.indusbm;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import pl.droidsonroids.gif.GifImageView;

public class OrResult extends AppCompatActivity {

    GifImageView gifImageViewRESULT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_or_result);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView good = findViewById(R.id.tc);

        textView1.setText(getIntent().getExtras().getDouble("res1")+"");
        textView2.setText(getIntent().getExtras().getDouble("res2")+"");
        textView3.setText(getIntent().getExtras().getDouble("res3")+"");
        good.setText(getIntent().getExtras().getDouble("res4")+"");
        gifImageViewRESULT = findViewById(R.id.resultIMG);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.check);
        gifImageViewRESULT.setImageURI(uri);
    }
}