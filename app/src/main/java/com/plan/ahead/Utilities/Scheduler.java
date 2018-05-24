package com.plan.ahead.Utilities;

import android.content.Context;
import android.widget.Toast;

import com.alamkanak.weekview.WeekViewEvent;
import com.plan.ahead.Storage.StorageUtils;

import java.util.List;

/**
 * Class responsible with scheduling tasks and assigning slots in the WeekView
 */

public class Scheduler {
    // get id available for event
    public static long getAvailableId()
    {
        List<WeekViewEvent> events = StorageUtils.loadEvents();

        if(events.isEmpty())
            return 0;

        return events.get(events.size()-1).getId() + 1;
    }

    // check if this event overlaps another
    private static boolean isEventOverlapping(WeekViewEvent ev)
    {
        List<WeekViewEvent> events = StorageUtils.loadEvents();

        for(int i=0; i<events.size(); i++)
        {
            WeekViewEvent tmp = events.get(i);

            if(tmp.getStartTime().getTimeInMillis() >= ev.getStartTime().getTimeInMillis() &&
                    tmp.getEndTime().getTimeInMillis() <= ev.getEndTime().getTimeInMillis())
                return true;
        }

        return false;
    }

    public static void scheduleNewEvent(WeekViewEvent ev, Context cx)
    {
        List<WeekViewEvent> events = StorageUtils.loadEvents();

        if(!isEventOverlapping(ev))
            events.add(ev);
        else
            Toast.makeText(cx, "Event overlaps another event! Discarding.", Toast.LENGTH_LONG).show();

        StorageUtils.saveEvents(events);
    }
}
