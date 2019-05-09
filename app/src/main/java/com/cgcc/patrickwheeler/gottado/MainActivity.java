// GottaDo - Final Project App for CIS165DA Android Level I
// CGCC - Spring 2019
// authors Tanner Kay & Patrick Wheeler

// Main Activity creates the initial tab layout and the Add button at the bottom of the screen.
//tiny change right now 734

package com.cgcc.patrickwheeler.gottado;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.Snackbar;

import android.util.Log;
import android.view.View;
import android.view.Gravity;

import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.maps.CameraUpdateFactory;       says "gms" not resolved
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.lang.Object;
import java.lang.String;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // TODO may need SavePreferences in order to save in-progress actions during onPause and onResume

    // the main ArrayList of TaskEvents, should be easily accessible to sub fragments
    static ArrayList<TaskEvent> taskEvents;

    String weekDay;

    // attempted to get location data, but could not get to work.

//    LocationManager locationManager;
//    LocationListener locationListener;
//
//    // code from instructor sample
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == 1) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//                }
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // LOAD PRE-EXISTING DATA:
        // For some reason, my logic needs this dummy item in order to work - PW
        //      For the time being, lets just load sample data by default.
        //      If saved data exists, it will proceed to blow out sample data.
        taskEvents = TaskEvent.createDemoTaskEventList(8);

        // Tried to get location awareness to work, but was getting an unresolved identifier error in import statements
        // loadGeoLocation();

        // loads saved data from internal storage...
        loadData();

        TextView titleTextView = (TextView) findViewById(R.id.title);

        FragmentManager fm = getSupportFragmentManager();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(fm);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        // floating action button
        //       allows user to add items to the To-Do list
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

        // Old location of loadData() call
        // idea was to have it loadData(), then if no data loaded, it would call this createDemoTaskEventList() method
        //
        // if-and-only-if ArrayList.size() == 0 after loading data, then create demoData
        // calls the static class method to create and populate the demonstration taskEvents list
//        if (taskEvents.size() < 1) {
//            taskEvents = TaskEvent.createDemoTaskEventList(8);          // don't set higher than 8
//        }

        // saveData();             // testing to see if this refreshes the list-1 after adding a new Task - DIDNT WORK

        // gets current day and adds it to the Title on the Main UI
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        Calendar calendar = Calendar.getInstance();
        weekDay = dayFormat.format(calendar.getTime());
        titleTextView.setText("GottaDo          ..." + weekDay + "!");
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveData();
    }

    // Thought this would be useful to make it reload/refresh data, but instead it was preventing AddTaskActivity from actually adding to the ArrayList.
//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//
//        loadData();
//    }

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
        // Toast.makeText(getApplicationContext(), outputString, Toast.LENGTH_LONG).show();

        try {
            FileOutputStream fileOutputStream = openFileOutput("GottaDoDataFile", Context.MODE_PRIVATE);
            fileOutputStream.write(outputString.getBytes());
            // old line before outputString variable implemented:
            // fileOutputStream.write("This is Data that is stored in the internal storage".getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();

            // temp
            Toast.makeText(getApplicationContext(), "GottaDo data saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // int i = taskEvents.size();              // just a line to prove to myself that this method has access to taskEvents

        // once working, saveData() should be called at all of the onPause, etc events
    }

    public void loadData() {
        // reading data from a file
        // this code taken from class PowerPoint
        try {
            int ch;
            StringBuilder inputString = new StringBuilder();
            FileInputStream fileInputStream = openFileInput("GottaDoDataFile");
            while ((ch = fileInputStream.read()) != -1) {
                inputString.append((char) ch);
            }

            // TEMP prints resulting string from loading data
            // Toast.makeText(getApplicationContext(), "Data Read = " + inputString, Toast.LENGTH_LONG).show();

            // erase taskEvents ArrayList contents first before loading
            taskEvents.clear();

            // split inputString into tokens and populate taskEvents ArrayList
            String[] tokens = inputString.toString().split(",");
            int numberOfTasks = tokens.length / 6;                      // divided by the number of fields

            for (int i = 0 ; i < numberOfTasks; i++) {
                // create a TaskEvent
                TaskEvent aTask = new TaskEvent(tokens[6 * i]);

                // set its fields
                aTask.setComplete(Boolean.parseBoolean(tokens[6 * i + 1]));
                aTask.doItToday = Boolean.parseBoolean(tokens[6 * i + 2]);
                aTask.doesRepeat = Boolean.parseBoolean(tokens[6 * i + 3]);
                aTask.hasTimeReminder = Boolean.parseBoolean(tokens[6 * i + 4]);
                aTask.hasMapReminder = Boolean.parseBoolean(tokens[6 * i + 5]);

                // add it to taskEvents ArrayList
                taskEvents.add(aTask);
            }

            Toast.makeText(getApplicationContext(), "Stored GottaDo data loaded!", Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "No saved data. Demo data loaded.", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // once working, loadData() should be called at onCreate, onResume, etc
    }

//    Tried to get location awareness to work, but was getting an unresolved identifier error in the import statements

//    public void loadGeoLocation() {
//
//        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
//                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
//
//                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
//
//                try {
//                    List<Address> listAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//
//                    if (listAddresses != null && ((List) listAddresses).size() > 0) {
//                        String address = "";
//
//                        if (listAddresses.get(0).getThoroughfare() != null) {
//                            address += listAddresses.get(0).getPostalCode() + " ";
//                        }
//
//                        if (listAddresses.get(0).getAdminArea() != null) {
//                            address += listAddresses.get(0).getAdminArea();
//                        }
//
//                        Toast.makeText(MapsActivity.this, address, Toast.LENGTH_LONG).show();
//                        Log.i("Address", address);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//            Toast geoToast = Toast.makeText(getApplicationContext(),"Geolocation Message", Toast.LENGTH_LONG);
//        geoToast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 250);
//        geoToast.show();
//
//    }


    // Tried implementing basic optionsMenu but it is apparently not compatible with current Tabbed Activity
}