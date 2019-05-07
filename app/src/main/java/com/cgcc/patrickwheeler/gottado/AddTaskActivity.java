package com.cgcc.patrickwheeler.gottado;

import android.content.Context;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {

    ArrayList<TaskEvent> localTaskEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        final EditText nameInputEditText = (EditText) findViewById(R.id.nameInputEditText);

        final Switch todaySwitch = (Switch) findViewById(R.id.todaySwitch);
        final Switch repeatSwitch = (Switch) findViewById(R.id.repeatSwitch);
        final Switch timeSwitch = (Switch) findViewById(R.id.repeatSwitch);
        final Switch geoSwitch = (Switch) findViewById(R.id.geoSwitch);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        Button saveButton = (Button) findViewById(R.id.saveButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // this action would save the new or edited TaskEvent, and then...

                // would need to create newTaskEvent
                TaskEvent newTaskEvent = new TaskEvent(nameInputEditText.getText().toString());
                // populate its properties
                newTaskEvent.doItToday = todaySwitch.isChecked();
                newTaskEvent.doesRepeat = repeatSwitch.isChecked();
                newTaskEvent.hasTimeReminder = timeSwitch.isChecked();
                newTaskEvent.hasMapReminder = geoSwitch.isChecked();

                // and then...
                // should be able to call
                localTaskEvents = MainActivity.getTaskEvents();
                // to get the arrayList, then add a new TaskEvent to the list
                localTaskEvents.add(newTaskEvent);
                // and then call
                MainActivity.setTaskEvents(localTaskEvents);

                // BEST I CAN TELL, this works, but I can't figure out how to update the RecyclerView yet
                //      I think there's an "Adapter".notifyDataSetChanged(); method to be called, but I have no idea where

                finish();
            }
        });

    }

    // this method is triggered by the Time Sample button on the AddView to show a sample Time Reminder Notification
    public void TimeSampleAlert(View v) {

        EditText nameInputEditText = (EditText) findViewById(R.id.nameInputEditText);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((nameInputEditText.getText().toString().isEmpty()) ? "Sample DO SOMETHING" : nameInputEditText.getText().toString() );
        builder.setMessage("You asked to be reminded to " + ((nameInputEditText.getText().toString().isEmpty()) ? "DO SOMETHING" : nameInputEditText.getText().toString() ) +" at 4:00 PM");
        builder.setPositiveButton("Done!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Pressed DONE Button", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Snooze", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Pressed SNOOZE Button", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    // this method is triggered by the Geo Sample button on the AddView to show a sample Geo-fenced Reminder Notification
    public void MapSampleAlert(View v) {

        EditText nameInputEditText = (EditText) findViewById(R.id.nameInputEditText);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((nameInputEditText.getText().toString().isEmpty()) ? "Sample DO SOMETHING" : nameInputEditText.getText().toString() );
        builder.setMessage("You asked to be reminded to " + ((nameInputEditText.getText().toString().isEmpty()) ? "DO SOMETHING" : nameInputEditText.getText().toString() ) + " at when you arrived SOMEPLACE");
        builder.setPositiveButton("Done!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Pressed DONE Button", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Snooze", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Pressed SNOOZE Button", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
