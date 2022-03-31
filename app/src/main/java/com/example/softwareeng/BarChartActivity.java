package com.example.softwareeng;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
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
        checkIns.add(new BarEntry(2,320));
        checkIns.add(new BarEntry(3,20));
        checkIns.add(new BarEntry(4,420));
        checkIns.add(new BarEntry(5,120));
        checkIns.add(new BarEntry(6,100));
        checkIns.add(new BarEntry(7,90));
        checkIns.add(new BarEntry(8,90));
        checkIns.add(new BarEntry(9,90));
        checkIns.add(new BarEntry(10,10));
        checkIns.add(new BarEntry(11,80));
        checkIns.add(new BarEntry(12,90));
        checkIns.add(new BarEntry(13,90));
        checkIns.add(new BarEntry(14,90));
        checkIns.add(new BarEntry(15,120));
        checkIns.add(new BarEntry(16,320));
        checkIns.add(new BarEntry(17,20));



        BarDataSet barDataSet = new BarDataSet(checkIns,"");


        barDataSet.setValueTextSize(13f);

        BarData barData = new BarData(barDataSet);


        barChart.setData(barData);
        String[] times = new String[] {"6AM", "7AM","8AM","9AM","10AM","11AM","12PM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM","9PM","10PM","11PM",};
        XAxis xAxis = barChart.getXAxis();

        xAxis.setValueFormatter(new IndexAxisValueFormatter(times));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(7);

        barChart.invalidate();
//update
        barChart.animateY(500);




    }
}