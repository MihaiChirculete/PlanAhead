package com.plan.ahead.Data;

/**
 * Created by Madalina on 5/24/2018.
 */

public class RoutineEntryWeekly extends RoutineEntryDaily {

    private boolean[] weekDays;

    public boolean[] getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(boolean[] weekDays) {
        this.weekDays = weekDays;
    }

    public RoutineEntryWeekly() {

        super(7);
        this.weekDays = new boolean[7];

    }
}
