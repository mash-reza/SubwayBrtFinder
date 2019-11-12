package com.example.subwayfinder.database;

import android.database.Cursor;

import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Query("select * from METRO")
    List<Metro> getMetroStations();

    @Query("select * from BRT")
    List<Brt> getBrtStations();


}
