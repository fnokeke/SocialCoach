package com.example.calendarquickstart;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.util.DateTime;

import com.google.api.services.calendar.model.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An asynchronous task that handles the Google Calendar API call.
 * Placing the API calls in their own task ensures the UI stays responsive.
 */
public class ApiAsyncTask extends AsyncTask<Date, Void, Void> {
    private MainActivity mActivity;


    /**
     * Constructor.
     * @param activity MainActivity that spawned this task.
     */
    ApiAsyncTask(MainActivity activity) {
        this.mActivity = activity;
    }

    /**
     * Background task to call Google Calendar API.
     * @param params no parameters needed for this task.
     */
    @Override
    protected Void doInBackground(Date... params) {
        try {
            mActivity.clearResultsText();
            mActivity.updateResultsText(getDataFromApi(params[0]));
            mActivity.updateSignals();

        } catch (final GooglePlayServicesAvailabilityIOException availabilityException) {
            mActivity.showGooglePlayServicesAvailabilityErrorDialog(
                    availabilityException.getConnectionStatusCode());

        } catch (UserRecoverableAuthIOException userRecoverableException) {
            mActivity.startActivityForResult(
                    userRecoverableException.getIntent(),
                    MainActivity.REQUEST_AUTHORIZATION);

        } catch (Exception e) {
            //@TODO: fix this error that prints to screen
            //mActivity.updateStatus("The following error occurred:\n" +
              //      e.getMessage());
            e.printStackTrace();
        }
        if (mActivity.mProgress.isShowing()) {
            mActivity.mProgress.dismiss();
        }
        return null;
    }

    /**
     * Fetch a list of the next 10 events from the primary calendar.
     * @return List of Strings describing returned events.
     * @throws IOException
     */
    private List<String> getDataFromApi(Date selected_date) throws IOException {
        long current_time_millis = 0;

        try {
            Log.i("**selected date**:", selected_date.toString());
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            Log.i("selected ms of event:", Long.toString(selected_date.getTime()));
            current_time_millis = selected_date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        com.google.api.client.util.DateTime startofday = new com.google.api.client.util.DateTime(getStartOfDayMillis(current_time_millis));
        com.google.api.client.util.DateTime endofday = new com.google.api.client.util.DateTime(getEndOfDayMillis(current_time_millis));
        Log.i("start event:", startofday.toString());
        Log.i("stop event:", endofday.toString());

        // List the next 10 events from the primary calendar.
        //DateTime now = new DateTime(System.currentTimeMillis());
        List<String> eventStrings = new ArrayList<String>();
        Events events = mActivity.mService.events().list("primary")
                .setMaxResults(20)
                .setTimeMin(startofday)
                .setTimeMax(endofday)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();

        for (Event event : items) {
            DateTime start = event.getStart().getDateTime();
            if (start == null) {
                // All-day events don't have start times, so just use
                // the start date.
                start = event.getStart().getDate();
            }
            eventStrings.add(
                    String.format("%s (%s)", event.getSummary(), convertTo12hrClock(start)));
        }
        Log.i("event_strings:", eventStrings.toString());
        return eventStrings;
    }

    private long getStartOfDayMillis(long date_in_ms) {
        org.joda.time.DateTime current_date = new org.joda.time.DateTime(date_in_ms);
        return current_date.withTimeAtStartOfDay().getMillis();
    }

    private long getEndOfDayMillis(long date_in_ms) {
        org.joda.time.DateTime current_date = new org.joda.time.DateTime(date_in_ms);
        return current_date.plusDays(1).withTimeAtStartOfDay().getMillis();
    }

    //@TODO: refactor code
    private String convertTo12hrClock(DateTime date_time) {
        String dt = String.format("%s", date_time);
        String [] parts = dt.split("T");
        String onlyTime = parts[1];

        String[] time_parts = onlyTime.split("-");
        String begin_time = time_parts[0];

        int hr = Integer.parseInt(begin_time.split(":")[0]);
        String mins = begin_time.split(":")[1];

        //We need to figure if time should be am or pm
        String time_in_12hrs = "";
        if (hr == 12)
            time_in_12hrs = Integer.toString(hr) + ":" + mins + "pm";
        else if (hr > 12) {
            hr = hr % 12;
            time_in_12hrs = Integer.toString(hr) + ":" + mins + "pm";
        }
        else {
            time_in_12hrs = Integer.toString(hr) + ":" + mins + "am";
        }

        return time_in_12hrs;

    }

}