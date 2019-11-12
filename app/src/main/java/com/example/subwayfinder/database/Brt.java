package com.example.subwayfinder.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BRT")
public class Brt {
    @PrimaryKey
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String fa_name;
    @NonNull
    private double lat;
    @NonNull
    private double lon;
    @NonNull
    private int line;

    public Brt() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getFa_name() {
        return fa_name;
    }

    public void setFa_name(@NonNull String fa_name) {
        this.fa_name = fa_name;
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

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @NonNull
    @Override
    public String toString() {
        return "\nid: " + this.id + ", name: " + this.name + ", line: " + this.line + ", lat: " + this.lat + ", lon:" + this.lon + "\n";
    }
}
