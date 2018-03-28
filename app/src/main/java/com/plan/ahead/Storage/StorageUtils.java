package com.plan.ahead.Storage;

import android.graphics.Color;
import android.widget.Toast;

import com.alamkanak.weekview.WeekViewEvent;
import com.plan.ahead.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mihai on 27.02.2018.
 *  Utility methods for working with files on /data partition
 */

public class StorageUtils {
    // checks if the storage dir exists and if not it creates it
    // call this when the app is opened
    public static void createAppStorageDir()
    {
        File storageDir = new File("/data/data/com.plan.ahead/files/PlanAheadStorage/");
        if(!storageDir.exists())
            storageDir.mkdirs();
    }

    // returns the path for the writable storage dir of the application
    public static String getAppStoragePath()
    {
        return "/data/data/com.plan.ahead/files/PlanAheadStorage/";
    }

    // save a list of Events as a json
    public static void saveEvents(List<WeekViewEvent> events)
    {
        File f = new File(getAppStoragePath() + "Events.json");
        JSONArray ja = new JSONArray();
        JSONObject jo;

        try {
            // create the file if it doesn't exist
            if(!f.exists())
                f.createNewFile();

            for(int i=0; i<events.size(); i++)
            {
                WeekViewEvent event = events.get(i);

                jo = new JSONObject();
                jo.put("id", event.getId());
                jo.put("eventName", event.getName());
                jo.put("startTime", event.getStartTime().getTimeInMillis());
                jo.put("endTime", event.getEndTime().getTimeInMillis());
                jo.put("location", event.getLocation());
                jo.put("color", event.getColor());

                ja.put(jo);
            }

            // the json array is ready
            // write it to the file
            FileOutputStream stream = new FileOutputStream(f);
            stream.write(ja.toString().getBytes());
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // load a list of Events from a json
    public static List<WeekViewEvent> loadEvents()
    {
        File f = new File(getAppStoragePath() + "Events.json");

        if(!f.exists())
            return new ArrayList<WeekViewEvent>();

        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        JSONArray ja;
        JSONObject jo;

        try {
            FileInputStream in = new FileInputStream(f);
            byte[] b = new byte[(int) f.length()];
            in.read(b);
            in.close();

            ja = new JSONArray(new String(b));

            for(int i=0; i<ja.length(); i++)
            {
                jo = ja.getJSONObject(i);

                Calendar startTime = Calendar.getInstance();
                startTime.setTimeInMillis(jo.getLong("startTime"));
                Calendar endTime = Calendar.getInstance();
                endTime.setTimeInMillis(jo.getLong("endTime"));
                WeekViewEvent event = new WeekViewEvent(jo.getInt("id"), jo.getString("eventName"), startTime, endTime);
                event.setColor(Color.GREEN);
                events.add(event);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return events;
    }
}
