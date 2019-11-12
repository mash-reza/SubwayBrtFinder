package com.example.subwayfinder.Util;

import android.content.Context;

import com.example.subwayfinder.database.Brt;
import com.example.subwayfinder.database.Database;
import com.example.subwayfinder.database.Metro;

import java.util.ArrayList;
import java.util.List;

public class Utilies {

    private static Utilies instance;
    private static List<Brt> brtStationList = new ArrayList<>();
    private static List<Metro> metroStationList = new ArrayList<>();

    /**
     * @param context application context to use with room
     * @return singleton object of class
     */
    public static Utilies getInstance(Context context) {
        if (instance == null) {
            instance = new Utilies();
            brtStationList.addAll(Database.getInstance(context).dao().getBrtStations());
            metroStationList.addAll(Database.getInstance(context).dao().getMetroStations());
            return instance;
        }
        return instance;
    }


    /**
     * @param lat
     * @param lon
     * @return the id of the station witch is nearest to the location provided in method signature
     */
    public Brt getNearestBrtStation(double lat, double lon) {
        Brt returningStation = null;
        double distance = 0;
        for (Brt station : brtStationList) {
            double calculatedDistance = Math.abs(distance(lat, station.getLat(), lon, station.getLon(), 0, 0));
            if (returningStation == null) {
                returningStation = station;
                distance = calculatedDistance;
                continue;
            }
            if (distance > calculatedDistance) {
                distance = calculatedDistance;
                returningStation = station;
            }
        }
        return returningStation;
    }

    /**
     * @param lat
     * @param lon
     * @param line
     * @return the id of the station witch is nearest to the location provided in method signature
     */
    public Brt getNearestBrtStation(double lat, double lon, int line) {
        Brt returningStation = null;
        double distance = 0;
        for (Brt station : brtStationList) {
            if (station.getLine() == line) {
                double calculatedDistance = Math.abs(distance(lat, station.getLat(), lon, station.getLon(), 0, 0));
                if (returningStation == null) {
                    returningStation = station;
                    distance = calculatedDistance;
                    continue;
                }
                if (distance > calculatedDistance) {
                    distance = calculatedDistance;
                    returningStation = station;
                }
            }
        }
        return returningStation;
    }
    /**
     * @param lat
     * @param lon
     * @return the id of the station witch is nearest to the location provided in method signature
     */
    public Metro getNearestMetroStation(double lat, double lon) {
        Metro returningStation = null;
        double distance = 0;
        for (Metro station : metroStationList) {
            double calculatedDistance = Math.abs(distance(lat, station.getLat(), lon, station.getLon(), 0, 0));
            if (returningStation == null) {
                returningStation = station;
                distance = calculatedDistance;
                continue;
            }
            if (distance > calculatedDistance) {
                distance = calculatedDistance;
                returningStation = station;
            }
        }
        return returningStation;
    }

    /**
     * @param lat
     * @param lon
     * @param line
     * @return the id of the station witch is nearest to the location provided in method signature
     */
    public Metro getNearestMetroStation(double lat, double lon, int line) {
        Metro returningStation = null;
        double distance = 0;
        for (Metro station : metroStationList) {
            if (station.getLine() == line) {
                double calculatedDistance = Math.abs(distance(lat, station.getLat(), lon, station.getLon(), 0, 0));
                if (returningStation == null) {
                    returningStation = station;
                    distance = calculatedDistance;
                    continue;
                }
                if (distance > calculatedDistance) {
                    distance = calculatedDistance;
                    returningStation = station;
                }
            }
        }
        return returningStation;
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
