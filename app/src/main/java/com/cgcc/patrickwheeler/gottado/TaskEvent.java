package com.cgcc.patrickwheeler.gottado;

import java.util.ArrayList;

public class TaskEvent {

    // fields, properties, whatever you wanna call 'em
    private String mTaskEventName;
    private boolean mIsCompleted;
    public boolean doItToday;
    // startDate
    // startTime
    public boolean doesRepeat;
    // deadline
    // duration
    public boolean hasTimeReminder;
    // reminderTime
    public boolean hasMapReminder;
    // reminderLocation
    // UniqueID             ??
    // SortOrder#
    // do we need an IsEvent boolean, to distinguish calendar events from tasks? (both are TaskEvent objects)

    public TaskEvent(String taskEventName, boolean isComplete) {
        mTaskEventName = taskEventName;
        mIsCompleted = isComplete;
        doItToday = false;
        doesRepeat = false;
        hasTimeReminder = false;
        hasMapReminder = false;
    }

    public String getTaskEventName() {
        return mTaskEventName;
    }

    public boolean isComplete() {
        return mIsCompleted;
    }
    public void setComplete(Boolean value) { mIsCompleted = value; }

    private static int taskEventID = 0;

    public static ArrayList<TaskEvent> createTaskEventList(int numTaskEvents) {
        ArrayList<TaskEvent> taskEvents = new ArrayList<TaskEvent>();

        for (int i = 1; i <= numTaskEvents; i++) {
            taskEvents.add(new TaskEvent("TaskEvent " + ++taskEventID, false));
            // above line sets first half of taskEvents boolean differently
        }

        return taskEvents;
    }

    /* TODO: 2019-05-01
    *   I guess next would be to make the data save/persist (whether that's SQLite, I don't know)
    *       and then only run the above createTaskEventList() if it doesn't exist to be loaded.
    * */

}