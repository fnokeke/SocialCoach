package com.example.calendarquickstart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by fnokeke on 10/3/15.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String tabname = "";

        switch (position) {
            case 0:
                tabname = "Events";
                break;
            case 1:
                tabname = "Location";
                break;
            case 2:
                tabname = "Experience";
                break;

        }
        return tabname;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment tabfrag = new Fragment();

        switch (position) {
            case 0:
                tabfrag = new FragmentEvent();
                break;
            case 1:
                tabfrag = new FragmentLocation();
                break;
            case 2:
                tabfrag = new FragmentExperience();
                break;
        }

        return tabfrag;
    }
}