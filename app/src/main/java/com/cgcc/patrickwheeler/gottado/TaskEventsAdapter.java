package com.cgcc.patrickwheeler.gottado;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

//  Source: https://guides.codepath.com/android/Using-the-RecyclerView

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views

public class TaskEventsAdapter extends RecyclerView.Adapter<TaskEventsAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Your holder should contain a member variable for any view that will be set as you render a row
//        public TextView nameTextView;
//        public Button messageButton;
        // TextView and Button from tutorial replaced with CheckBox
        public CheckBox checkBox;
        public ImageView timeImageView;
        public ImageView mapImageView;
        public ImageView repeatImageView;
        public ImageView todayImageView;
        public ImageView editImageView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(Context context, View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

//            nameTextView = (TextView) itemView.findViewById(R.id.task_event_name);
//            messageButton = (Button) itemView.findViewById(R.id.message_button);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            timeImageView = (ImageView) itemView.findViewById(R.id.timeImageView);
            mapImageView = (ImageView) itemView.findViewById(R.id.mapImageView);
            repeatImageView = (ImageView) itemView.findViewById(R.id.repeatImageView);
            todayImageView = (ImageView) itemView.findViewById(R.id.todayImageView);
            editImageView = (ImageView) itemView.findViewById(R.id.editImageView);

            checkBox.setOnClickListener(this);
            timeImageView.setOnClickListener(this);
            mapImageView.setOnClickListener(this);
            repeatImageView.setOnClickListener(this);
            todayImageView.setOnClickListener(this);
            editImageView.setOnClickListener(this);
        }

        // Handles clickListener
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) {
                TaskEvent thisTaskEvent = mTaskEvents.get(position);

                // TODO this needs better logic. Should really dim all icon buttons too when checked,
                //      and un-dim only the true ones when unchecked.
                if (view == checkBox) {
                    if(thisTaskEvent.isComplete()) {
                        thisTaskEvent.setComplete(false);
                        checkBox.setTextColor(Color.BLACK);
                        checkBox.setChecked(false);
                    } else {
                        thisTaskEvent.setComplete(true);
                        checkBox.setTextColor(Color.LTGRAY);
                    }
                } else if (view == timeImageView) {
                    if (thisTaskEvent.hasTimeReminder) {
                        thisTaskEvent.hasTimeReminder = false;
                        timeImageView.setImageResource(R.drawable.time_icon_off);
                    } else {
                        thisTaskEvent.hasTimeReminder = true;
                        timeImageView.setImageResource(R.drawable.time_icon_on);
                    }
                } else if (view == mapImageView) {
                    if (thisTaskEvent.hasMapReminder) {
                        thisTaskEvent.hasMapReminder = false;
                        mapImageView.setImageResource(R.drawable.map_icon_off);
                    } else {
                        thisTaskEvent.hasMapReminder = true;
                        mapImageView.setImageResource(R.drawable.map_icon_on);
                    }
                } else if (view == repeatImageView) {
                    if (thisTaskEvent.doesRepeat) {
                        thisTaskEvent.doesRepeat = false;
                        repeatImageView.setImageResource(R.drawable.repeat_icon_off);
                    } else {
                        thisTaskEvent.doesRepeat = true;
                        repeatImageView.setImageResource(R.drawable.repeat_icon_on);
                    }
                } else if (view == todayImageView) {
                    if (thisTaskEvent.doItToday) {
                        thisTaskEvent.doItToday = false;
                        todayImageView.setImageResource(R.drawable.today_icon_off);
                    } else {
                        thisTaskEvent.doItToday = true;
                        todayImageView.setImageResource(R.drawable.today_icon_on);
                    }
                } else if (view == editImageView) {

                    // launch AddTaskActivity here


                    // not sure how to do it. The following code does not compile:
//                    Intent intent = new Intent(view.getContext(), StartActivity.AddTaskActivity);
//                    view.getContext().startActivity(intent);
//                    MainActivity.startActivity(new Intent(MainActivity.getApplicationContext(), AddTaskActivity.class));
                }

            }
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
        View taskEventView = inflater.inflate(R.layout.item_taskevent, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(context, taskEventView);
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
        ImageView timeImageView = viewHolder.timeImageView;
        ImageView mapImageView = viewHolder.mapImageView;
        ImageView repeatImageView = viewHolder.repeatImageView;
        ImageView todayImageView = viewHolder.todayImageView;

        // checkBox.setChecked(!taskEvent.isComplete());
        checkBox.setText(taskEvent.getTaskEventName());
        checkBox.setTextColor(taskEvent.isComplete() ? Color.LTGRAY : Color.BLACK);
        checkBox.setChecked(taskEvent.isComplete() ? true : false);


        if ((taskEvent.hasTimeReminder)) {
            timeImageView.setImageResource(R.drawable.time_icon_on);
        } else {
            timeImageView.setImageResource(R.drawable.time_icon_off);
        }

        if (taskEvent.hasMapReminder) {
            mapImageView.setImageResource(R.drawable.map_icon_on);
        } else {
            mapImageView.setImageResource(R.drawable.map_icon_off);
        }

        if (taskEvent.doesRepeat) {
            repeatImageView.setImageResource(R.drawable.repeat_icon_on);
        } else {
            repeatImageView.setImageResource(R.drawable.repeat_icon_off);
        }

        // tried doing this via get/set methods instead, still doesn't work.
        if(taskEvent.doingItToday()) {
            todayImageView.setImageResource(R.drawable.today_icon_on);
        }else {
            todayImageView.setImageResource(R.drawable.today_icon_off);
        }
        // NOTE: the UI checkbox behavior works, but it doesn't actually changed boolean of TaskEvent
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mTaskEvents.size();
    }
}