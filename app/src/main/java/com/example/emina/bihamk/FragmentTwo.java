package com.example.emina.bihamk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTwo extends Fragment {

        private TextView textView;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View RootView = inflater.inflate(R.layout.fragment_two_layout, container, false);

            textView=RootView.findViewById(R.id.text);

            Bundle bundle = getActivity().getIntent().getExtras();

            if(bundle != null)  //this line is necessary for getting any value
            {
                String [] radarStreets = bundle.getStringArray("ulice");
                TextView textView = (TextView) RootView.findViewById(R.id.text);
                String streets = "";

                final String mimeType = "text/html";
                final String encoding = "UTF-8";
                textView.setText(Html.fromHtml(radarStreets[0]), TextView.BufferType.EDITABLE);
                String almostSameText = Html.toHtml(textView.getEditableText()).toString();
                textView.setText(Html.fromHtml(almostSameText), TextView.BufferType.SPANNABLE);
                textView.setMovementMethod(new ScrollingMovementMethod());
                //textView.loadDataWithBaseURL(" ", radarStreets[0], mimeType, encoding, "");
                //Toast.makeText(this, "value="+radarStreets[0]+" "+radarStreets[1], Toast.LENGTH_SHORT).show();
            }
            else{
                TextView textView = (TextView) RootView.findViewById(R.id.text);
                textView.setText("Trenutno nema stacionarnih stanja");
            }


            return RootView;

           // return inflater.inflate(R.layout.fragment_two_layout,container,false);


        }








}

