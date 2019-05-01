package com.cgcc.patrickwheeler.gottado;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Tab1 extends Fragment {

    ArrayList<TaskEvent> taskEvents;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.list1, container, false);

        // 1. get reference to the recyclerView
        // Lookup the recyclerview in activity layout
        RecyclerView rvTaskEvents = (RecyclerView) rootView.findViewById(R.id.rvTaskEvents);

        // 2. Set layout manager to position the items
        rvTaskEvents.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize contacts
        taskEvents = TaskEvent.createTaskEventList(20);

        // Create adapter passing in the sample user data
        TaskEventsAdapter adapter = new TaskEventsAdapter(taskEvents);
        // Attach the adapter to the recyclerview to populate items
        rvTaskEvents.setAdapter(adapter);

        // That's all!

        return rootView;
    }


}
