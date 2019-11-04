package com.example.subwayfinder.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.subwayfinder.R;
import com.example.subwayfinder.Util.Utilies;
import com.example.subwayfinder.database.Database;
import com.example.subwayfinder.database.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    List<Station> stations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        AsyncTask.execute(() -> {
//            stations.addAll(Database.getInstance(getApplicationContext()).dao().getStations());
//            for (Station station : stations) {
//                Log.i(TAG, "sd"+station.getId() + "\n" + station.getName() + "\n" + station.getLine() + "\n" + station.getLat() + "\n" + station.getLon());
//            }
//        });
        AsyncTask.execute(() -> {
        int id = Utilies.getInstance(getApplicationContext()).getNearestStation(35.6916123,51.4227613);
        Log.i(TAG, "station id: id");

            Log.i(TAG, Room.databaseBuilder(getApplicationContext(), Database.class, "subway").build().dao().getStation(id).toString());
        });
    }
}
