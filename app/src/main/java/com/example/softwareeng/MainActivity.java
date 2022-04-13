package com.example.softwareeng;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.softwareeng.Nicole.MiddleMan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Calendar;
import java.util.Date;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends BaseMenu {
    final String TAG = "MAIN ACTIVITY";
    private FirebaseAuth mAuth;
    Double actualTime;
    //Button btn_Logout;
    Button btn_CheckIn;
    Button btn_Bar;
    Button btn_Schedule;
    FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    Date currentTime = Calendar.getInstance().getTime();
    String firstHalf = currentTime.toString().substring(11, 13);
    int minute = Integer.valueOf(firstHalf) + 1;
    String minuteString = String.valueOf(minute);
    //put them together
    String formattedTime = firstHalf + "-" + minuteString;
    String globalCurrentTime = formattedTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize the FireBaseAuth instance
        mAuth = FirebaseAuth.getInstance();
        //btn_Logout = findViewById(R.id.btn_Logout_MainActivity);
        btn_CheckIn = findViewById(R.id.btn_CheckIn_MainActivity);
        btn_Bar = findViewById(R.id.btn_Histogram_MainActivity);
        btn_Schedule = findViewById(R.id.btn_Schedule_MainActivity);
        //txt_gymSchedule = findViewById(R.id.txt_GymSchedule_MainActivity);
        firebaseMessaging.subscribeToTopic("change_in_population");
        firebaseMessaging.subscribeToTopic("change_in_population_filling");


        btn_CheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make this happen when check in is clicked
                DocumentReference checkIn = db.collection("checkInCounter").document("times");
                // Atomically increment the population by 1


                //fix time if its 7, 8 or 9
                if (minute == 7 || minute == 8 || minute == 9) {
                    minuteString = "0" + minuteString;
                }


                //use the current time substring to know where to place data
                Log.d("Main", "Current time:" + formattedTime);

                //'checkIn' field below has to be dynamic. change according to time
                checkIn.update(formattedTime, FieldValue.increment(1));

                MiddleMan.checkOutTimer();
            }
        });

        btn_Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BarChartActivity.class));


            }
        });

        btn_Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScheduleActivity.class));
            }
        });


        DocumentReference docRef = db.collection("checkInCounter").document("times");

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentReference threshold = db.collection("checkInCounter").document("times");
                if (task.isSuccessful()) {
                    DocumentSnapshot documentGlobalTime = task.getResult();
                    if (documentGlobalTime.exists()) {
                        actualTime = documentGlobalTime.getDouble(globalCurrentTime);
                        Log.d(TAG, "Document exists");

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }


        });

        DocumentReference Threshold = db.collection("checkInCounter").document("Threshold");


        Threshold.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentReference threshold = db.collection("checkInCounter").document("Threshold");
                if (task.isSuccessful()) {
                    DocumentSnapshot documentNotification = task.getResult();
                    if (documentNotification.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + documentNotification.getData());
                        Log.d(TAG, "GLOBAL CURRENT TIME " + globalCurrentTime);

                        if(actualTime > 350){

                            if(documentNotification.getBoolean("over70%")){
                                threshold.update("over70%" , false);
                                Log.d(TAG, "SUCCESSFUL CHECK 200 if1");

                            }else{
                                threshold.update("over70%" , true);
                                Log.d(TAG, "SUCCESSFUL CHECK 200 if2");
                            }
                            Log.d(TAG, "SUCCESSFUL CHECK 200");
                        }




                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }


        });



        DocumentReference secondThreshold = db.collection("checkInCounter").document("SecondThreshold");

        secondThreshold.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentReference threshold = db.collection("checkInCounter").document("SecondThreshold");
                if (task.isSuccessful()) {
                    DocumentSnapshot documentNotification = task.getResult();
                    if (documentNotification.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + documentNotification.getData());
                        Log.d(TAG, "GLOBAL CURRENT TIME " + globalCurrentTime);

                        if(actualTime > 200 && actualTime < 350){


                            if(documentNotification.getBoolean("over40%")){
                                threshold.update("over40%" , false);
                                Log.d(TAG, "SUCCESSFUL CHECK 200 if1");

                            }else{
                                threshold.update("over40%" , true);
                                Log.d(TAG, "SUCCESSFUL CHECK 200 if2");
                            }
                            Log.d(TAG, "SUCCESSFUL CHECK 200");
                        }




                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }


        });



    }


    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        //Check if user is already logged in (debugging)
        //Log.d(TAG, "USER IS ALREADY LOGGED IN");

        if(currentUser == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

}