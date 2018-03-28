package com.plan.ahead.Data;

/**
 * Created by mihai on 28.03.2018.
 */

public class ToDoEntry {
    private String entryTitle;
    private String entryDescription;
    private String entryTimeInterval;

    public ToDoEntry(){

    }

    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public String getEntryDescription() {
        return entryDescription;
    }

    public void setEntryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
    }

    public String getEntryTimeInterval() {
        return entryTimeInterval;
    }

    public void setEntryTimeInterval(String entryTimeInterval) {
        this.entryTimeInterval = entryTimeInterval;
    }
}
