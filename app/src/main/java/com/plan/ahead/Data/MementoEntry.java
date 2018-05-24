package com.plan.ahead.Data;

/**
 * Created by Madalina on 5/24/2018.
 */

public class MementoEntry {

    protected String memName;
    protected String memRemindHour;

    public MementoEntry() {

    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemRemindHour() {
        return memRemindHour;
    }

    public void setMemRemindHour(String memRemindHour) {
        this.memRemindHour = memRemindHour;
    }

}
