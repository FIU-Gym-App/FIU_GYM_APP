package com.example.softwareeng;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BarChartActivity extends BaseMenu {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String TAG = "BarChartActivity";
    DocumentSnapshot document;
    ArrayList<BarEntry> checkIns = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //DocumentReference checkIn = db.collection("checkInCounter").document("times");
        DocumentReference docRef = db.collection("checkInCounter").document("times");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        Log.d(TAG, "DATA FROM 6-7: " + String.valueOf(document.getDouble("06-07")));


                        checkIns.add(new BarEntry(1, document.getDouble("06-07").floatValue()));
                        checkIns.add(new BarEntry(2,document.getDouble("07-08").floatValue()));
                        checkIns.add(new BarEntry(3,document.getDouble("08-09").floatValue()));
                        checkIns.add(new BarEntry(4,document.getDouble("09-10").floatValue()));
                        checkIns.add(new BarEntry(5,document.getDouble("10-11").floatValue()));
                        checkIns.add(new BarEntry(6,document.getDouble("11-12").floatValue()));
                        checkIns.add(new BarEntry(7,document.getDouble("12-13").floatValue()));
                        checkIns.add(new BarEntry(8,document.getDouble("13-14").floatValue()));
                        checkIns.add(new BarEntry(9,document.getDouble("14-15").floatValue()));
                        checkIns.add(new BarEntry(10,document.getDouble("15-16").floatValue()));
                        checkIns.add(new BarEntry(11,document.getDouble("16-17").floatValue()));
                        checkIns.add(new BarEntry(12,document.getDouble("17-18").floatValue()));
                        checkIns.add(new BarEntry(13,document.getDouble("18-19").floatValue()));
                        checkIns.add(new BarEntry(17,document.getDouble("19-20").floatValue()));
                        checkIns.add(new BarEntry(14,document.getDouble("20-21").floatValue()));
                        checkIns.add(new BarEntry(15,document.getDouble("21-22").floatValue()));
                        checkIns.add(new BarEntry(16,document.getDouble("22-23").floatValue()));


                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        BarChart barChart = findViewById(R.id.barChart);

                        barChart.setPinchZoom(false);
                        barChart.setDoubleTapToZoomEnabled(false);

                        BarDataSet barDataSet = new BarDataSet(checkIns,"Population");

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

                        barChart.animateY(500);



                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


    }
}