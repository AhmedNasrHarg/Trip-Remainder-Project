package com.example.tripplanner.POJOs;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Trip implements Serializable {
    private String tripName;
    private String startPoint;
    private String endPoint;
    private String tripDate;
    private String tripTime;
    private String tripType;    //round or oneWay
    private String status;      // upcoming, or Cancelled, or Done
    private double longtiude;
    private double latitude;
    private String id;
    private ArrayList<String> notes= new ArrayList<>();

    private int requestCode;
    private int year;
    private int month;
    private int dayOfMonth;
    private int minute;
    private int hourOfDay;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addNewNote(String note){
        notes.add(note);
    }
    public ArrayList<String> getNotes(){
        return notes;
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

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }
}
