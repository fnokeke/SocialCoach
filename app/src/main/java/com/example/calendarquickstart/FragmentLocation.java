package com.example.calendarquickstart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by fnokeke on 10/2/15.
 */
public class FragmentLocation extends Fragment {

    View rootView;
    private static TextView lonTV;
    private static TextView latTV;
    private static TextView addressTV;
    private static TextView timeTV;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_location,container,false);

        lonTV = (TextView)rootView.findViewById(R.id.latitude_text);
        latTV = (TextView) rootView.findViewById(R.id.longitude_text);
        addressTV = (TextView) rootView.findViewById(R.id.address_text);
        timeTV = (TextView) rootView.findViewById(R.id.last_update_time_text);

        return rootView;
    }

    public static void updateUI(String uiElement, String text) {
        CharSequence tmp = "";
        if (uiElement.equals("lon")) {
            tmp = lonTV.getText();
            lonTV.setText(text + "\n" + tmp);
        }
        else if (uiElement.equals("lat")) {
            tmp = latTV.getText();
            latTV.setText(text + "\n" + tmp);            latTV.setText(text);
        }
        else if (uiElement.equals("address")) {
            tmp = addressTV.getText();
            addressTV.setText(text + "\n" + tmp);         }
        else if (uiElement.equals("time")) {
            tmp = timeTV.getText();
            timeTV.setText(text + "\n" + tmp);
        }
    }

}