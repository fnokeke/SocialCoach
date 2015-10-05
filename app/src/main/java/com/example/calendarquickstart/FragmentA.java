package com.example.calendarquickstart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by fnokeke on 10/2/15.
 */
public class FragmentA extends Fragment {

    View rootView;
    private static TextView eventTV;
    private static TextView eventTitleTV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_a,container,false);

        eventTV = (TextView)rootView.findViewById(R.id.eventTV);
        eventTV.setVerticalScrollBarEnabled(true);
        eventTV.setMovementMethod(new ScrollingMovementMethod());
        eventTV.setText("Supp");

        return rootView;
    }

    public static void setEvent(String text) {
        eventTV.setText(text);
    }

    public static void setEventTitle(String text) {
        eventTitleTV.setText(text);
    }
}