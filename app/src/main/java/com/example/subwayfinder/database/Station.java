package com.example.subwayfinder.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "station")
public class Station {
    @PrimaryKey
    private int id;
    @NonNull
    private String name;
    @NonNull
    private int line;
    @NonNull
    private double lat;
    @NonNull
    private double lon;

    public Station() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @NonNull
    @Override
    public String toString() {
        return "\nid: " + this.id + ", name: " + this.name + ", line: " + this.line + ", lat: " + this.lat + ", lon:" + this.lon + "\n";
    }
}
