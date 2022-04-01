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
                        Log.d(TAG, "DATA FROM 6-7: " + document.getDouble("06-07"));


                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        BarChart barChart = findViewById(R.id.barChart);

        barChart.setPinchZoom(false);
        barChart.setDoubleTapToZoomEnabled(false);



        ArrayList<BarEntry> checkIns = new ArrayList<>();
        checkIns.add(new BarEntry(1, 120));
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




    }
}