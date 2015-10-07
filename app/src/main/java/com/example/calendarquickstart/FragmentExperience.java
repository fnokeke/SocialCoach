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
public class FragmentExperience extends Fragment {

    View rootView;
    private static TextView reportTV;
    private static TextView reportTitleTV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_experience,container,false);

        reportTV = (TextView)rootView.findViewById(R.id.report_text);
        reportTV.setVerticalScrollBarEnabled(true);
        reportTV.setMovementMethod(new ScrollingMovementMethod());

        reportTitleTV = (TextView) rootView.findViewById(R.id.report_title_text);

        return rootView;
    }

    public static void updateUI(String uiElement, String text) {
        if (uiElement.equals("report"))
            reportTV.setText(text);
        else if (uiElement.equals("report_title"))
            reportTitleTV.setText(text);
    }
}