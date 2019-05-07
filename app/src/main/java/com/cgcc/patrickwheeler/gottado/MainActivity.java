// GottaDo - Final Project App for CIS165DA Android Level I
// CGCC - Spring 2019
// authors Tanner Kay & Patrick Wheeler

// Main Activity creates the initial tab layout and the Add button at the bottom of the screen.
// just a tiny change plus a smaller change.

package com.cgcc.patrickwheeler.gottado;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.lang.Object;
import java.lang.String;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // TODO need to implement Local Storage to load/save ArrayList
    //          ArrayList to Local Storage
    //          - Load when opening/launching
    //          - if does not exists then create sampleData
    //          - Save on all edits
    //  see SQLite DB powerpoint
    // TODO may need SavePreferences in order to save in-progress actions during onPause and onResume

    // the main ArrayList of TaskEvents, should be easily accessible to sub fragments
    static ArrayList<TaskEvent> taskEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(fm);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        // floating action button
        // will eventually allow user to add items to the To-Do list
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                // this call to saveData only really exists so that I can trigger it deliberately
                // and prove that it works - PW
                saveData();         // temp for testing saveData behavior

                // launches a new Activity page, where user can create a new Task
                startActivity(new Intent(getApplicationContext(), AddTaskActivity.class));

            }
        });

        // LOAD PRE-EXISTING DATA:
        // For some reason, my logic needs this dummy item in order to work - PW
        taskEvents = TaskEvent.createDemoTaskEventList(1);

        // loads saved data from internal storage...
        loadData();

        // and then if-and-only-if ArrayList.size() == 0 after loading data, then create demoData
        // calls the static class method to create and populate the demonstration taskEvents list
        if (taskEvents.size() < 1) {
            taskEvents = TaskEvent.createDemoTaskEventList(8);          // don't set higher than 8
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveData();
    }

    // this method should allow any fragment or secondary activity to set the ArrayList
    public static void setTaskEvents(ArrayList<TaskEvent> taskEvents) {
        taskEvents = taskEvents;
    }

    // this method should allow any fragment or secondary activity to get the ArrayList
    public static ArrayList<TaskEvent> getTaskEvents() {
        return taskEvents;
    }

    public void saveData() {
        // writing data to internal storage
        // this code taken from class PowerPoint
        String outputString = "";

        for (TaskEvent task:taskEvents) {
            outputString = outputString.concat(task.getTaskEventName() + "," +
                    task.isComplete() + "," +
                    task.doItToday + "," +
                    task.doesRepeat + "," +
                    task.hasTimeReminder + "," +
                    task.hasMapReminder + ",");
        }

        // Toast command only here to see the outputString created for saving
         Toast.makeText(getApplicationContext(), outputString, Toast.LENGTH_LONG).show();

        try {
            FileOutputStream fileOutputStream = openFileOutput("DemoFile", Context.MODE_PRIVATE);
            fileOutputStream.write(outputString.getBytes());
            // old line before outputString variable implemented:
            // fileOutputStream.write("This is Data that is stored in the internal storage".getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();

            // temp
            Toast.makeText(getApplicationContext(), "data saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO should modify the above code, to tokenize taskEvents and write to file
        int i = taskEvents.size();              // just a line to prove to myself that this method has access to taskEvents

        // TODO once working, saveData() should be called at all of the onPause, etc events
    }

    public void loadData() {
        // reading data from a file
        // this code taken from class PowerPoint
        try {
            int ch;
            StringBuilder inputString = new StringBuilder();
            FileInputStream fileInputStream = openFileInput("DemoFile");
            while ((ch = fileInputStream.read()) != -1) {
                inputString.append((char) ch);
            }

            // TEMP prints resulting string from loading data
             Toast.makeText(getApplicationContext(), "Data Read = " + inputString, Toast.LENGTH_LONG).show();

            // TODO split inputString into tokens and populate taskEvents ArrayList (probably need to erase contents first)
            taskEvents.clear();

            String[] tokens = inputString.toString().split(",");
            int numberOfTasks = tokens.length / 6;                      // divided by the number of fields
            for (int i = 0 ; i < numberOfTasks; i++) {
                // create a TaskEvent
                TaskEvent aTask = new TaskEvent(tokens[6 * i]);

                // set its fields
                aTask.setComplete(Boolean.parseBoolean(tokens[6 * i + 1]));
                aTask.doItToday = Boolean.parseBoolean(tokens[6 * 1 + 2]);
                aTask.doesRepeat = Boolean.parseBoolean(tokens[6 * 1 + 3]);
                aTask.hasTimeReminder = Boolean.parseBoolean(tokens[6 * 1 + 4]);
                aTask.hasMapReminder = Boolean.parseBoolean(tokens[6 * 1 + 5]);

                // add it to taskEvents ArrayList
                taskEvents.add(aTask);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // taskEvents = TaskEvent.createDemoTaskEventList(1); // TEMP LINE until loadData() works

        // TODO once saveData method works, should modify this code to empty ArrayList, read file, de-tokenize, create taskEvents, and add to ArrayList

        // TODO once working, loadData() should be called at onCreate, onResume, etc ????
    }
}