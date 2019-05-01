package com.cgcc.patrickwheeler.gottado;

import java.util.ArrayList;

public class TaskEvent {
    private String mTaskEventName;
    private boolean mIsCompleted;

    public TaskEvent(String taskEventName, boolean isComplete) {
        mTaskEventName = taskEventName;
        mIsCompleted = isComplete;
    }

    public String getTaskEventName() {
        return mTaskEventName;
    }

    public boolean isComplete() {
        return mIsCompleted;
    }

    private static int taskEventID = 0;

    public static ArrayList<TaskEvent> createTaskEventList(int numTaskEvents) {
        ArrayList<TaskEvent> taskEvents = new ArrayList<TaskEvent>();

        for (int i = 1; i <= numTaskEvents; i++) {
            taskEvents.add(new TaskEvent("TaskEvent " + ++taskEventID, i <= numTaskEvents / 2));
            // above line sets first half of taskEvents boolean differently
        }

        return taskEvents;
    }
}