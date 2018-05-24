package com.plan.ahead.Data;

import java.io.Serializable;

/**
 * Created by Madalina on 5/24/2018.
 */

//for dayliy entry, make an instance of this class with number onOfEntries = 1

public class RoutineEntryDaily implements Serializable{

    protected String routineName;
    protected TimeInterval[] intervals;

    public TimeInterval[] getIntervals() {
        return intervals;
    }

    public void setIntervals(TimeInterval[] intervals) {
        this.intervals = intervals;
    }

    public RoutineEntryDaily(int nrOfEntries) {
        this.intervals = new TimeInterval[nrOfEntries];

    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }
}
