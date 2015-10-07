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
public class FragmentEvent extends Fragment {

    View rootView;
    private static TextView eventTV;
    private static TextView eventTitleTV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_a,container,false);

        eventTV = (TextView)rootView.findViewById(R.id.eventTV);
        eventTV.setVerticalScrollBarEnabled(true);
        eventTV.setMovementMethod(new ScrollingMovementMethod());

        eventTitleTV = (TextView) rootView.findViewById(R.id.eventTitle);

        return rootView;
    }

    public static void updateUI(String uiElement, String text) {
        if (uiElement.equals("event"))
            eventTV.setText(text);
        else if (uiElement.equals("event_title"))
            eventTitleTV.setText(text);
    }
}