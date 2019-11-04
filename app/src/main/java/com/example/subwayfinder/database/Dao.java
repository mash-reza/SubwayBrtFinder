package com.example.subwayfinder.database;

import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Query("select * from station")
    List<Station> getStations();
}
