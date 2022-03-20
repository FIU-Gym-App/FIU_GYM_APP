package com.example.softwareeng.Nicole;
import java.util.Timer;

public class GymTimer {
    Timer timer;

    public GymTimer(int seconds){
        timer = new Timer();
        timer.schedule(new GymTimerTask(), seconds*1000);
    }
}
