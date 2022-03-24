package com.example.softwareeng;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends BaseMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> checkIns = new ArrayList<>();
        checkIns.add(new BarEntry(1,120));
        checkIns.add(new BarEntry(2,520));
        checkIns.add(new BarEntry(3,20));
        checkIns.add(new BarEntry(4,420));
        checkIns.add(new BarEntry(5,620));
        checkIns.add(new BarEntry(6,100));
        checkIns.add(new BarEntry(7,90));

        BarDataSet barDataSet = new BarDataSet(checkIns,"Population");

        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Popularity");
        barChart.animateY(500);




    }
}