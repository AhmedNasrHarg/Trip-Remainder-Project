package com.example.tripplanner.POJOs;

public class Trip {
    private String tripName;
    private String startPoint;
    private String endPoint;
    private String tripDate;
    private String tripTime;
    private String tripType;    //round or oneWay
    private String status;      // upcoming, or Cancelled, or Done
    private double longtiude;
    private double latitude;

    public Trip() {
    }

    public Trip(String tripName, String startPoint, String endPoint, String tripDate, String tripTime, String tripType, String status,double longtiude,double latitude) {
        this.tripName = tripName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.tripDate = tripDate;
        this.tripTime = tripTime;
        this.tripType = tripType;
        this.status=status;
        this.longtiude=longtiude;
        this.latitude=latitude;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public String getTripTime() {
        return tripTime;
    }

    public void setTripTime(String tripTime) {
        this.tripTime = tripTime;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getLongtiude() {
        return longtiude;
    }

    public void setLongtiude(double longtiude) {
        this.longtiude = longtiude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
