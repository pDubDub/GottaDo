package com.cgcc.patrickwheeler.gottado;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cgcc.patrickwheeler.gottado.R;
import com.cgcc.patrickwheeler.gottado.Tab1;
import com.cgcc.patrickwheeler.gottado.Tab2;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */

// Called by MainActivity
//      creates a fragment for each section

// source for multi-fragment tabbbed activity that allowed me to get this to work   //
//      https://www.youtube.com/watch?time_continue=119&v=ZnhSbXuJaqQ               //


public class SectionsPagerAdapter extends FragmentPagerAdapter {

//    @StringRes
//    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
//    private final Context mContext;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
//      mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.

        switch (position) {
            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                return tab2;
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "All To-Do's";
            case 1:
                return "Daily Planner";
        }
        return null;
    }
}