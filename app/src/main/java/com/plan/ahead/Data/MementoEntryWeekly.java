package com.plan.ahead.Data;

/**
 * Created by Madalina on 5/24/2018.
 */

public class MementoEntryWeekly extends MementoEntry {

    public boolean[] days;

    public boolean[] getDays() {
        return days;
    }

    public void setDays(boolean[] days) {
        this.days = days;
    }

    public MementoEntryWeekly() {

        this.days = new boolean[7]; //from monday 0 to sunday 7

    }
}
