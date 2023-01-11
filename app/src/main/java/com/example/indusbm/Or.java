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

        //second table
        EditText l1c1 = view.findViewById(R.id.l1c1);
        EditText l2c1 = view.findViewById(R.id.l2c1);
        EditText l3c1 = view.findViewById(R.id.l3c1);
        EditText l4c1 = view.findViewById(R.id.l4c1);
        EditText l5c1 = view.findViewById(R.id.l5c1);
        EditText l6c1 = view.findViewById(R.id.l6c1);
        EditText chalcopyrite = view.findViewById(R.id.chalcopyrite);
        EditText l7c1 = view.findViewById(R.id.l7c1);



        result.setOnClickListener(view -> {
            if (!(TextUtils.isEmpty(teneurDor.getText().toString()) ||
                    TextUtils.isEmpty(tauxDeRecuperation.getText().toString()) ||
                    TextUtils.isEmpty(tonnageT.getText().toString()) ||
                    TextUtils.isEmpty(dillution.getText().toString()) ||
                    TextUtils.isEmpty(prix.getText().toString()) || // second table
                    TextUtils.isEmpty(l1c1.getText().toString()) ||
                    TextUtils.isEmpty(l2c1.getText().toString()) ||
                    TextUtils.isEmpty(l3c1.getText().toString()) ||
                    TextUtils.isEmpty(l4c1.getText().toString()) ||
                    TextUtils.isEmpty(l5c1.getText().toString()) ||
                    TextUtils.isEmpty(l6c1.getText().toString()) ||
                    TextUtils.isEmpty(chalcopyrite.getText().toString()) ||
                    TextUtils.isEmpty(l7c1.getText().toString())
            )) {
                Double iv1 = Double.valueOf(teneurDor.getText().toString());
                Double iv2 = Double.valueOf(tauxDeRecuperation.getText().toString());
                Double iv3 = Double.valueOf(tonnageT.getText().toString());
                Double iv4 = Double.valueOf(dillution.getText().toString());
                Double iv5 = Double.valueOf(cout.getText().toString());
                Double iv6 = Double.valueOf(prix.getText().toString());

                //second table
                //Double jv1 = Double.valueOf(l1c1.getText().toString());
                Double jv2 = Double.valueOf(l2c1.getText().toString());
                Double jv3 = Double.valueOf(l3c1.getText().toString());
                Double jv4 = Double.valueOf(l4c1.getText().toString());
                Double jv5 = Double.valueOf(l5c1.getText().toString());
                Double jv6 = Double.valueOf(l6c1.getText().toString());
                Double jvchalcopyrite = Double.valueOf(chalcopyrite.getText().toString());
                Double jv7 = Double.valueOf(l7c1.getText().toString());

                Double res1 = (iv1 * iv3);
                Double res2 = (iv1 * iv3 * iv2 / 100) ;
                Double res3 = (iv3 * 1000 - res2 );
                Double res4 = 100 * (iv5 / (iv6 * (1 - (iv4 / 100)) * (iv2 / 100) * (2205 * (iv2 / 100))));
                Intent intent = new Intent(getActivity(), OrResult.class);
                intent.putExtra("teneur dor", iv1);
                intent.putExtra("res1", res1);
                intent.putExtra("res2", res2);
                intent.putExtra("res3", res3);
                intent.putExtra("res4", res4);
                //check if the some of values of second table == 100%
                if ((jv2 + jv3 + jv4 + jv5 + jv6 + jvchalcopyrite + jv7) == 100){
                    //second table
                    //intent.putExtra("T2res1", jv1);
                    intent.putExtra("T2res2", jv2);
                    intent.putExtra("T2res3", jv3);
                    intent.putExtra("T2res4", jv4);
                    intent.putExtra("T2res5", jv5);
                    intent.putExtra("T2res6", jv6);
                    intent.putExtra("T2reschalcopyrite", jvchalcopyrite);
                    intent.putExtra("T2res7", jv7);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "The sum of second table's values is " +
                            (jv2 + jv3 + jv4 + jv5 + jv6 + jvchalcopyrite + jv7) + "%",Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(getContext(), "please fill all the informations", Toast.LENGTH_SHORT).show();
            }

        });
        return view;
    }
}