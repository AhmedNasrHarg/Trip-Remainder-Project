package com.example.tripplanner.POJOs;

public class Trip {
    private String tripName;
    private String startPoint;
    private String endPoint;
    private String tripDate;
    private String tripTime;
    private String tripType;    //round or oneWay
    private String status;      // upcoming, or Cancelled, or Done

    public Trip() {
    }

    public Trip(String tripName, String startPoint, String endPoint, String tripDate, String tripTime, String tripType, String status) {
        this.tripName = tripName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.tripDate = tripDate;
        this.tripTime = tripTime;
        this.tripType = tripType;
        this.status=status;
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
}
