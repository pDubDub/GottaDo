package com.cgcc.patrickwheeler.gottado;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

//  Source: https://guides.codepath.com/android/Using-the-RecyclerView

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class TaskEventsAdapter extends RecyclerView.Adapter<TaskEventsAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Your holder should contain a member variable for any view that will be set as you render a row
//        public TextView nameTextView;
//        public Button messageButton;
        // TextView and Button from tutorial replaced with CheckBox
        public CheckBox checkBox;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

//            nameTextView = (TextView) itemView.findViewById(R.id.task_event_name);
//            messageButton = (Button) itemView.findViewById(R.id.message_button);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }

    // Store a member variable for the contacts
    private List<TaskEvent> mTaskEvents;

    // Pass in the contact array into the constructor
    public TaskEventsAdapter(List<TaskEvent> taskEvents) {
        mTaskEvents = taskEvents;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public TaskEventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_taskevent, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TaskEventsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        TaskEvent taskEvent = mTaskEvents.get(position);

        // Set item views based on your views and data model
//        TextView textView = viewHolder.nameTextView;
//        textView.setText(taskEvent.getName());

//        Button button = viewHolder.messageButton;
//        button.setText(taskEvent.isOnline() ? "Button" : "Offline");
//        button.setEnabled(taskEvent.isOnline());

        // TextView and Button from tutorial replaced with CheckBox
        CheckBox checkBox = viewHolder.checkBox;
        checkBox.setChecked(!taskEvent.isComplete());
        checkBox.setText(taskEvent.getTaskEventName());
        checkBox.setTextColor(taskEvent.isComplete() ? Color.BLACK : Color.LTGRAY);

        // NOTE: the UI checkbox behavior works, but it doesn't actually changed boolean of TaskEvent
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mTaskEvents.size();
    }
}