package com.example.softwareeng.Nicole;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class MiddleMan {


    public static void checkOutTimer(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        GymUser user = new GymUser();
        GymTimer timer = new GymTimer(5);


        //when timer  = 0,  meaning that the 5 seconds have passed, subtract one from the data base
        //Timer that works xd
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        DocumentReference checkIn = db.collection("checkInCounter").document("checkIn");
                        // Atomically increment the population by 1
                        checkIn.update("checkIn", FieldValue.increment(-1));
                    }
                },
                5000
        );


    }
}
