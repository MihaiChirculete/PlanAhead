package com.plan.ahead.Storage;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.alamkanak.weekview.WeekViewEvent;
import com.plan.ahead.Data.ToDoEntry;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

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
                //jo.put("location", event.getLocation());
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

            for(int i=0; i<ja.length(); i++) {
                jo = ja.getJSONObject(i);

                Calendar startTime = Calendar.getInstance();
                startTime.setTimeInMillis(jo.getLong("startTime"));
                Calendar endTime = Calendar.getInstance();
                endTime.setTimeInMillis(jo.getLong("endTime"));
                WeekViewEvent event = new WeekViewEvent(jo.getInt("id"), jo.getString("eventName"), startTime, endTime);

                switch ((int)(Math.random() * 3))
                {
                    case 0: event.setColor(Color.GREEN);
                            break;
                    case 1: event.setColor(Color.BLUE);
                            break;
                    case 2: event.setColor(Color.RED);
                            break;
                    case 3: event.setColor(Color.CYAN);
                            break;
                }

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

    // save a list of ToDoEntry as json
    public static void saveToDoEntries(List<ToDoEntry> entries)
    {
        File f = new File(getAppStoragePath() + "ToDoEntries.json");
        JSONArray ja = new JSONArray();
        JSONObject jo;

        try {
            // create the file if it doesn't exist
            if(!f.exists())
                f.createNewFile();

            for(int i=0; i<entries.size(); i++)
            {
                ToDoEntry entry = entries.get(i);

                jo = new JSONObject();
                jo.put("title", entry.getEntryTitle());
                jo.put("description", entry.getEntryDescription());
                jo.put("timeInterval", entry.getEntryTimeInterval());

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

    public static List<ToDoEntry> loadToDoEntries()
    {
        File f = new File(getAppStoragePath() + "ToDoEntries.json");

        if(!f.exists())
            return new ArrayList<ToDoEntry>();

        List<ToDoEntry> entries = new ArrayList<ToDoEntry>();
        JSONArray ja;
        JSONObject jo;

        try {
            FileInputStream in = new FileInputStream(f);
            byte[] b = new byte[(int) f.length()];
            in.read(b);
            in.close();

            ja = new JSONArray(new String(b));

            for(int i=0; i<ja.length(); i++) {
                jo = ja.getJSONObject(i);

                ToDoEntry entry = new ToDoEntry();
                entry.setEntryTitle(jo.getString("title"));
                entry.setEntryDescription(jo.getString("description"));
                entry.setEntryTimeInterval(jo.getString("timeInterval"));

                entries.add(entry);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entries;
    }

    // generic method for serializing a list of objects to a file
    private static void serializeObjectList(List<Object> entries, Context ctx, String fileName)
    {
        try {
            int objectCount = entries.size();
            FileOutputStream fos = ctx.openFileOutput(getAppStoragePath() + fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            // put the size at the beggining of the file
            os.writeObject(objectCount);
            for(int i=0; i<entries.size(); i++)
                os.writeObject(entries.get(i));
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // generic method for deserializing a list of objects from a file
    private static List<Object> deserializeObjectList(Context ctx, String fileName)
    {
        try {
            int objectCount;
            List<Object> entries = new ArrayList<Object>();

            FileInputStream fis = ctx.openFileInput(getAppStoragePath() + fileName);
            ObjectInputStream is = new ObjectInputStream(fis);

            // read the size of the array
            objectCount = (int)is.readObject();

            for(int i=0; i<objectCount; i++)
                entries.add(is.readObject());

            is.close();
            fis.close();

            return entries;
        }
        catch (IOException e)
        {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // save a list of memento entries
    public static void saveMementoEntries(List<Object> entries, Context ctx)
    {
        serializeObjectList(entries, ctx, "MementoEntries.dat");
    }

    public static List<Object> loadMementoEntries(Context ctx)
    {
        return deserializeObjectList(ctx, "MementoEntries.dat");
    }

    // save a list of routine entries
    public static void saveRoutineEntries(List<Object> entries, Context ctx)
    {
        serializeObjectList(entries, ctx, "RoutineEntries.dat");
    }

    public static List<Object> loadRoutineEntries(Context ctx)
    {
        return deserializeObjectList(ctx, "RoutineEntries.dat");
    }

    public static void deleteAllEvents()
    {
        File f = new File(getAppStoragePath() + "Events.json");
        f.delete();
    }
}
