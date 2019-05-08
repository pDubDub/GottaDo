package com.cgcc.patrickwheeler.gottado;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;


// This fragment will container the UI for the Scheduler tab of the app

// Tutorial source for static list view:
// https://www.quickprogrammingtips.com/android/creating-static-listview-in-android.html


// This works in a crude fashion, but I'd like to figure out how to customize the cells
//    in such a way that it looks like our intended combination of calendar and task list
// If we can at least figure out how to put some select images in the cells, we should be able
//    to fake anything.



public class Tab2 extends Fragment {

//    private static String[] staticListViewArray = {
//            "Morning Task","9AM - Coffee Meeting","Another task","Task",
//            "Meet Advisor : On Campus, Before Class","12PM - CIS 162 AD, on Campus","A task on campus",
//            "Return Home","Feed Cat 2"};
//
//    public static Tab2 newInstance() {
//        Bundle args = new Bundle();
//        Tab2 fragment = new Tab2();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.tab2fake, container, false);
//        ListView listView = (ListView) mainView.findViewById(R.id.tab2listView);
//
//        listView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, staticListViewArray));
        return mainView;
    }
}
