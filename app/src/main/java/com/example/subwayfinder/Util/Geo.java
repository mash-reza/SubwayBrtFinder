package com.example.subwayfinder.Util;

public class Geo{
    private int id;
    private double distance;

    public int getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public Geo(int id, double distance) {
        this.id = id;
        this.distance = distance;
    }

//    @Override
//    public int compareTo(Double distance) {
//        return Double.compare(this.distance,distance);
//    }
}
