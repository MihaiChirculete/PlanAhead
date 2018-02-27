package com.plan.ahead.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;

/**
 * Created by mihai on 27.02.2018.
 * Defines properties of a task that the user adds to the schedule
 */

public class Task {
    private String name;
    private Timestamp start, end;

    public Task()
    {
        this.name = null;
        this.start = null;
        this.end = null;
    }

    public Task(String name, Timestamp start, Timestamp end)
    {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    // loads this object from a JSON representation
    public Task(JSONObject jo)
    {
        try {
            if(jo.getString("taskName").equals("null"))
                this.name = null;
            else
                this.name = jo.getString("taskName");

            if(jo.getString("taskStart").equals("null"))
                this.start = null;
            else
                this.start = Timestamp.valueOf(jo.getString("taskStart"));

            if(jo.getString("taskEnd").equals("null"))
                this.end = null;
            else
                this.end = Timestamp.valueOf(jo.getString("taskEnd"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public Timestamp getStart() { return this.start; }
    public Timestamp getEnd() { return this.end; }

    public void setStart(Timestamp start) { this.start = start; }
    public void setEnd(Timestamp end) { this.end = end; }

    // returns a JSON representation of this object
    public JSONObject toJSON()
    {
        JSONObject jo = new JSONObject();
        try {
            if(this.name == null)
                jo.put("taskName", "null");
            else
                jo.put("taskName", this.name);

            if(this.start == null)
                jo.put("taskStart", "null");
            else
                jo.put("taskStart", this.start.toString());

            if(this.end == null)
                jo.put("taskEnd", "null");
            else
                jo.put("taskEnd", this.start.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jo;
    }
}
