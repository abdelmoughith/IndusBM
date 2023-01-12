package com.example.indusbm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class OrResult extends AppCompatActivity {

    GifImageView gifImageViewRESULT;
    PieChart pieChart;
    TextView Muscovite, Chlorite, Albitie, Dolomite, Pyrite, chalcopyrite, Sphalerite;
    TextView carbonates, sulfates;
    TextView khouloud;
    TextView conclusionTeneur;
    ImageView imageofconclusion;
    TableLayout machinemaintenance;
    CardView sohaib , khalid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_or_result);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView good = findViewById(R.id.tc);

        textView1.setText(getIntent().getExtras().getDouble("res1") + "");
        textView2.setText(getIntent().getExtras().getDouble("res2") + "");
        textView3.setText(getIntent().getExtras().getDouble("res3") + "");
        good.setText(getIntent().getExtras().getDouble("res4") + "");
        //gifImageViewRESULT = findViewById(R.id.resultIMG);
        //Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.check);
        //gifImageViewRESULT.setImageURI(uri);


        pieChart = findViewById(R.id.piechart);
        Muscovite = findViewById(R.id.Muscovite);
        Chlorite = findViewById(R.id.Chlorite);
        Albitie = findViewById(R.id.Albitie);
        Dolomite = findViewById(R.id.Dolomite);
        Pyrite = findViewById(R.id.Pyrite);
        chalcopyrite = findViewById(R.id.chalcopyrite);
        Sphalerite = findViewById(R.id.Sphalerite);
        carbonates = findViewById(R.id.carbonates);
        sulfates = findViewById(R.id.Sulfates);
        //message khouloud
        khouloud = findViewById(R.id.khouloud);
        //Teneur d'or ...
        conclusionTeneur = findViewById(R.id.conclusion);
        imageofconclusion = findViewById(R.id.imageofconclusion);
        sohaib = findViewById(R.id.sohaib);
        khalid = findViewById(R.id.khalid);
        setDataPieChart();

        sohaib.setOnClickListener(view -> {
            Uri uri = Uri.parse("https://www.linkedin.com/in/sohaib-temsamani/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        khalid.setOnClickListener(view -> {
            Uri uri = Uri.parse("https://www.linkedin.com/in/khalid-anbri-448879218/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        //FIREBASE REAL TIME DATABASE
        // Most viewed posts

        Query myTopPostsQuery = FirebaseDatabase.getInstance().getReference("Machinery Cards");
        //.orderByChild("editerinfo/name/References/debitRef")
        // My top posts by number of stars
        myTopPostsQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    ElementClass elementREF = postSnapshot.child("").child("REF").getValue(ElementClass.class);
                    ElementClass elementINFO = postSnapshot.child("").child("INFO").getValue(ElementClass.class);
                    float t, v, f, d, p;
                    t = Math.abs(Float.parseFloat(elementINFO.getTemperature()) -
                            Float.parseFloat(elementREF.getTemperatureRef()));
                    v = Math.abs(Float.parseFloat(elementINFO.getVibration()) -
                            Float.parseFloat(elementREF.getVibrationRef()));
                    f = Math.abs(Float.parseFloat(elementINFO.getFrenquence()) -
                            Float.parseFloat(elementREF.getFrenquenceRef()));
                    d = Math.abs(Float.parseFloat(elementINFO.getDebit()) -
                            Float.parseFloat(elementREF.getDebitRef()));
                    p = Math.abs(Float.parseFloat(elementINFO.getPuissance()) -
                            Float.parseFloat(elementREF.getPuissanceRef()));


                    if ((t > 2) || (v > 2) || (f > 2) || (d > 2) || (p > 2)) {
                        addrowtotable(elementREF.getName(), t, v, f, d, p);
                    }

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "failed loading", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addrowtotable(String Mname, float t, float v, float f, float d, float p) {
        machinemaintenance = findViewById(R.id.machinemaintenance);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.setPadding(1, 1, 1, 1);
        View tC = new View(this);
        View pC = new View(this);
        View vC = new View(this);
        View dC = new View(this);
        View fC = new View(this);
        TextView nameM = new TextView(this);
        nameM.setText(Mname);
        nameM.setTextColor(getResources().getColor(R.color.black));
        nameM.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        tC.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        pC.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        vC.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        dC.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        fC.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));

        /* Add to row. */
        if (t > 2) { //we have + or - 2 with abs = +2
            tC.setBackgroundResource(R.color.red);
        } else {
            tC.setBackgroundResource(R.color.white);
        }
        ////////
        if (v > 2) { //we have + or - 2 with abs = +2
            vC.setBackgroundResource(R.color.red);
        } else {
            vC.setBackgroundResource(R.color.white);
        }
        //////////
        if (f > 2) { //we have + or - 2 with abs = +2
            fC.setBackgroundResource(R.color.red);
        } else {
            fC.setBackgroundResource(R.color.white);
        }
        //////
        if (d > 2) { //we have + or - 2 with abs = +2
            dC.setBackgroundResource(R.color.red);
        } else {
            dC.setBackgroundResource(R.color.white);
        }
        ///////
        if (p > 2) { //we have + or - 2 with abs = +2
            pC.setBackgroundResource(R.color.red);
        } else {
            pC.setBackgroundResource(R.color.white);
        }
        tr.addView(nameM);
        tr.addView(tC);
        tr.addView(pC);
        tr.addView(vC);
        tr.addView(dC);
        tr.addView(fC);
        machinemaintenance.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }

    private void setDataPieChart() {
        Muscovite.setText(String.valueOf(getIntent().getExtras().getDouble("T2res2")));
        Chlorite.setText(String.valueOf(getIntent().getExtras().getDouble("T2res3")));
        Albitie.setText(String.valueOf(getIntent().getExtras().getDouble("T2res4")));
        Dolomite.setText(String.valueOf(getIntent().getExtras().getDouble("T2res5")));
        Pyrite.setText(String.valueOf(getIntent().getExtras().getDouble("T2res6")));
        chalcopyrite.setText(String.valueOf(getIntent().getExtras().getDouble("T2reschalcopyrite")));
        Sphalerite.setText(String.valueOf(getIntent().getExtras().getDouble("T2res7")));
        //TOTAL
        Double carb = getIntent().getExtras().getDouble("T2res2") +
                getIntent().getExtras().getDouble("T2res3") +
                getIntent().getExtras().getDouble("T2res4") +
                getIntent().getExtras().getDouble("T2res5");
        Double sulf = getIntent().getExtras().getDouble("T2res6") +
                getIntent().getExtras().getDouble("T2reschalcopyrite") +
                getIntent().getExtras().getDouble("T2res7");
        carbonates.setText(carb + "%");
        sulfates.setText(sulf + "%");
        //KHOULOUD
        if (carb > sulf) {
            khouloud.setText("Résultat:\nLa teneur des carbonates (comme la Dolomite et le calcite) est plus élevée que la teneur des sulfures (comme la pyrite et la chalopyrite).\nAlors les carbonates peuvent servir à la restauration des sites miniers générateurs d'acides par l'utilisation de ces derniers comme barrière d'oxygène. Donc il faut placer les sulfures en bas et puis les carbonates pour éviter le DMA.");
        } else {
            khouloud.setText("Résultat:\nDonc vouez devez faire la Désulfuration : pour réduire le volume des résidus ayant un potentiel de génération d'acide.\n(La désulfuration c'est une opération qui s'examine sur les rejets sulfures pour réduire la teneur des sulfures)");
        }
        //Teneur d'or..
        if (getIntent().getExtras().getDouble("teneur dor") >
                getIntent().getExtras().getDouble("res4")) {
            // teneur d'or >>> teneur de coupure
            conclusionTeneur.setText("You are lucky:\nLa Teneur de l'OR est largement supérieur à la Teneur de Coupure :\nDanc le Projet Est RENTABLE\n---> You Could Start Extracting");
            Bitmap yesPHOTO = BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.yes));
            imageofconclusion.setImageBitmap(yesPHOTO);
        } else {
            conclusionTeneur.setText("Unfortunately:\nLa Teneur de Coupure est largement supérieur à la Teneur de  l'OR :\nDanc le Projet Est N'est Pas RENTABLE\n---> You Couldn't Start Extracting");
            Bitmap noPHOTO = BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.no));
            imageofconclusion.setImageBitmap(noPHOTO);
        }
        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "R",
                        Float.parseFloat(String.valueOf(getIntent().getExtras().getDouble("T2res2"))),
                        Color.parseColor("#51B435")));
        pieChart.addPieSlice(
                new PieModel(
                        "R",
                        Float.parseFloat(String.valueOf(getIntent().getExtras().getDouble("T2res3"))),
                        Color.parseColor("#E60505")));
        pieChart.addPieSlice(
                new PieModel(
                        "R",
                        Float.parseFloat(String.valueOf(getIntent().getExtras().getDouble("T2res4"))),
                        Color.parseColor("#059FE6")));
        pieChart.addPieSlice(
                new PieModel(
                        "R",
                        Float.parseFloat(String.valueOf(getIntent().getExtras().getDouble("T2res5"))),
                        Color.parseColor("#05E6A6")));
        pieChart.addPieSlice(
                new PieModel(
                        "R",
                        Float.parseFloat(String.valueOf(getIntent().getExtras().getDouble("T2res6"))),
                        Color.parseColor("#D8CE09")));
        pieChart.addPieSlice(
                new PieModel(
                        "R",
                        Float.parseFloat(String.valueOf(getIntent().getExtras().getDouble("T2reschalcopyrite"))),
                        Color.parseColor("#C309D8")));
        pieChart.addPieSlice(
                new PieModel(
                        "R",
                        Float.parseFloat(String.valueOf(getIntent().getExtras().getDouble("T2res7"))),
                        Color.parseColor("#5F09D8")));
        // To animate the pie chart
        pieChart.startAnimation();

    }
}