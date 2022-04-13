package com.example.softwareeng;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ScheduleActivity extends BaseMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}