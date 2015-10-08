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
    private static TextView joint_text;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_location,container,false);
        joint_text = (TextView) rootView.findViewById(R.id.joint_text);

        return rootView;
    }

    public static void updateUI(String uiElement, String text) {
        CharSequence tmp = "";
        if (uiElement.equals("joint_label")) {
            tmp = joint_text.getText();
            joint_text.setText(text + "\n" + tmp);
        }
    }

}