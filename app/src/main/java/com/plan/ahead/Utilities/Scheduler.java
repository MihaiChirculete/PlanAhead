package com.plan.ahead.Utilities;

import android.content.Context;

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

        return false;
    }

    public static void scheduleNewEvent(WeekViewEvent ev)
    {
        List<WeekViewEvent> events = StorageUtils.loadEvents();

        if(!isEventOverlapping(ev))
            events.add(ev);

        StorageUtils.saveEvents(events);
    }
}
