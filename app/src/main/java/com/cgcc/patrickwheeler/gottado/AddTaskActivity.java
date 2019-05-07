package com.cgcc.patrickwheeler.gottado;

import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {

    ArrayList<TaskEvent> localTaskEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        final EditText nameInputEditText = (EditText) findViewById(R.id.nameInputEditText);

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
}
