package com.gamboa.troy.WattsOn;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Troygbv on 2/24/17.
 */

public class FragmentHome extends Fragment {

    private Button test;

    public FragmentHome() {
        //required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView welcome = (TextView) view.findViewById(R.id.welcomeTV);

        //Set the welcome text to welcome the extra string "username" from login
        String item = getActivity().getIntent().getExtras().getString("username");

        //Bring the first letter of the username to be capitalized
        item = item.substring(0,1).toUpperCase() + item.substring(1).toLowerCase();
        welcome.setText("Welcome, " + item + "!");

        test = (Button) view.findViewById(R.id.buttonTest);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openTest = new Intent(getActivity(), TestMP.class);
                startActivity(openTest);
            }
        });
        return view;
    }
}
