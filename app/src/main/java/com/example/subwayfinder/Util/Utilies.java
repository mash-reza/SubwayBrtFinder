package com.example.subwayfinder.Util;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.subwayfinder.database.Database;
import com.example.subwayfinder.database.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utilies {

    private static Utilies instance;
    private List<Geo> geoList = new ArrayList<>();
    private static List<Station> stationList = new ArrayList<>();

    /**
     * @param context application context to use with room
     * @return singleton object of class
     */
    public static Utilies getInstance(Context context) {
        if (instance == null) {
            instance = new Utilies();
            stationList.addAll(Room.databaseBuilder(context, Database.class, "subway").build().dao().getStations());
            return instance;
        }
        return instance;
    }


    /**
     * @param lat
     * @param lon
     * @return the id of the station witch is nearest to the location provided in method signature
     */
    public int getNearestStation(double lat, double lon) {
        for (Station station : stationList) {
            geoList.add(new Geo(station.getId(), distance(
                    lat, station.getLat(), lon, station.getLon(), 0, 0
            )));
        }
        Collections.sort(geoList, (o1, o2) -> Double.compare(o1.getDistance(), o2.getDistance()));
        return geoList.get(0).getId();
    }

    private double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
