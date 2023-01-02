package com.example.indusbm;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Or extends Fragment {

    View view;
    public Or() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_or, container, false);

        Button result = view.findViewById(R.id.resultat);

        EditText teneurDor = view.findViewById(R.id.teneurDor);
        EditText tauxDeRecuperation = view.findViewById(R.id.tauxDeRecuperation);
        EditText tonnageT = view.findViewById(R.id.tonnageT);
        EditText dillution = view.findViewById(R.id.dillution);
        EditText prix = view.findViewById(R.id.prix);
        EditText cout = view.findViewById(R.id.cout);



        result.setOnClickListener(view -> {
            if (!(TextUtils.isEmpty(teneurDor.getText().toString()) ||
                    TextUtils.isEmpty(tauxDeRecuperation.getText().toString()) ||
                    TextUtils.isEmpty(tonnageT.getText().toString()) ||
                    TextUtils.isEmpty(dillution.getText().toString()) ||
                    TextUtils.isEmpty(prix.getText().toString()))) {
                String v1 = teneurDor.getText().toString();
                String v2 = tauxDeRecuperation.getText().toString();
                String v3 = tonnageT.getText().toString();
                String v4 = dillution.getText().toString();
                String v5 = cout.getText().toString();
                String v6 = prix.getText().toString();

                Double iv1 = Double.valueOf(v1);
                Double iv2 = Double.valueOf(v2);
                Double iv3 = Double.valueOf(v3);
                Double iv4 = Double.valueOf(v4);
                Double iv5 = Double.valueOf(v5);
                Double iv6 = Double.valueOf(v6);

                Double res1 = (iv1 * iv3);
                Double res2 = (iv1 * iv3 * iv2 / 100) ;
                Double res3 = (iv3 * 1000 - res2 );
                Double res4 = 100 * (iv5 / (iv6 * (1 - (iv4 / 100)) * (iv2 / 100) * (2205 * (iv2 / 100))));
                Intent intent = new Intent(getActivity(), OrResult.class);
                intent.putExtra("res1", res1);
                intent.putExtra("res2", res2);
                intent.putExtra("res3", res3);
                intent.putExtra("res4", res4);
                startActivity(intent);
            }else{
                Toast.makeText(getContext(), "please fill all the informations", Toast.LENGTH_SHORT).show();
            }

        });
        startActivity(new Intent(getActivity(), OrResult.class));
        return view;
    }
}