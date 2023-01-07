package com.example.indusbm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;


public class FirstFragment extends Fragment {

    Button addCard;
    public FirstFragment(){
        // require a empty public constructor
    }
    List<ElementClass> myElements = new ArrayList<>();
    RecyclerView list_of_machines;
    View view;

    //FIREBASE REAL TIME DATABASE
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_first, container, false);


        addCard = view.findViewById(R.id.addCard);
        list_of_machines = view.findViewById(R.id.list_of_machines);
        AdapterItems adapterItems = new AdapterItems(myElements, getContext());


        addCard.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), AddActivity.class));
        });



        //FIREBASE REAL TIME DATABASE
        // Most viewed posts
        Query myTopPostsQuery = FirebaseDatabase.getInstance().getReference("Machinery Cards");
        //.orderByChild("editerinfo/name/References/debitRef")
        // My top posts by number of stars
        myTopPostsQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    ElementClass elementREF = postSnapshot.child("").child("REF").getValue(ElementClass.class);
                    ElementClass elementINFO = postSnapshot.child("").child("INFO").getValue(ElementClass.class);
                    //passing info to ref
                    elementREF.setTemperature(elementINFO.getTemperature());
                    elementREF.setVibration(elementINFO.getVibration());
                    elementREF.setFrenquence(elementINFO.getFrenquence());
                    elementREF.setDebit(elementINFO.getDebit());
                    elementREF.setPuissance(elementINFO.getPuissance());
                    elementREF.setUploader(elementINFO.getUploader());
                    elementREF.setEditertime(elementINFO.getEditertime());

                    myElements.add(elementREF);

                }
                adapterItems.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Getting Post failed, log a message
            }
        });


        list_of_machines.setHasFixedSize(false);
        list_of_machines.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
            );
        list_of_machines.setAdapter(adapterItems);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
