package com.example.indusbm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Rapport extends Fragment {

    public Rapport() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    List<ElementRappotClass> elementRappotClassList = new ArrayList<>();
    RecyclerView recyclerRapport;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_rapport, container, false);

        recyclerRapport = view.findViewById(R.id.recyclerRapport);
        AdapterString me = new AdapterString(elementRappotClassList, getContext());
        //FIREBASE REAL TIME DATABASE
        // Most viewed posts

        Query myTopPostsQuery = FirebaseDatabase.getInstance().getReference("Machinery Cards");
        //.orderByChild("editerinfo/name/References/debitRef")
        // My top posts by number of stars
        myTopPostsQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String resultat = "";
                    ElementClass elementREF = postSnapshot.child("").child("REF").getValue(ElementClass.class);
                    ElementClass elementINFO = postSnapshot.child("").child("INFO").getValue(ElementClass.class);
                    float t,v,f,d,p;
                    t = Math.abs(Float.parseFloat(elementINFO.getTemperature()) -
                            Float.parseFloat(elementREF.getTemperatureRef()) );
                    v = Math.abs(Float.parseFloat(elementINFO.getVibration()) -
                            Float.parseFloat(elementREF.getVibrationRef()) );
                    f = Math.abs(Float.parseFloat(elementINFO.getFrenquence()) -
                            Float.parseFloat(elementREF.getFrenquenceRef()) );
                    d = Math.abs(Float.parseFloat(elementINFO.getDebit()) -
                            Float.parseFloat(elementREF.getDebitRef()) );
                    p = Math.abs(Float.parseFloat(elementINFO.getPuissance()) -
                            Float.parseFloat(elementREF.getPuissanceRef()) );
                    if (t > 2){ //we have + or - 2 with abs = +2
                        resultat += "and temperature ";
                    }
                    ////////
                    if (v > 2){ //we have + or - 2 with abs = +2
                        resultat += "and Vibration ";
                    }
                    //////////
                    if (f > 2){ //we have + or - 2 with abs = +2
                        resultat += "and frenquency ";
                    }
                    //////
                    if (d > 2){ //we have + or - 2 with abs = +2
                        resultat += "and Debit ";
                    }
                    ///////
                    if (p > 2 ){ //we have + or - 2 with abs = +2
                        resultat += "and Power ";
                    }
                    resultat+="\n";
                    if ( (t > 2 ) || (v > 2 ) || (f > 2 ) || (d > 2 ) || (p > 2 ) ){
                        resultat = resultat.substring("and".length());
                        elementRappotClassList.add(new ElementRappotClass(elementREF.getName(),resultat));
                    }

                }
                me.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "failed loading", Toast.LENGTH_SHORT).show();
            }
        });


        recyclerRapport.setHasFixedSize(false);
        recyclerRapport.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
        );
        recyclerRapport.setAdapter(me);
        return view;
    }
}