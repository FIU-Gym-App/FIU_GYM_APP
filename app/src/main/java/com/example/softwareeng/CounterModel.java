package com.example.softwareeng;

public class CounterModel {
    private int checkIn;

    public CounterModel(){}

    public CounterModel(int checkIn){
        this.checkIn = checkIn;

    }
    public int getChecksIn() {return this.checkIn;}

    public void setCheckIn(int checkIn){
        this.checkIn = checkIn;
    }

}
