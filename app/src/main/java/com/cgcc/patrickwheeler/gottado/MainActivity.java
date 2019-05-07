// GottaDo - Final Project App for CIS165DA Android Level I
// CGCC - Spring 2019
// authors Tanner Kay & Patrick Wheeler

// Main Activity creates the initial tab layout and the Add button at the bottom of the screen.
//tiny change right now 734

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // TODO need to implement Local Storage to load/save ArrayList
    //          ArrayList to Local Storage
    //          - Load when opening/launching
    //          - if does not exists then create sampleData
    //          - Save on all edits
    //  see SQLite DB powerpoint
    // TODO may need SavePreferences in order to save in-progress actions during onPause and onResume

    // the main ArrayList of TaskEvents
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

                // launches a new Activity page, where user can create a new Task
                startActivity(new Intent(getApplicationContext(), AddTaskActivity.class));

            }
        });

        // I think we need to loadData here...
        loadData();

        taskEvents = TaskEvent.createDemoTaskEventList(8); // TODO DELETE THIS LINE ONCE SAVE/LOAD WORK

        // and then if-and-only-if ArrayList.size() == 0 after loading data, then create demoData
        // calls the static class method to create and populate the demonstration taskEvents list
        if (taskEvents.size() < 1) {
            taskEvents = TaskEvent.createDemoTaskEventList(8);
        }
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
        try {
            FileOutputStream fileOutputStream = openFileOutput("DemoFile", Context.MODE_PRIVATE);
            fileOutputStream.write("This is Data that is stored in the internal storage".getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
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
            StringBuilder builder = new StringBuilder();
            FileInputStream fileInputStream = openFileInput("DemoFile");
            while ((ch = fileInputStream.read()) != -1) {
                builder.append((char) ch);
            }
            Toast.makeText(getApplicationContext(), ""+ch, Toast.LENGTH_LONG).show();
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