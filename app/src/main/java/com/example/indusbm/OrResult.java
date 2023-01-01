package com.example.indusbm;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

public class OrResult extends AppCompatActivity {

    VideoView resultVideo;
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

        resultVideo = findViewById(R.id.resultVideo);
    }
}