package com.example.emina.bihamk;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emina.bihamk.database.RadarBaseHelper;

import java.util.ArrayList;

public class FragmentOne extends Fragment {

    private ListView listView;
    private RadarBaseHelper baza;
    ArrayAdapter<String> listViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_layout, container, false);

        ListView lista = view.findViewById(R.id.lista);
        Bundle bundle = getActivity().getIntent().getExtras();

        baza = new RadarBaseHelper(getActivity());

        String cantonID = bundle.getString("cantonID");
        ArrayList<String> alist = baza.dajPodatke(cantonID);

       listViewAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, alist);
        lista.setAdapter(listViewAdapter);
        listViewAdapter.notifyDataSetChanged();

        return view;
    }


}