package com.example.indusbm;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;


public class FirstFragment extends Fragment {

    GifImageView addCard;
    public FirstFragment(){
        // require a empty public constructor
    }
    List<ElementClass> myElements = new ArrayList<>();
    RecyclerView list_of_machines;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_first, container, false);


        addCard = view.findViewById(R.id.addCard);
        list_of_machines = view.findViewById(R.id.list_of_machines);
        if( myElements != null && !myElements.isEmpty()){
            AdapterItems adapterItems = new AdapterItems(myElements, getContext());
            list_of_machines.setHasFixedSize(false);
            list_of_machines.setLayoutManager(
                    new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
            );
            list_of_machines.setAdapter(adapterItems);
            //noMusic.setVisibility(View.GONE);
        }
        addCard.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), AddActivity.class));
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i=0; i<3;i++){
            myElements.add(new ElementClass("La corroyeuse", "assure le rabotage " +
                    "des quatre faces d’une pièce en un seul passage. Elle est " +
                    "appelée moulurière lorsqu’elle dispose d’un ou de plusieurs outils " +
                    "de profilage, de type arbre de toupie"));
            myElements.add(new ElementClass("La ponceuse calibreuse",
                    "Cette machine permet de rectifier une surface par ponçage. Suivant " +
                            "la taille du grain abrasif utilisé, elle est aussi bien utilisée pour le " +
                            "calibrage (dressage d’une face en épaisseur) que pour la finition"
            ));
            myElements.add(new ElementClass("La scie circulaire à panneau \n" +
                    "à positionnement numérique",
                    "C’est une scie circulaire où le panneau est positionné à plat. \n" +
                            "Lors de la coupe (à l’inverse de la scie circulaire à format), c’est\n" +
                            "la lame de scie qui se déplace et non le panneau. Les réglages "+
                            "et les déplacements des guides sont programmés et automatisés \n" +
                            "par ordinateur"
            ));
        }


    }
}
