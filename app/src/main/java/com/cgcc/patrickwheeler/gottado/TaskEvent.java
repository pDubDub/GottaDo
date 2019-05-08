package com.cgcc.patrickwheeler.gottado;

import java.util.ArrayList;

// TaskEvent class is used for To-Do tasks in GottaDo.
// Additionally, GottaDo would eventually import events from the phone's calendar app, hence TaskEvent.

public class TaskEvent {

    // fields, properties, whatever you wanna call 'em
    private String mTaskEventName;
    private boolean mIsCompleted;
    public boolean doItToday;                   // TaskEvents marked Today would float to top of lists.
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

    public TaskEvent(String taskEventName) {
        mTaskEventName = taskEventName;
        mIsCompleted = false;
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

    public boolean doingItToday() { return doItToday; }
    public void setDoItToday(Boolean value) { doItToday = value; }

    private static int taskEventID = 0;

    // this static method creates a TaskEventList, originally called by Tab1.java
    //      now it is called by MainActivity
    public static ArrayList<TaskEvent> createDemoTaskEventList(int numTaskEvents) {
        ArrayList<TaskEvent> taskEvents = new ArrayList<TaskEvent>();

        // array to populate the created TaskEventList with actual task titles
        String[] nameArray = {"Feed Cat", "Turn in Final Project", "Visit Advisor",
                "Drop off mail", "Buy Cat Food", "Get Milk",
                "Call Mom", "Pay Phone Bill"};

        for (int i = 1; i <= numTaskEvents; i++) {

            taskEvents.add(new TaskEvent(nameArray[i-1]));
            // above line sets first half of taskEvents boolean differently
        }

        return taskEvents;
    }

    /* TODO: 2019-05-01
    *   I guess next would be to make the data save/persist (whether that's SQLite, I don't know)
    *       and then only run the above createDemoTaskEventList() if it doesn't exist to be loaded.
    * */

}